package yujianroom.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yujian.models.RespJson;
import yujian.models.Users;
import yujian.service.UsersService;
import yujian.utilities.DESHelper;
import yujianroom.common.ResultStatus;
import yujianroom.common.Skin;
import yujianroom.common.UserType;

@Controller
public class LoginController {
	@Autowired
	private UsersService userService;

	@RequestMapping(value = "Login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/LoginOut", method = RequestMethod.GET)
	public void loginOut(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.getSession().setAttribute(Skin.USER, null);
			if (request.getCookies() != null) {
				for (Cookie cookie : request.getCookies()) {
					if (cookie.getName().equals(Skin.USER)) {
						Cookie c = new Cookie(Skin.USER, 0 + "");
						c.setPath("/");
						c.setMaxAge(0);
						response.addCookie(c);
						break;
					}
				}
			}

			response.sendRedirect("/YuJianRoom/Login");
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}
	}

	/**
	 * 登陆
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/DoLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public RespJson doLogin(String phone, String password, boolean isSave, HttpServletRequest request,
			HttpServletResponse response) {
		RespJson respJson = new RespJson();
		try {
			Users user = userService.getSingleByPhone(phone);
			if (user == null || user.getUsertype() != UserType.ADMIN) {
				respJson.setStatus(ResultStatus.USERDEFINED);
				respJson.setMsg("该用户不存在");
				return respJson;
			}
			if (!user.getPassword().equals(password)) {
				respJson.setStatus(ResultStatus.USERDEFINED);
				respJson.setMsg("用户名或密码错误");
				return respJson;
			}

			// 写入session
			request.getSession().setAttribute(Skin.USER, user);

			// 如果选择记住我则写入cookie
			if (isSave) {
				System.out.println(DESHelper.encode(Skin.DESKEY, user.getUserid() + ""));
				Cookie cookie = new Cookie(Skin.USER, DESHelper.encode(Skin.DESKEY, user.getUserid() + ""));
				cookie.setMaxAge(60 * 60 * 24 * 30); // 保存一个月
				cookie.setPath("/");
				response.addCookie(cookie);
			}

			// 返回成功
			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg("登陆成功");
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg("登陆失败");
		}
		return respJson;
	}
}
