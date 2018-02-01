package yujianroom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.models.UserLovers;
import yujian.models.UserLovserAndABUser;
import yujian.service.UserLoversService;
import yujian.utilities.ConvertHelper;
import yujianroom.common.ResultEasyUIList;

@Controller
@RequestMapping(value = "UserLovers", produces = "application/json;charset=utf-8")
public class UserLoversController {
	@Autowired
	private UserLoversService userLoversService;

	@ResponseBody
	@RequestMapping(value = "GetList", method = RequestMethod.POST)
	public ResultEasyUIList<UserLovserAndABUser> getList(int page, int rows, String condition) {
		ResultEasyUIList<UserLovserAndABUser> result = new ResultEasyUIList<UserLovserAndABUser>();
		try {
			List<UserLovserAndABUser> list = userLoversService.getListByPage(page, rows, condition);
			long total = userLoversService.getCount(condition);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	/**
	 * 获取单条信息
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetSingle", method = RequestMethod.GET)
	public UserLovserAndABUser getSingle(UserLovers model) {
		try {
			return userLoversService.getSingle(model);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 添加
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Add", method = RequestMethod.POST)
	public String add(UserLovers model) {
		try {
			// 判断重复
			if (userLoversService.getSingle(model) != null)
				return "该成功牵手用户已经存在";
			userLoversService.add(model);
			return "添加成功";
		} catch (Exception e) {
			return "添加失败";
		}
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Update", method = RequestMethod.POST)
	public String update(UserLovers model) {
		try {
			userLoversService.update(model);
			return "修改成功";
		} catch (Exception e) {
			return "修改失败";
		}
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Del", method = RequestMethod.POST)
	public String del(String auserids, String buserids) {
		try {
			List<Long> aids = ConvertHelper.convertToLongs(auserids.split(","));
			List<Long> bids = ConvertHelper.convertToLongs(buserids.split(","));
			for (int i = 0; i < aids.size(); i++) {
				UserLovers model = new UserLovers();
				model.setAuserid(aids.get(i));
				model.setBuserid(bids.get(i));
				userLoversService.delete(model);
			}
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
		}
	}
}
