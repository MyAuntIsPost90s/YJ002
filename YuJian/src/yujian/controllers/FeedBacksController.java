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

import yujian.common.FeedBackResult;
import yujian.common.FeedBackStatus;
import yujian.common.FeedBackType;
import yujian.common.RandomNum;
import yujian.common.ResultStatus;
import yujian.common.Skin;
import yujian.models.FeedBacks;
import yujian.models.FeedBacksAndUser;
import yujian.models.RespJson;
import yujian.models.Users;
import yujian.service.FeedBacksService;
import yujian.utilities.HtmlHelper;

@Controller
@RequestMapping(value = "FeedBacks")
public class FeedBacksController {
	@Autowired
	private FeedBacksService feedBacksService;

	/**
	 * 提交反馈
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Add", method = RequestMethod.POST)
	public RespJson add(FeedBacks model, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			model.setUserid(users.getUserid());
			model.setFeedbackstatus(FeedBackStatus.NO);
			model.setFeedbackid(RandomNum.getFeedBackID());
			model.setFeedbacktime(new Date());
			model.setFeedbackcontent(HtmlHelper.delHTMLTag(model.getFeedbackcontent()));
			model.setFeedbackresult(FeedBackResult.UNDEFINE);

			if (model.getFeedbacktype() == FeedBackType.ASKMATCHMAKER) {
				List<FeedBacksAndUser> list = feedBacksService.getListByIDAndType(users.getUserid(),
						FeedBackType.ASKMATCHMAKER, FeedBackStatus.NO);
				if (list != null && list.size() > 0) {
					respJson.setStatus(ResultStatus.FAIL);
					respJson.setMsg(Skin.TipMap.get("feedbacksuccess"));
					return respJson;
				}
			}

			feedBacksService.add(model);

			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg(Skin.TipMap.get("feedbacksuccess"));
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg(Skin.TipMap.get("feedbackfail"));
		}

		return respJson;
	}
}
