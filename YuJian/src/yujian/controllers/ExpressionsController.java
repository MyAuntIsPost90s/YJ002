package yujian.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.models.Expressions;
import yujian.service.ExpressionsService;

@Controller
@RequestMapping(value = "Expressions")
public class ExpressionsController {
	@Autowired
	private ExpressionsService expressionsService;

	/**
	 * 获取表情信息
	 * 
	 * @param expressionbagid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetExpressions")
	public List<Expressions> getExpressions(String expressionbagid) {
		try {
			return expressionsService.getList(expressionbagid);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取表情信息
	 * 
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetExpressionMap")
	public Map<String, List<Expressions>> getExpressionMap(String ids) {
		try {
			List<String> list = Arrays.asList(ids.split(","));
			List<Expressions> eList = expressionsService.getListByEBIDs(list);
			Map<String, List<Expressions>> map = new HashMap<>();

			for (String id : list) {
				if (!map.containsKey(id)) {
					map.put(id, new ArrayList<>());
				}
			}

			for (Expressions e : eList) {
				if (map.containsKey(e.getExpressionbagid())) {
					map.get(e.getExpressionbagid()).add(e);
				}
			}

			return map;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}
}
