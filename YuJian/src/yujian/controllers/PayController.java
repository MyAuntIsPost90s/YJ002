package yujian.controllers;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import weixin.bll.WeiXinPayer;
import weixin.utilities.HttpHelper;
import weixin.utilities.XMLHelper;
import yujian.common.OrderType;
import yujian.common.RandomNum;
import yujian.common.ResultStatus;
import yujian.common.Skin;
import yujian.models.Orders;
import yujian.models.RespJson;
import yujian.models.Users;
import yujian.service.OrdersService;
import yujian.service.SysConfigsService;
import yujian.service.UsersService;
import yujian.utilities.ConvertHelper;

@Controller
@RequestMapping("Pay")
public class PayController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private SysConfigsService sysConfigsService;

	/**
	 * 充值
	 * 
	 * @param openid
	 * @param type
	 * @param request
	 * @return
	 */
	@ResponseBody
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "DoPay", method = RequestMethod.POST)
	public RespJson doPay(long type, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		Users user = (Users) request.getSession().getAttribute(Skin.USER);
		try {
			// 获取openid
			if (user.getWeixinoid() == null || user.getWeixinoid().isEmpty() || user == null) {
				Logger.getRootLogger().error("支付openid为空");
				respJson.setStatus(ResultStatus.FAIL);
				respJson.setMsg(Skin.TipMap.get("payfail"));
				return respJson;
			}

			// 充值类别
			String cost = "1";
			String body = "充值";
			String orderid = RandomNum.getOrderID();
			if (type == 1) {
				body += "10金币";
				cost = "1";
			}
			if (type == 5) {
				body += "50金币";
				cost = "5";
			}
			if (type == 10) {
				body += "100金币";
				cost = "10";
			}
			if (type == 100) {
				body += "1000金币";
				cost = "100";
			}
			if (type == 500) {
				body += "5000金币";
				cost = "500";
			}
			if (type == 1000) {
				body += "VIP";
				cost = "1000";
			}
			// 转cost以分为单位
			cost += "00";

			WeiXinPayer weiXinPayer = new WeiXinPayer();

			SortedMap<Object, Object> paraMap = new TreeMap<Object, Object>();
			paraMap.put("appid", weiXinPayer.getConfig().getAppID());
			paraMap.put("body", body);
			paraMap.put("mch_id", weiXinPayer.getConfig().getMchID());
			paraMap.put("nonce_str", weiXinPayer.create_nonce_str().replaceAll("-", ""));
			paraMap.put("openid", user.getWeixinoid());
			paraMap.put("out_trade_no", orderid);
			paraMap.put("spbill_create_ip", request.getRemoteAddr());
			paraMap.put("total_fee", cost);
			paraMap.put("trade_type", "JSAPI");
			paraMap.put("notify_url", weiXinPayer.getConfig().getPayRedirectUrl());
			String sign = weiXinPayer.getSign(paraMap);
			paraMap.put("sign", sign);

			@SuppressWarnings("static-access")
			String requestXML = weiXinPayer.getRequestXml(paraMap);
			String result = HttpHelper.httpsRequest(weiXinPayer.getConfig().getWxPayUrl(), "POST", requestXML);
			Map<String, String> map = new HashMap<String, String>();
			map = XMLHelper.doXMLParse(result);
			// 下单成功
			if (map.get("return_code").equals("SUCCESS")) {
				SortedMap<Object, Object> params = new TreeMap<Object, Object>();
				params.put("appId", weiXinPayer.getConfig().getAppID());
				params.put("timeStamp", weiXinPayer.create_timestamp());
				params.put("nonceStr", weiXinPayer.create_nonce_str());
				params.put("package", "prepay_id=" + map.get("prepay_id"));
				params.put("signType", "MD5");
				String paySign = weiXinPayer.getSign(params);
				params.put("packageValue", "prepay_id=" + map.get("prepay_id")); // 这里用packageValue是预防package是关键字在js获取值出错
				params.put("paySign", paySign); // paySign的生成规则和Sign的生成规则一致

				respJson.setStatus(ResultStatus.SUCCESS);
				respJson.setMsg("正在等待支付...");
				respJson.setJsonData(params);
			} else {
				Logger.getRootLogger().error(map.get("return_msg"));
				respJson.setStatus(ResultStatus.FAIL);
				respJson.setMsg(Skin.TipMap.get("payfail"));
			}
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg(Skin.TipMap.get("payfail"));
		}

		return respJson;
	}

	/**
	 * 通知支付结果
	 * 
	 * @param request
	 * @param response
	 */
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "WxNotify")
	public void wxNotify(HttpServletRequest request, HttpServletResponse response) {
		Skin.PayLock.lock();
		try {
			try (InputStream inStream = request.getInputStream();
					ByteArrayOutputStream outSteam = new ByteArrayOutputStream()) {
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = inStream.read(buffer)) != -1) {
					outSteam.write(buffer, 0, len);
				}
				// log.info("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");
				String result = new String(outSteam.toByteArray(), "utf-8");
				Map<String, String> map = XMLHelper.doXMLParse(result);
				if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
					Users user = usersService.getSingleByWXOID(map.get("openid"));
					int cost = ConvertHelper.convertToInt(map.get("total_fee")) / 100;// 这里的total_fee以分为单位
					String orderid = map.get("out_trade_no");
					// 生成订单
					Orders orders = new Orders();
					orders.setOrdercost((float) cost);
					orders.setOrderdate(new Date());
					orders.setOrderid(orderid);
					orders.setOrdertype(OrderType.RECHARGE);
					orders.setUserid(user == null ? 0 : user.getUserid());

					user.setRiches(user.getRiches() +  (cost * 10));
					if (cost != 1000) {
						user.setGoldbalance(user.getGoldbalance() + (cost * 10));
					} else {
						// 赠送金币
						int sendgold = sysConfigsService.getSingle().getSendgold();
						sendgold = sendgold < 1 ? 0 : sendgold;
						user.setGoldbalance(user.getGoldbalance() + sendgold);

						orders.setOrdertype(OrderType.VIP);
						orders.setOrdercost((float) 1000);
					}

					ordersService.add(orders);
					usersService.update(user);
					Logger.getRootLogger().error(String.format("编号为%s用户充值%s成功", user.getUserid(),cost));
					// TODO 对数据库的操作
					response.getWriter().write(setXML("SUCCESS", "")); // 告诉微信服务器，我收到信息了，不要在调用回调action了
				}
			}
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}finally {
			Skin.PayLock.unlock();
		}
	}

	public String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}
}
