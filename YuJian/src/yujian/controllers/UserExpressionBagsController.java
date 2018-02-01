package yujian.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.common.OrderType;
import yujian.common.RandomNum;
import yujian.common.ResultStatus;
import yujian.common.Skin;
import yujian.models.ExpressionBags;
import yujian.models.Orders;
import yujian.models.RespJson;
import yujian.models.UserExpressionBags;
import yujian.models.Users;
import yujian.service.ExpressionBagsService;
import yujian.service.OrdersService;
import yujian.service.UserExpressionBagsService;
import yujian.service.UsersService;

@Controller
@RequestMapping("UserExpressionBags")
public class UserExpressionBagsController {
	@Autowired
	private UserExpressionBagsService userExpressionBagsService;
	@Autowired
	private ExpressionBagsService expressionBagsService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private OrdersService ordersService;

	/**
	 * 获取我的表情包
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetMyExpressionBags", method = RequestMethod.POST)
	public List<ExpressionBags> getMyExpressionBags(HttpServletRequest request) {
		try {
			Users user = (Users) request.getSession().getAttribute(Skin.USER);
			return userExpressionBagsService.getList(user.getUserid());
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	@ResponseBody
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "BuyExpressionBags", method = RequestMethod.POST)
	public RespJson buyExpressionBags(UserExpressionBags model, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		Skin.PayLock.lock();
		try {
			Users user = (Users) request.getSession().getAttribute(Skin.USER);
			ExpressionBags expressionBags = expressionBagsService.getSingle(model.getExpressionbagid());
			// 判断金币是否足够
			if (user.getGoldbalance() >= expressionBags.getEbcost()) {
				model.setUserid(user.getUserid());
				user.setGoldbalance(user.getGoldbalance() - expressionBags.getEbcost());

				// 生成订单
				Orders orders = new Orders();
				orders.setOrdercost(expressionBags.getEbcost() / 10);
				orders.setOrderid(RandomNum.getOrderID());
				orders.setOrderdate(new Date());
				orders.setOrdertype(OrderType.EXPRESSION);
				orders.setUserid(user.getUserid());

				userExpressionBagsService.add(model); // 先add
				ordersService.add(orders); // 加入订单
				usersService.update(user); // 后update

				request.getSession().setAttribute(Skin.USER, user);

				respJson.setStatus(ResultStatus.SUCCESS);
				respJson.setMsg(Skin.TipMap.get("buyexpresssuccess"));
			} else {
				respJson.setJsonData(ResultStatus.FAIL);
				respJson.setMsg(Skin.TipMap.get("buyexpressless") + "<br/><a href='/YuJian/Recharge'>立即充值<a>");
			}
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setJsonData(ResultStatus.FAIL);
			respJson.setMsg(Skin.TipMap.get("buyexpressfail"));
		}
		Skin.PayLock.unlock();
		return respJson;
	}
}
