package yujianroom.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorPageController {
	Logger log = LogManager.getLogger(ErrorPageController.class);

	@RequestMapping("404")
	public ModelAndView page404() {
		ModelAndView modelAndView = new ModelAndView("err");
		modelAndView.addObject("msg", "404页面走丢了");
		return modelAndView;
	}

	@RequestMapping("500")
	public ModelAndView page500() {
		log.error(500);
		ModelAndView modelAndView = new ModelAndView("err");
		modelAndView.addObject("msg", "T^T内部错误~~~");
		return modelAndView;
	}
}
