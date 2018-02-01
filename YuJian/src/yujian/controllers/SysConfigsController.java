package yujian.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.models.SysConfigs;
import yujian.service.SysConfigsService;

@Controller
@RequestMapping(value = "SysConfigs")
public class SysConfigsController {
	@Autowired
	private SysConfigsService sysConfigsService;

	/**
	 * 获取单条信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetSysConfig", method = RequestMethod.GET)
	public SysConfigs getSingle() {
		try {
			return sysConfigsService.getSingle();
		} catch (Exception e) {
			return null;
		}
	}
}
