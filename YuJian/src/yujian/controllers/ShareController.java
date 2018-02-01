package yujian.controllers;

import java.security.MessageDigest;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import weixin.bll.WeiXinPayer;
import weixin.bll.WeiXinSharer;

@Controller
@RequestMapping(value = "Share")
public class ShareController {

	/**
	 * 获取分享 配置信息
	 * 
	 * @param jsapi_ticket
	 * @param url
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetShareConfig", method = RequestMethod.GET)
	public SortedMap<Object, Object> getShareConfig(HttpServletRequest request, String url) {
		try {
			if (url != null){
				url = url.replace("amp;", "&");
			}
			WeiXinPayer weiXinPayer = new WeiXinPayer();
			WeiXinSharer weiXinSharer = new WeiXinSharer();
			String jsapi_ticket = weiXinSharer.getTiket();
			SortedMap<Object, Object> map = new TreeMap<Object, Object>();

			String noncestr = weiXinPayer.create_nonce_str();
			String timestamp = weiXinPayer.create_timestamp();

			map.put("noncestr", noncestr);
			map.put("timestamp", timestamp);
			map.put("appId", weiXinPayer.getConfig().getAppID());
			map.put("url", url);
			map.put("jsapi_ticket", jsapi_ticket);

			String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
					+ url;
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(str.getBytes("UTF-8"));
			String signature = weiXinSharer.byteToHex(crypt.digest());
			map.put("signature", signature);

			return map;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}
}
