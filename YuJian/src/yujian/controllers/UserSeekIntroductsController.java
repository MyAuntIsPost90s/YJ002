package yujian.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.models.UserSeekIntroductsAndUser;
import yujian.service.UserSeekIntroductsService;

@Controller
@RequestMapping("/UserSeekIntroducts")
public class UserSeekIntroductsController {

	@Autowired
	private UserSeekIntroductsService userSeekIntroductsService;

	/**
	 * 获取记录列表
	 * 
	 * @param userid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetList", method = RequestMethod.POST)
	public List<UserSeekIntroductsAndUser> getList(long userid, int page, int pagesize) {
		try {
			List<UserSeekIntroductsAndUser> list = userSeekIntroductsService.getListByPage(page, pagesize, userid);
			return list;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 删除记录
	 * 
	 * @param userseekintroductid
	 */
	@ResponseBody
	@RequestMapping(value = "Delete", method = RequestMethod.POST)
	public void delete(String userseekintroductid) {
		try {
			userSeekIntroductsService.delete(userseekintroductid);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}
	}
}
