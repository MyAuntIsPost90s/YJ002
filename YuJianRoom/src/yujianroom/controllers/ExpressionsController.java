package yujianroom.controllers;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;
import yujian.models.Expressions;
import yujian.models.RespJson;
import yujian.service.ExpressionsService;
import yujian.utilities.FileHelper;
import yujian.utilities.WebMapHelper;
import yujianroom.common.RandomNum;
import yujianroom.common.ResultStatus;
import yujianroom.common.Skin;

@Controller
@RequestMapping(value = "Expressions", produces = "application/json;charset=utf-8")
public class ExpressionsController {
	@Autowired
	private ExpressionsService expressionsService;

	@ResponseBody
	@RequestMapping(value = "GetList", method = RequestMethod.POST)
	public List<Expressions> getList(String expressionbagid) {
		try {
			return expressionsService.getList(expressionbagid);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 添加图片
	 * 
	 * @param userid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Add", method = RequestMethod.POST)
	public RespJson add(Expressions model, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		try {
			model.setExpressionid(RandomNum.getExpressionID());

			// 保存文件
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Iterator<String> filenames = mRequest.getFileNames();
			if (!filenames.hasNext()) {
				respJson.setStatus(ResultStatus.FAIL);
				respJson.setMsg("添加失败");
				return respJson;
			}

			MultipartFile file = mRequest.getFile(filenames.next());
			if (file == null || file.getBytes().length < 1) {
				respJson.setStatus(ResultStatus.FAIL);
				respJson.setMsg("添加失败");
				return respJson;
			}
			String filename = model.getExpressionid() + ".png";
			String path = WebMapHelper.getWebRoot() + Skin.REAL_EXPRESSION_PATH + filename;
			FileHelper.saveFile(path, file.getBytes());
			Thumbnails.of(path).size(500, 500).toFile(path);

			model.setExpressionurl(Skin.getVirtualPath(request, Skin.REAL_EXPRESSION_PATH) + filename);

			expressionsService.add(model);
			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg("添加成功");
			respJson.setJsonData(model);
		} catch (Exception e) {
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg("添加失败");
		}
		return respJson;
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
			String[] strings = ids.split(",");
			for (String string : strings) {
				expressionsService.delete(string, WebMapHelper.getWebRoot() + Skin.REAL_EXPRESSION_PATH);
			}
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
		}
	}
}
