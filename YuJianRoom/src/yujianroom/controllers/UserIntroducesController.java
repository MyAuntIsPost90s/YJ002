package yujianroom.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.models.UserIntroduces;
import yujian.models.UserIntroducesAndUser;
import yujian.models.Users;
import yujian.service.UserImgsService;
import yujian.service.UserIntroducesService;
import yujian.service.UsersService;
import yujian.utilities.ConvertHelper;
import yujian.utilities.WebMapHelper;
import yujianroom.common.ResultEasyUIList;
import yujianroom.common.Skin;
import yujianroom.common.UserIntroduceStatus;
import yujianroom.common.UserIntroduceType;

@Controller
@RequestMapping(value = "UserIntroduces", produces = "application/json;charset=utf-8")
public class UserIntroducesController {
	@Autowired
	private UserIntroducesService userIntroducesService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private UserImgsService userImgsService;

	/**
	 * 获取红人
	 * 
	 * @param page
	 * @param rows
	 * @param condition
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetList", method = RequestMethod.POST)
	public ResultEasyUIList<UserIntroducesAndUser> getList(int page, int rows, Integer userintroducestatus,
			String condition, String address) {
		ResultEasyUIList<UserIntroducesAndUser> result = new ResultEasyUIList<>();
		try {
			List<UserIntroducesAndUser> list = userIntroducesService.getListByPage(page, rows, condition,
					userintroducestatus, UserIntroduceType.RED, null, address);
			long total = userIntroducesService.getCount(condition, userintroducestatus, UserIntroduceType.RED, address);
			result.setTotal(total);
			result.setRows(list);
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	/**
	 * 获取红人数量
	 * 
	 * @param userintroducestatus
	 * @param condition
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetCount", method = RequestMethod.POST)
	public long getCount(Integer userintroducestatus, String condition,String address) {
		try {
			long count = userIntroducesService.getCount(condition, userintroducestatus, UserIntroduceType.RED, address);
			return count;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 批量添加
	 * 
	 * @param ids
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "AddMore", method = RequestMethod.POST)
	public String addMore(String ids, long userid) {
		try {
			List<UserIntroduces> list = new ArrayList<>();
			List<Long> longs = ConvertHelper.convertToLongs(ids.split(","));
			for (int i = 0; i < longs.size(); i++) {
				UserIntroduces model = new UserIntroduces();
				model.setUiuserid(longs.get(i));
				model.setUserid(userid);
				model.setUserintroducestatus(UserIntroduceStatus.PASS);
				model.setUserintroducetype(UserIntroduceType.USER);
				list.add(model);
			}

			userIntroducesService.deleteMore(list);
			userIntroducesService.addMore(list);
			return "分配成功";
		} catch (Exception e) {
			return "分配失败";
		}
	}

	/**
	 * 修改红人状态
	 * 
	 * @param uiids
	 * @param userids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateStatus", method = RequestMethod.POST)
	public String updateStatus(String uiids, String userids) {
		try {
			List<Long> uiList = ConvertHelper.convertToLongs(uiids.split(","));
			List<Long> uList = ConvertHelper.convertToLongs(userids.split(","));

			for (int i = 0; i < uiList.size(); i++) {
				UserIntroduces model = new UserIntroduces();
				model.setUiuserid(uiList.get(i));
				model.setUserid(uList.get(i));
				model.setUserintroducestatus(UserIntroduceStatus.PASS);
				userIntroducesService.update(model);
			}
			return "处理成功";
		} catch (Exception e) {
			return "处理失败";
		}
	}

	/**
	 * 修改用户排序号
	 * 
	 * @param userid
	 * @param usersortindex
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateSortIndex", method = RequestMethod.POST)
	public String updateSortIndex(long userid, int usersortindex) {
		try {
			Users users = usersService.getSingleByUserID(userid);
			users.setUsersortindex(usersortindex);
			usersService.update(users);

			return "处理成功";
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return "处理失败";
		}
	}

	/**
	 * 删除红人
	 * 
	 * @param uiids
	 * @param userids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Del", method = RequestMethod.POST)
	public String del(String uiids, String userids) {
		try {
			List<Long> uiList = ConvertHelper.convertToLongs(uiids.split(","));
			List<Long> uList = ConvertHelper.convertToLongs(userids.split(","));

			for (int i = 0; i < uiList.size(); i++) {
				UserIntroduces model = new UserIntroduces();
				model.setUiuserid(uiList.get(i));
				model.setUserid(uList.get(i));
				model.setUserintroducestatus(UserIntroduceStatus.PASS);
				userIntroducesService.delete(model);
			}

			// 删除用户
			for (Long id : uiList) {
				userImgsService.deleteByUserID(id, WebMapHelper.getWebRoot() + Skin.REAL_PHOTO_PATH);
			}
			// 删除图片
			usersService.delete(uiList, WebMapHelper.getWebRoot() + Skin.REAL_HEADIMG_PATH);

			return "处理成功";
		} catch (Exception e) {
			return "处理失败";
		}
	}
}
