package yujianroom.controllers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;
import yujian.models.ExpressionBags;
import yujian.service.ExpressionBagsService;
import yujian.service.ExpressionsService;
import yujian.utilities.FileHelper;
import yujian.utilities.WebMapHelper;
import yujianroom.common.RandomNum;
import yujianroom.common.ResultEasyUIList;
import yujianroom.common.Skin;

@Controller
@RequestMapping(value = "ExpressionBags", produces = "application/json;charset=utf-8")
public class ExpresssionBagsController {
	@Autowired
	private ExpressionBagsService expressionBagsService;
	@Autowired
	private ExpressionsService expressionsService;

	/**
	 * 获取集合
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetList", method = RequestMethod.POST)
	public ResultEasyUIList<ExpressionBags> getList(int page, int rows) {
		ResultEasyUIList<ExpressionBags> result = new ResultEasyUIList<ExpressionBags>();
		try {
			List<ExpressionBags> list = expressionBagsService.getListByPage(page, rows);
			long total = expressionBagsService.getCount();
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetSingle", method = RequestMethod.GET)
	public ExpressionBags getSingle(String id) {
		try {
			return expressionBagsService.getSingle(id);
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
	public String add(ExpressionBags model, HttpServletRequest request) {
		try {
			// 合法校验
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Iterator<String> filenames = mRequest.getFileNames();
			if (!filenames.hasNext()) {
				return "请上传表情包图标";
			}
			MultipartFile file = mRequest.getFile(filenames.next());
			if (file == null || file.getBytes().length < 1)
				return "请上传表情包图标";

			// 保存文件
			String filename = UUID.randomUUID() + ".png";
			String path = WebMapHelper.getWebRoot() + Skin.REAL_EXPRESSIONBAG_PATH + filename;
			FileHelper.saveFile(path, file.getBytes());
			// 判断是否需要生成缩略图
			Thumbnails.of(path).size(200, 200).toFile(path);

			model.setExpressionbagurl(Skin.getVirtualPath(request, Skin.REAL_EXPRESSIONBAG_PATH) + filename);
			model.setExpressionbagid(RandomNum.getEBID());
			expressionBagsService.add(model);
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
	public String update(ExpressionBags model, HttpServletRequest request) {
		try {
			// 合法校验
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Iterator<String> filenames = mRequest.getFileNames();
			String filename = "";
			String oldname = model.getExpressionbagurl();
			oldname = FileHelper.getFileName(oldname);
			if (oldname.contains("?"))
				oldname = oldname.substring(0, oldname.indexOf("?"));

			if (filenames.hasNext()) {
				MultipartFile file = mRequest.getFile(filenames.next());
				if (file != null && file.getBytes().length > 0) {
					// 保存文件
					filename = UUID.randomUUID() + ".png";
					String path = WebMapHelper.getWebRoot() + Skin.REAL_EXPRESSIONBAG_PATH + filename;
					FileHelper.saveFile(path, file.getBytes());
					// 判断是否需要生成缩略图
					Thumbnails.of(path).size(200, 200).toFile(path);
				}
			}

			if (!filename.isEmpty()) {
				model.setExpressionbagurl(Skin.getVirtualPath(request, Skin.REAL_EXPRESSIONBAG_PATH) + filename);
				// 删除旧文件
				FileHelper.delete(WebMapHelper.getWebRoot() + Skin.REAL_EXPRESSIONBAG_PATH + oldname);
			}
			expressionBagsService.update(model);
			return "修改成功";
		} catch (Exception e) {
			return "修改失败";
		}
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Del", method = RequestMethod.POST)
	public String del(String ids) {
		try {
			List<String> list = Arrays.asList(ids.split(","));
			for (String id : list) {
				expressionsService.deleteByExpressionBagID(id, WebMapHelper.getWebRoot() + Skin.REAL_EXPRESSION_PATH);
			}
			expressionBagsService.delete(list, WebMapHelper.getWebRoot() + Skin.REAL_EXPRESSIONBAG_PATH);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
		}
	}
}
