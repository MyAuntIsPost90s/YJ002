package yujian.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.common.Skin;
import yujian.models.ExpressionBags;
import yujian.models.Users;
import yujian.service.ExpressionBagsService;

@Controller
@RequestMapping(value = "ExpressionBags")
public class ExpressionBagsController {
	@Autowired
	private ExpressionBagsService expressionBagsService;

	@ResponseBody
	@RequestMapping(value = "GetExpressionBagsViews", method = RequestMethod.POST)
	public List<ExpressionBags> getExpressionBagsViews(int page, int pageSize, HttpServletRequest request) {
		try {
			Users user = (Users) request.getSession().getAttribute(Skin.USER);
			return expressionBagsService.getExpressionBagsNoUser(page, pageSize, user.getUserid());
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "GetExpressionBag", method = RequestMethod.POST)
	public ExpressionBags getExpressionBag(String expressionbagid) {
		try {
			return expressionBagsService.getSingle(expressionbagid);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}
}
