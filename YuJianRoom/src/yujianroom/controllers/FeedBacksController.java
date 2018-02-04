package yujianroom.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.models.FeedBacksAndUser;
import yujian.models.Users;
import yujian.service.FeedBacksService;
import yujian.service.UsersService;
import yujianroom.common.FeedBackStatus;
import yujianroom.common.OtherFunction;
import yujianroom.common.ResultEasyUIList;
import yujianroom.common.Skin;
import yujianroom.common.UserType;

@Controller
@RequestMapping(value = "FeedBacks", produces = "application/json;charset=utf-8")
public class FeedBacksController {
	@Autowired
	private FeedBacksService feedBacksService;
	@Autowired
	private UsersService usersService;

	/**
	 * 获取集合
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetList", method = RequestMethod.POST)
	public ResultEasyUIList<FeedBacksAndUser> getList(int page, int rows, int feedbacktype, int feedbackstatus
			,String address) {
		ResultEasyUIList<FeedBacksAndUser> result = new ResultEasyUIList<FeedBacksAndUser>();
		try {
			if(address==null||address.isEmpty())
				address=null;
			List<FeedBacksAndUser> list = feedBacksService.getListByPage(page, rows, feedbacktype, feedbackstatus,address);
			long total = feedBacksService.getCount(address,feedbacktype, feedbackstatus);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	/**
	 * 获取未处理反馈总数量
	 * 
	 * @param feedbackstatus
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetUnDoCount", method = RequestMethod.POST)
	public Map<String, Long> getUnDoCount(HttpServletRequest request) {
		try {
			String address=null;
			Users users =(Users)request.getSession().getAttribute(Skin.USER);
			if(users.getUsertype()!=UserType.ROOT){
				address=users.getAddress();
			}
			long confession = feedBacksService.getCount(address,0, 0);// 获取告白数量
			long propose = feedBacksService.getCount(address,1, 0);// 获取求婚数量
			long other = feedBacksService.getCount(address,2, 0);// 获取其他数量
			long feedback = feedBacksService.getCount(address,3, 0);// 获取系统反馈数量
			long askmatchmaker = feedBacksService.getCount(address,4, 0);// 获取申请红娘数量

			Map<String, Long> map = new HashMap<>();
			map.put("confession", confession);
			map.put("propose", propose);
			map.put("other", other);
			map.put("feedback", feedback);
			map.put("askmatchmaker", askmatchmaker);
			map.put("sum", confession + propose + other + feedback + askmatchmaker);
			return map;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 处理回复
	 * 
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "DealWith", method = RequestMethod.POST)
	public String dealWith(String ids) {
		try {
			List<String> strs = Arrays.asList(ids.split(","));
			for (String string : strs) {
				FeedBacksAndUser model = feedBacksService.getSingle(string);
				model.setFeedbackstatus(FeedBackStatus.YES);

				feedBacksService.update(model);
			}
			return "处理成功";
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return "处理失败";
		}
	}

	/**
	 * 请求红娘权限
	 * 
	 * @param feedbackid
	 * @param userid
	 * @param ispass
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "AskMatchmaker", method = RequestMethod.POST)
	public String askMatchmaker(String feedbackid, long userid, int ispass) {
		try {
			Users user = usersService.getSingleByUserID(userid);
			user.setOtherfunction(ispass == 1 ? OtherFunction.MATCHMAKER : OtherFunction.NONE);
			FeedBacksAndUser model = feedBacksService.getSingle(feedbackid);
			model.setFeedbackstatus(FeedBackStatus.YES);
			model.setFeedbackresult(ispass);

			usersService.update(user);
			feedBacksService.update(model);

			return "处理成功";
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return "处理失败";
		}
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Del", method = RequestMethod.POST)
	public String del(String ids) {
		try {
			List<String> list = Arrays.asList(ids.split(","));
			feedBacksService.delete(list);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
		}
	}
}
