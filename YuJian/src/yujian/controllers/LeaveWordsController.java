package yujian.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yujian.common.RandomNum;
import yujian.common.Skin;
import yujian.models.LeaveWords;
import yujian.models.LeaveWordsAndUser;
import yujian.models.Users;
import yujian.service.LeaveWordsService;
import yujian.service.UsersService;

@Controller
@RequestMapping(value = "LeaveWords")
public class LeaveWordsController {
	@Autowired
	private LeaveWordsService leaveWordsService;
	@Autowired
	private UsersService usersService;

	@ResponseBody
	@RequestMapping(value = "GetUserLeaveWords", method = RequestMethod.POST)
	public List<LeaveWordsAndUser> getUserLeaveWords(long userid, int page, int pageSize) {
		try {
			List<LeaveWordsAndUser> list = leaveWordsService.getLeaveWordsAndUserByPage(userid, "", page, pageSize); // 获取主列表
			return list;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "GetUserLeaveWordsByParentId", method = RequestMethod.POST)
	public List<LeaveWordsAndUser> getUserLeaveWordsByParentId(String parentid, int page, int pageSize) {
		try {
			List<LeaveWordsAndUser> list = leaveWordsService.getLeaveWordsAndUserByParentId(parentid, page, pageSize); // 获取单项列表
			return list;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}
	
	/**
	 * 获取单条信息
	 * @param leavewordid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetSingle", method = RequestMethod.POST)
	public LeaveWordsAndUser getSingle(String leavewordid) {
		try {
			return leaveWordsService.getSingle(leavewordid); // 获取单项列表
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 给用户留言
	 * 
	 * @param touserid
	 * @param expressionurl
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "AddLeaveWord", method = RequestMethod.GET)
	public ModelAndView addLeaveWord(long touserid, String expressionurl, Long toleaveworduserid,
			String parentid,HttpServletRequest request) {
		try {
			Users user = (Users) request.getSession().getAttribute(Skin.USER);

			LeaveWords model = new LeaveWords();
			model.setLeavewordid(RandomNum.getLeaveWordID());
			model.setFromuserid(user.getUserid());
			model.setTouserid(touserid);
			model.setLeavewordtime(new Date());
			model.setLeavewordurl(expressionurl);
			model.setToleaveworduserid(toleaveworduserid == null ? -1 : toleaveworduserid);
			model.setParentid(parentid==null?"":parentid);
			leaveWordsService.add(model);

			// 火热度
			Users toUsers = usersService.getSingleByUserID(model.getTouserid());
			if (toUsers != null) {
				toUsers.setHotcount(toUsers.getHotcount() + 1);
				usersService.update(toUsers);
			}

			return new ModelAndView("redirect:/LeaveWords?id=" + touserid);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return new ModelAndView("redirect:/LeaveWords?id=" + touserid);
		}
	}
}
