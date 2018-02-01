package yujianroom.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.models.RespJson;
import yujian.models.UsersSortView;
import yujian.service.UserGiftsService;
import yujianroom.common.ResultStatus;

@Controller
@RequestMapping(value = "UserGifts", produces = "application/json;charset=utf-8")
public class UserGiftsController {
	@Autowired
	private UserGiftsService userGiftsService;

	/**
	 * 获取用户收入合计
	 * 
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetUserIncome", method = RequestMethod.POST)
	public float getUseIncome(long userid) {
		try {
			List<UsersSortView> list = userGiftsService.getUserGiftsRank(userid, 1, 9999);
			float sum = 0;
			for (int i = 0; i < list.size(); i++) {
				sum += list.get(i).getUsersortcount();
			}
			return sum;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return 0;
		}
	}

	/**
	 * 删除用户收入信息
	 * 
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "DelUserIncome", method = RequestMethod.POST)
	public RespJson delUserIncome(long userid) {
		RespJson respJson = new RespJson();
		try {
			userGiftsService.deleteByUserID(userid);

			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg("处理成功");
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg("处理失败");
		}
		return respJson;
	}
}
