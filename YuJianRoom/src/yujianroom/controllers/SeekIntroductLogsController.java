package yujianroom.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.models.SeekIntroductLogs;
import yujian.models.SeekIntroductLogsAndUser;
import yujian.service.SeekIntroductLogsService;
import yujianroom.common.RandomNum;
import yujianroom.common.ResultEasyUIList;

@Controller
@RequestMapping(value = "SeekIntroductLogs", produces = "application/json;charset=utf-8")
public class SeekIntroductLogsController {
	@Autowired
	private SeekIntroductLogsService seekIntroductLogsService;

	@ResponseBody
	@RequestMapping(value = "GetList", method = RequestMethod.POST)
	public ResultEasyUIList<SeekIntroductLogsAndUser> getList(int page, int rows, Integer userid, Integer relativeid) {
		ResultEasyUIList<SeekIntroductLogsAndUser> result = new ResultEasyUIList<SeekIntroductLogsAndUser>();
		try {
			List<SeekIntroductLogsAndUser> list = seekIntroductLogsService.getList(page, rows, userid, relativeid);
			long total = seekIntroductLogsService.getCount(userid, relativeid);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	/**
	 * 获取单条信息
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetSingle", method = RequestMethod.GET)
	public SeekIntroductLogsAndUser getSingle(String seekintroductlogid) {
		try {
			return seekIntroductLogsService.getSingle(seekintroductlogid);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 添加
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Add", method = RequestMethod.POST)
	public String add(SeekIntroductLogs model) {
		try {
			model.setSeekintroductlogid(RandomNum.getLogID());
			seekIntroductLogsService.add(model);
			return "添加成功";
		} catch (Exception e) {
			return "添加失败";
		}
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Update", method = RequestMethod.POST)
	public String update(SeekIntroductLogs model) {
		try {
			seekIntroductLogsService.update(model);
			return "修改成功";
		} catch (Exception e) {
			return "修改失败";
		}
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Del", method = RequestMethod.POST)
	public String del(String ids) {
		try {
			List<String> strs = Arrays.asList(ids.split(","));
			seekIntroductLogsService.delete(strs);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
		}
	}
}
