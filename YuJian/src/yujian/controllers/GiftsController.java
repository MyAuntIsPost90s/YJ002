package yujian.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.models.Gifts;
import yujian.service.GiftsService;

@Controller
@RequestMapping("Gifts")
public class GiftsController {
	@Autowired
	private GiftsService giftsService;

	/**
	 * 获取商城礼物
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetGifts", method = RequestMethod.POST)
	public List<Gifts> getGifts(int page, int pageSize) {
		try {
			return giftsService.getListByPage(page, pageSize);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}
}
