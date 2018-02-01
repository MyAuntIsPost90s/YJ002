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
import yujian.models.UserCollectionAndUser;
import yujian.models.UserCollections;
import yujian.models.Users;
import yujian.service.UserCollectionsService;

@Controller
@RequestMapping(value = "UserCollections")
public class UserCollectionsController {
	@Autowired
	private UserCollectionsService userCollectionsService;

	/**
	 * 收集
	 * 
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = "Collect", method = RequestMethod.POST)
	public void collect(UserCollections model, HttpServletRequest request) {
		try {
			Users user = (Users) request.getSession().getAttribute(Skin.USER);
			model.setUserid(user.getUserid());
			userCollectionsService.add(model);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}
	}

	/**
	 * 取消收藏
	 * 
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = "UnCollect", method = RequestMethod.POST)
	public void unCollect(UserCollections model, HttpServletRequest request) {
		try {
			Users user = (Users) request.getSession().getAttribute(Skin.USER);
			model.setUserid(user.getUserid());
			userCollectionsService.delete(model);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}
	}

	/**
	 * 获取我的收藏
	 * 
	 * @param page
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetMyCollectUser", method = RequestMethod.POST)
	public List<UserCollectionAndUser> getMyCollectUser(int page, int pageSize, HttpServletRequest request) {
		try {
			Users user = (Users) request.getSession().getAttribute(Skin.USER);
			List<UserCollectionAndUser> list = userCollectionsService.getListByPage(page, pageSize, user.getUserid());
			return list;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}
}
