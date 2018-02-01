package yujianroom.controllers;

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
import yujian.models.RespJson;
import yujian.models.UserImgs;
import yujian.service.UserImgsService;
import yujian.utilities.FileHelper;
import yujian.utilities.WebMapHelper;
import yujianroom.common.ResultStatus;
import yujianroom.common.Skin;

@Controller
@RequestMapping(value = "/UserImgs", produces = "application/json;charset=utf-8")
public class UserImgsController {
	@Autowired
	private UserImgsService userImgsService;

	/**
	 * 获取用户照片集合
	 * 
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetList", method = RequestMethod.POST)
	public List<UserImgs> getList(long userid) {
		try {
			return userImgsService.getList(userid);
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
	public RespJson add(long userid, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		try {
			UserImgs model = new UserImgs();
			model.setUserid(userid);
			model.setUserimgid(UUID.randomUUID() + "");

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
			String filename = model.getUserimgid() + ".png";
			String path = WebMapHelper.getWebRoot() + Skin.REAL_PHOTO_PATH + filename;
			String minipath = WebMapHelper.getWebRoot() + Skin.REAL_PHOTO_PATH + "mini_" + filename;
			FileHelper.saveFile(path, file.getBytes());
			Thumbnails.of(path).size(600, 600).toFile(path);
			// 生成缩略图
			Thumbnails.of(path).size(80, 80).toFile(minipath);

			model.setUserimgurl(Skin.getVirtualPath(request, Skin.REAL_PHOTO_PATH) + filename);

			userImgsService.add(model);
			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg("添加成功");
			respJson.setJsonData(model);
		} catch (Exception e) {
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg("添加失败");
		}
		return respJson;
	}

	@ResponseBody
	@RequestMapping(value = "Del", method = RequestMethod.POST)
	public String del(String ids) {
		try {
			String[] strings = ids.split(",");
			for (String string : strings) {
				userImgsService.delete(string, WebMapHelper.getWebRoot() + Skin.REAL_PHOTO_PATH);
			}
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
		}
	}
}
