package yujian.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.models.Banners;
import yujian.service.BannersService;

@Controller
@RequestMapping(value="Banners")
public class BannersController {
	@Autowired
	private BannersService bannersService;
	
	/**
	 * 获取轮播图集合
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="GetList",method=RequestMethod.POST)
	public List<Banners> getList(Integer bannertype,String address){
		try{
			return bannersService.getList(bannertype,address);
		}catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

}
