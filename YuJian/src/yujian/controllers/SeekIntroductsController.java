package yujian.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.common.RandomNum;
import yujian.common.SeekIntroductStatus;
import yujian.common.SeekIntroductType;
import yujian.common.Skin;
import yujian.models.SeekIntroductAndUser;
import yujian.models.SeekIntroducts;
import yujian.models.UserIntroducesAndUser;
import yujian.models.UserSeekIntroducts;
import yujian.models.Users;
import yujian.service.SeekIntroductsService;
import yujian.service.UserIntroducesService;
import yujian.service.UserSeekIntroductsService;

@Controller
@RequestMapping(value = "SeekIntroducts")
public class SeekIntroductsController {
	@Autowired
	private SeekIntroductsService seekIntroductsService;
	@Autowired
	private UserIntroducesService userIntroducesService;
	@Autowired
	private UserSeekIntroductsService userSeekIntroductsService;

	/**
	 * 获取我的推荐数
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetMySeekIntroductCount", method = RequestMethod.POST)
	public int getMySeekIntroductCount(HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			return seekIntroductsService.getCount(users.getUserid(), SeekIntroductStatus.UNSEE);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return 0;
		}
	}

	/**
	 * 获取求撮合列表
	 * 
	 * @param page
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetMySeekIntroduct", method = RequestMethod.POST)
	public List<SeekIntroductAndUser> getMySeekIntroduct(int page, int pageSize, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			seekIntroductsService.updateStatus(users.getUserid(), SeekIntroductStatus.SEE);
			return seekIntroductsService.getList(page, pageSize, users.getUserid(), null);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "GetMySingleSeekIntroduct", method = RequestMethod.POST)
	public List<SeekIntroductAndUser> getMySingleSeekIntroduct(int page, int pageSize, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			seekIntroductsService.updateStatus(users.getUserid(), SeekIntroductStatus.SEE);
			return seekIntroductsService.getSingleSeekList(page, pageSize, users.getUserid(), null);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 判断是否发送过请求
	 * 
	 * @param touserid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "IsExist", method = RequestMethod.POST)
	public int isExist(long touserid, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			return seekIntroductsService.getSingle(users.getUserid(), touserid, null) == null ? 0 : 1;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return -1;
		}
	}

	/**
	 * 添加
	 * 
	 * @param touserid
	 * @param userid
	 * @param request
	 */
	@ResponseBody
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "Add", method = RequestMethod.POST)
	public void add(long touserid, long userid, int type, HttpServletRequest request) {
		try {
			if (isExist(touserid, request) == 0) {
				Users users = (Users) request.getSession().getAttribute(Skin.USER);
				UserIntroducesAndUser matchmaker = userIntroducesService.getSingle(users.getUserid());
				SeekIntroducts model = new SeekIntroducts();
				model.setSeekintroductid(RandomNum.getSeekIntroductID());
				model.setFromuserid(users.getUserid());
				model.setTouserid(touserid);
				model.setSeekintroductstatus(SeekIntroductStatus.UNSEE);
				model.setUserid(matchmaker.getUserid());
				model.setSeekintroducttime(new Date());
				model.setSeekintroducttype(type);
				if (type == SeekIntroductType.SINGLE) {
					model.setTouserid(-1);
					model.setUserid(touserid);
				}
				seekIntroductsService.add(model);

				if (type == SeekIntroductType.DOUBLE) {
					UserSeekIntroducts userSeekIntroducts = new UserSeekIntroducts();
					userSeekIntroducts.setFromuserid(users.getUserid());
					userSeekIntroducts.setTouserid(touserid);
					userSeekIntroducts.setUserseekintroductid(RandomNum.getSeekIntroductID());
					userSeekIntroducts.setUserseekintroducttime(new Date());
					userSeekIntroductsService.add(userSeekIntroducts);
				}
			}
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}
	}

	/**
	 * 移除
	 * 
	 * @param seekintroductid
	 */
	@ResponseBody
	@RequestMapping(value = "Del", method = RequestMethod.POST)
	public void del(String seekintroductid) {
		try {
			seekIntroductsService.delete(seekintroductid);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}
	}
}
