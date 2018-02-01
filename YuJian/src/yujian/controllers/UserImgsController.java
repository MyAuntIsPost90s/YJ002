package yujian.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.common.Skin;
import yujian.models.UserImgs;
import yujian.models.Users;
import yujian.service.UserImgsService;

@Controller
@RequestMapping(value = "UserImgs")
public class UserImgsController {
	@Autowired
	private UserImgsService userImgsService;

	/**
	 * 获取我的照片
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetMyImgs", method = RequestMethod.POST)
	public List<UserImgs> getMyImgs(HttpServletRequest request) {
		try {
			Users user = (Users) request.getSession().getAttribute(Skin.USER);
			return userImgsService.getList(user.getUserid());
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取用户照片
	 * 
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetUserImgs", method = RequestMethod.POST)
	public List<UserImgs> getUserImgs(long userid, HttpServletRequest request) {
		try {
			return userImgsService.getList(userid);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}
}
