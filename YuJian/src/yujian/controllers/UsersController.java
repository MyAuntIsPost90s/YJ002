package yujian.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.common.Skin;
import yujian.common.UserType;
import yujian.models.Adverts;
import yujian.models.SysConfigs;
import yujian.models.Users;
import yujian.models.UsersSortView;
import yujian.models.UsersView;
import yujian.service.AdvertsService;
import yujian.service.SysConfigsService;
import yujian.service.UsersService;
import yujian.utilities.HtmlHelper;

@Controller
@RequestMapping(value = "Users")
public class UsersController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private SysConfigsService sysConfigsService;
	@Autowired
	private AdvertsService advertsService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 获取用户
	 * 
	 * @param usertype
	 * @param page
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetUsers", method = RequestMethod.POST)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Map<String, Object> getUsers(String address, Date btime, Date etime, Integer sex, String height,
			Integer usertype, String wage, String record, String bloodtype, Integer otherfunction, String condition,
			String loadKey, int page, int pagesize, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			List<UsersView> list = usersService.getListByPage(page, pagesize, address, btime, etime,
					sex == null ? -1 : sex, wage, record, height, bloodtype, usertype, users.getUserid(),
					otherfunction < 0 ? null : otherfunction, condition);
			// 获取配置信息
			SysConfigs sysConfigs = sysConfigsService.getSingle();
			if (sysConfigs == null)
				return null;

			// 获取广告
			int num = pagesize / sysConfigs.getIntervalnum();
			List<Adverts> aList = advertsService.getListByPage(page, num);

			if (aList != null && aList.size() > 0 && list != null && list.size() > sysConfigs.getIntervalnum()) {
				for (int i = 0; i < aList.size(); i++) {
					if ((i + 1) * sysConfigs.getIntervalnum() + i > list.size()) {
						break;
					}
					UsersView temp = new UsersView();
					temp.setRealname(aList.get(i).getAdverttitle());
					temp.setHeadimgurl(aList.get(i).getAdvertlogourl());
					temp.setVideourl(aList.get(i).getAdvertimgurl());
					temp.setSigncontent(aList.get(i).getAdvertcontent());
					temp.setPhone(aList.get(i).getAdvertlink());
					temp.setUsertype(UserType.ADMIN + 99);
					list.add((i + 1) * sysConfigs.getIntervalnum() + i, temp);
				}
			}

			for (Users item : list) {
				item.setPassword("");
			}
			Map<String, Object> map = new HashMap<>();
			map.put("list", list);
			map.put("key", loadKey);
			return map;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return new HashMap<>();
		}
	}

	/**
	 * 搜索用户
	 * 
	 * @param condition
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "SearchUsers", method = RequestMethod.POST)
	public List<Users> searchUsers(String condition, int page, int pagesize) {
		try {
			List<Users> list = usersService.searchListByPage(condition, page, pagesize);
			for (Users item : list) {
				item.setPassword("");
			}
			return list;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return new ArrayList<Users>();
		}
	}

	/**
	 * 获取个人信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetMyInformation", method = RequestMethod.POST)
	public Users getMyInformation(HttpServletRequest request) {
		try {
			return (Users) request.getSession().getAttribute(Skin.USER);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 重新加载个人信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ReloadUser", method = RequestMethod.GET)
	public Users reloadUser(HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			users = usersService.getSingleByUserID(users.getUserid());
			request.getSession().setAttribute(Skin.USER, users);
			return users;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 修改我的信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateMe", method = RequestMethod.POST)
	public Users updateMe(Users model, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);

			if (model.getRealname() != null)
				users.setRealname(HtmlHelper.delHTMLTag(model.getRealname()));
			users.setSex(model.getSex());
			if (model.getWeixinnum() != null)
				users.setWeixinnum(model.getWeixinnum());
			if (model.getQqnum() != null)
				users.setQqnum(model.getQqnum());
			if (model.getPhone() != null)
				users.setPhone(model.getPhone());
			if (model.getBirthday() != null)
				users.setBirthday(model.getBirthday());
			if (model.getAddress() != null)
				users.setAddress(model.getAddress());
			if (model.getHeadimgurl() != null)
				users.setHeight(model.getHeight());
			if (model.getRecord() != null)
				users.setRecord(model.getRecord());
			if (model.getOccupation() != null)
				users.setOccupation(HtmlHelper.delHTMLTag(model.getOccupation()));
			if (model.getBloodtype() != null)
				users.setBloodtype(model.getBloodtype());
			if (model.getWage() != null)
				users.setWage(model.getWage());

			usersService.update(users);
			// 刷新session
			request.getSession().setAttribute(Skin.USER, users);
			return users;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 修改我的择偶条件
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateSelectCondtion", method = RequestMethod.POST)
	public Users updateSelectCondtion(String selectcondition, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			users.setSelectcondition(selectcondition);

			usersService.update(users);
			// 刷新session
			request.getSession().setAttribute(Skin.USER, users);
			return users;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 修改我的签名
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateSignContent", method = RequestMethod.POST)
	public Users updateSignContent(String signcontent, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			users.setSigncontent(signcontent);

			usersService.update(users);
			// 刷新session
			request.getSession().setAttribute(Skin.USER, users);
			return users;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 修改红娘简介
	 * 
	 * @param matchmakerintroduct
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateMatchmakerIntroduct", method = RequestMethod.POST)
	public Users updateMatchmakerIntroduct(String matchmakerintroduct, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			users.setMatchmakerintroduct(matchmakerintroduct);

			usersService.update(users);
			// 刷新session
			request.getSession().setAttribute(Skin.USER, users);
			return users;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 修改红娘擅长
	 * 
	 * @param matchmakergoodat
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateMatchmakerGoodAt", method = RequestMethod.POST)
	public Users updateMatchmakerGoodAt(String matchmakergoodat, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			users.setMatchmakergoodat(matchmakergoodat);

			usersService.update(users);
			// 刷新session
			request.getSession().setAttribute(Skin.USER, users);
			return users;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 修改用户兴趣
	 * 
	 * @param hobby
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateHobby", method = RequestMethod.POST)
	public Users updateHobby(String hobby, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			users.setHobby(hobby);

			usersService.update(users);
			// 刷新session
			request.getSession().setAttribute(Skin.USER, users);
			return users;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 修改红娘建议
	 * 
	 * @param matchmakeradvise
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateMatchmakerAdvise", method = RequestMethod.POST)
	public Users updateMatchmakerAdvise(String matchmakeradvise, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			users.setMatchmakeradvise(matchmakeradvise);

			usersService.update(users);
			// 刷新session
			request.getSession().setAttribute(Skin.USER, users);
			return users;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取排序通过热度
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetUsersByHotCount", method = RequestMethod.POST)
	public List<UsersSortView> getUsersByHotCount(int page, int pageSize) {
		try {
			return usersService.getListByHotCount(page, pageSize);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取排序通过财富值
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetUsersByRiches", method = RequestMethod.POST)
	public List<UsersSortView> getUsersByRiches(int page, int pageSize) {
		try {
			return usersService.getListByRiches(page, pageSize);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取我的财富位置
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetMyRichesIndex", method = RequestMethod.POST)
	public UsersSortView getMyRichesIndex(HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			return usersService.getMyRichesIndex(users.getUserid());
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取我点热门位置
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetMyHotCountIndex", method = RequestMethod.POST)
	public UsersSortView getMyHotCountIndex(HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			return usersService.getMyHotCountIndex(users.getUserid());
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取其他用户信息
	 * 
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetOtherUserInfo", method = RequestMethod.POST)
	public Users getOtherUserInfo(long userid) {
		try {
			Users model = usersService.getSingleByUserID(userid);
			model.setPassword("");
			model.setGoldbalance((float) 0);

			return model;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}
}
