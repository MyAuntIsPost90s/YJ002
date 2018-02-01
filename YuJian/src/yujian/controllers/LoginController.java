package yujian.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import weixin.bll.WeiXinLoginer;
import weixin.model.WeiXinToken;
import weixin.model.WeiXinUser;
import yujian.common.MatchmakerType;
import yujian.common.OtherFunction;
import yujian.common.Skin;
import yujian.common.UserIntroduceStatus;
import yujian.common.UserIntroduceType;
import yujian.common.UserType;
import yujian.models.UserIntroduces;
import yujian.models.Users;
import yujian.service.UserIntroducesService;
import yujian.service.UsersService;
import yujian.utilities.DESHelper;

@Controller
public class LoginController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private UserIntroducesService uiservice;

	@RequestMapping(value = "WeiXinLogin")
	public void weiXinLogin(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		try {
			response.sendRedirect(new WeiXinLoginer().getCodeUrl());
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}
	}

	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "WXLoginRedirect")
	public ModelAndView wxLoginRedirect(String code, String state, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (code == null)
				return new ModelAndView("redirect:/LoginErr");

			WeiXinLoginer weiXinLoginer = new WeiXinLoginer();
			WeiXinToken token = weiXinLoginer.getToken(code);
			if (token == null) {
				Logger.getRootLogger().error("我们的网站被CSRF攻击了或者用户取消了授权，OtherLoginController:130行");
				return new ModelAndView("redirect:/LoginErr");
			} else {
				WeiXinUser weiXinUser = weiXinLoginer.getUser(token.getAccess_token(), token.getOpenid());
				if (weiXinUser == null) {
					Logger.getRootLogger().error("get weixinuser error");
					return new ModelAndView("redirect:/LoginErr");
				}
				request.getSession().setAttribute("weixin_access_token", token);

				// 判断是否为第一次weixin登陆
				Users user = usersService.getSingleByWXOID(weiXinUser.getOpenid());
				if (user == null) {
					user = addClientUser(weiXinUser, user);// 第一次登陆需要添加客户端用户
				}

				request.getSession().setAttribute(Skin.USER, user);
				// 写入cookie
				Cookie cookie = new Cookie(Skin.USER, DESHelper.encode(Skin.DESKEY, user.getUserid() + ""));
				cookie.setMaxAge(60 * 60 * 24 * 30); // 保存一个月
				cookie.setPath("/");
				response.addCookie(cookie);

				if (user.getHeadimgurl() == null || user.getHeadimgurl().isEmpty())
					return new ModelAndView("redirect:/RegisterHeadImg");
				else if (user.getRealname() == null || user.getRealname().isEmpty())
					return new ModelAndView("redirect:/RegisterBase");
				else
					return new ModelAndView("redirect:/CommonUsers");
			}
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return new ModelAndView("redirect:/LoginErr");
		}
	}

	private Users addClientUser(WeiXinUser weiXinUser, Users user) {
		// 利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息
		user = new Users();
		user.setUsertype(UserType.NOMAL);
		user.setPassword(weiXinUser.getUnionid() == null ? "" : weiXinUser.getUnionid());
		user.setSex(weiXinUser.getSex().equals("男") ? 1 : 0);
		user.setBirthday(new Date());
		user.setCreatetime(new Date());
		user.setWeixinuid(weiXinUser.getUnionid() == null ? "" : weiXinUser.getUnionid());
		user.setWeixinoid(weiXinUser.getOpenid());
		user.setOtherfunction(OtherFunction.NONE);
		user.setPassword(weiXinUser.getOpenid());

		Skin.AddUserLock.lock();
		usersService.add(user);
		Skin.AddUserLock.unlock();

		user = usersService.getSingleByWXOID(weiXinUser.getOpenid());

		Users defaultuser = usersService.getSingleByMatchmakerType(MatchmakerType.DEFAULT);
		// 配置默认红娘
		UserIntroduces uIntroduces = new UserIntroduces();
		uIntroduces.setUiuserid(user.getUserid());
		uIntroduces.setUserid(defaultuser.getUserid());
		uIntroduces.setUserintroducestatus(UserIntroduceStatus.PASS);
		uIntroduces.setUserintroducetype(UserIntroduceType.USER);

		List<UserIntroduces> list = new ArrayList<>();
		list.add(uIntroduces);
		uiservice.deleteMore(list);
		uiservice.add(uIntroduces);

		return user;
	}
}
