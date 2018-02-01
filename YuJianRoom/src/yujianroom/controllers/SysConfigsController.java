package yujianroom.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;
import yujian.models.RespJson;
import yujian.models.SysConfigs;
import yujian.service.SysConfigsService;
import yujian.utilities.FileHelper;
import yujian.utilities.WebMapHelper;
import yujianroom.common.ResultStatus;
import yujianroom.common.Skin;

@Controller
@RequestMapping(value = "SysConfigs", produces = "application/json;charset=utf-8")
public class SysConfigsController {
	@Autowired
	private SysConfigsService sysConfigsService;

	/**
	 * 获取单条信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetSingle", method = RequestMethod.GET)
	public SysConfigs getSingle() {
		try {
			return sysConfigsService.getSingle();
		} catch (Exception e) {
			return null;
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
	public String update(SysConfigs model) {
		try {
			SysConfigs temp = sysConfigsService.getSingle();
			if (temp == null) {
				sysConfigsService.add(temp);
			} else {
				model.setSysconfigid(temp.getSysconfigid());
				sysConfigsService.update(model);
			}
			return "修改成功";
		} catch (Exception e) {
			return "修改失败";
		}
	}

	/**
	 * 保存背景缩略图
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateUserBgUrl", method = RequestMethod.POST)
	public RespJson updateUserBgUrl(HttpServletRequest request) {
		RespJson respJson = new RespJson();
		try {
			String filename = "bg.png";
			SysConfigs sysConfigs = sysConfigsService.getSingle();
			sysConfigs.setUserbgurl(
					Skin.getVirtualPath(request, Skin.REAL_SYSCONFIG_PATH) + filename + "?a=" + UUID.randomUUID());

			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = mRequest.getFile("file");

			// 校验文件是否存在
			if (file == null || file.getBytes().length < 1) {
				respJson.setStatus(ResultStatus.FAIL);
				respJson.setMsg("保存失败，上传文件错误");
				return respJson;
			}

			// 保存文件
			String path = WebMapHelper.getWebRoot() + Skin.REAL_SYSCONFIG_PATH + filename;
			FileHelper.saveFile(path, file.getBytes());
			Thumbnails.of(path).size(800, 500).outputQuality(0.7).toFile(path);

			sysConfigsService.update(sysConfigs);

			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg("保存成功");
			respJson.setJsonData(sysConfigs.getUserbgurl());
			return respJson;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg("保存失败");
			return respJson;
		}
	}
}
