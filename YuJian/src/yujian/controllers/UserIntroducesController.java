package yujian.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yujian.common.ResultStatus;
import yujian.common.Skin;
import yujian.common.UserIntroduceStatus;
import yujian.common.UserIntroduceType;
import yujian.models.RespJson;
import yujian.models.UserIntroduces;
import yujian.models.UserIntroducesAndUser;
import yujian.models.Users;
import yujian.service.UserImgsService;
import yujian.service.UserIntroducesService;
import yujian.service.UsersService;
import yujian.utilities.HtmlHelper;

@Controller
@RequestMapping(value = "UserIntroduces")
public class UserIntroducesController {
	@Autowired
	private UserIntroducesService userIntroducesService;
	@Autowired
	private UserImgsService userImgsService;
	@Autowired
	private UsersService usersService;

	/**
	 * 做页面重定向到红娘
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "MyMatchmaker", method = RequestMethod.GET)
	public ModelAndView myMatchmaker(HttpServletRequest request) {
		Users users = (Users) request.getSession().getAttribute(Skin.USER);
		UserIntroducesAndUser matchmaker = userIntroducesService.getSingle(users.getUserid());

		return new ModelAndView("redirect:/UserDetail?type=matchmaker&id=" + matchmaker.getUserid());
	}

	/**
	 * 获取我的红人
	 * 
	 * @param page
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetMyIntroduces", method = RequestMethod.POST)
	public List<UserIntroducesAndUser> getMyIntroduces(int page, int pageSize, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			List<UserIntroducesAndUser> list = userIntroducesService.getListByPage(page, pageSize, null, null,
					UserIntroduceType.RED, users.getUserid(), null);

			for (int i = 0; i < list.size(); i++) {
				list.get(i).setUserimgs(userImgsService.getList(list.get(i).getUiuserid()));
			}
			return list;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取单个红人信息
	 * 
	 * @param uiuserid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetIntroduce", method = RequestMethod.POST)
	public UserIntroducesAndUser getIntroduce(long uiuserid) {
		try {
			UserIntroducesAndUser userIntroducesAndUser = userIntroducesService.getSingle(uiuserid);
			userIntroducesAndUser.setUserimgs(userImgsService.getList(userIntroducesAndUser.getUiuserid()));
			return userIntroducesAndUser;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取所属信息
	 * 
	 * @param uiuserid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetIntroduceOwner", method = RequestMethod.POST)
	public Users getIntroduceOwner(long uiuserid) {
		try {
			UserIntroducesAndUser userIntroducesAndUser = userIntroducesService.getSingle(uiuserid);
			Users users = usersService.getSingleByUserID(userIntroducesAndUser.getUserid());
			users.setPassword("");
			users.setGoldbalance((float) 0);

			return users;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 修改红人信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateIntroduce", method = RequestMethod.POST)
	public RespJson updateIntroduce(Users model, long uiuserid, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		try {
			Users temp = (Users) request.getSession().getAttribute(Skin.USER);
			UserIntroducesAndUser userIntroducesAndUser = userIntroducesService.getSingle(uiuserid);
			if (userIntroducesAndUser.getUserid() != temp.getUserid()) {
				respJson.setStatus(ResultStatus.USERDEFINED);
				respJson.setMsg(Skin.TipMap.get("modifyredfail"));
				return respJson;
			}

			Users users = usersService.getSingleByUserID(uiuserid);
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

			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg(Skin.TipMap.get("modifyredsuccess"));
			return respJson;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg(Skin.TipMap.get("modifyredfail"));

			return respJson;
		}
	}

	/**
	 * 修改择偶条件
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateSelectCondtion", method = RequestMethod.POST)
	public RespJson updateSelectCondtion(long uiuserid, String selectcondition, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		try {
			Users temp = (Users) request.getSession().getAttribute(Skin.USER);
			UserIntroducesAndUser userIntroducesAndUser = userIntroducesService.getSingle(uiuserid);
			if (userIntroducesAndUser.getUserid() != temp.getUserid()) {
				respJson.setStatus(ResultStatus.USERDEFINED);
				respJson.setMsg(Skin.TipMap.get("modifyredfail"));
				return respJson;
			}
			Users users = usersService.getSingleByUserID(uiuserid);
			users.setSelectcondition(selectcondition);

			usersService.update(users);

			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg(Skin.TipMap.get("modifyredsuccess"));
			return respJson;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg(Skin.TipMap.get("modifyredfail"));

			return respJson;
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
	public RespJson updateSignContent(String signcontent, long uiuserid, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		try {
			Users temp = (Users) request.getSession().getAttribute(Skin.USER);
			UserIntroducesAndUser userIntroducesAndUser = userIntroducesService.getSingle(uiuserid);
			if (userIntroducesAndUser.getUserid() != temp.getUserid()) {
				respJson.setStatus(ResultStatus.USERDEFINED);
				respJson.setMsg(Skin.TipMap.get("modifyredfail"));
				return respJson;
			}
			Users users = usersService.getSingleByUserID(uiuserid);
			users.setSigncontent(signcontent);

			usersService.update(users);
			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg(Skin.TipMap.get("modifyredsuccess"));
			return respJson;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg(Skin.TipMap.get("modifyredfail"));

			return respJson;
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
	public RespJson updateHobby(String hobby, long uiuserid, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		try {
			Users temp = (Users) request.getSession().getAttribute(Skin.USER);
			UserIntroducesAndUser userIntroducesAndUser = userIntroducesService.getSingle(uiuserid);
			if (userIntroducesAndUser.getUserid() != temp.getUserid()) {
				respJson.setStatus(ResultStatus.USERDEFINED);
				respJson.setMsg(Skin.TipMap.get("modifyredfail"));
				return respJson;
			}
			Users users = usersService.getSingleByUserID(uiuserid);
			users.setHobby(hobby);

			usersService.update(users);
			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg(Skin.TipMap.get("modifyredsuccess"));
			return respJson;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg(Skin.TipMap.get("modifyredfail"));

			return respJson;
		}
	}

	/**
	 * 请求审核
	 * 
	 * @param uiuserid
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "RequestCheck", method = RequestMethod.GET)
	public void requestCheck(long uiuserid, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);

			UserIntroduces model = new UserIntroduces();
			model.setUiuserid(uiuserid);
			model.setUserid(users.getUserid());
			model.setUserintroducestatus(UserIntroduceStatus.UNPASS);

			userIntroducesService.update(model);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}
	}
}
