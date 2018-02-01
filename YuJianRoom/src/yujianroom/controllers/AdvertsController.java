package yujianroom.controllers;

import java.util.Arrays;
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
import yujian.models.Adverts;
import yujian.service.AdvertsService;
import yujian.utilities.FileHelper;
import yujian.utilities.WebMapHelper;
import yujianroom.common.RandomNum;
import yujianroom.common.ResultEasyUIList;
import yujianroom.common.Skin;

@Controller
@RequestMapping(value = "Adverts", produces = "application/json;charset=utf-8")
public class AdvertsController {
	@Autowired
	private AdvertsService advertsService;

	/**
	 * 获取集合
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetList", method = RequestMethod.POST)
	public ResultEasyUIList<Adverts> getList(int page, int rows) {
		ResultEasyUIList<Adverts> result = new ResultEasyUIList<Adverts>();
		try {
			List<Adverts> list = advertsService.getListByPage(page, rows);
			long total = advertsService.getCount();
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
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetSingle", method = RequestMethod.GET)
	public Adverts getSingle(String id) {
		try {
			return advertsService.getSingle(id);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 添加
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Add", method = RequestMethod.POST)
	public String add(Adverts model, HttpServletRequest request) {
		try {
			// 判断文件是否存在
			MultipartHttpServletRequest mRequst = (MultipartHttpServletRequest) request;
			MultipartFile logofile = mRequst.getFile("logo");
			MultipartFile imgfile = mRequst.getFile("img");
			if (imgfile == null || imgfile.isEmpty()) {
				return "请上传广告图片";
			}
			if (logofile == null || logofile.isEmpty()) {
				return "请上传广告商家logo";
			}
			model.setAdvertid(RandomNum.getAdvertID());
			// 保存img文件
			String fileName = model.getAdvertid() + ".png";
			String path = WebMapHelper.getWebRoot() + Skin.REAL_ADVERT_PATH + fileName;
			FileHelper.saveFile(path, imgfile.getBytes());
			Thumbnails.of(path).size(800, 500).outputQuality(0.7).toFile(path);
			// 保存logo
			String filelogname = model.getAdvertid() + "_logo.png";
			path = WebMapHelper.getWebRoot() + Skin.REAL_ADVERT_PATH + filelogname;
			FileHelper.saveFile(path, logofile.getBytes());
			Thumbnails.of(path).size(200, 200).outputQuality(0.7).toFile(path);

			model.setAdvertimgurl(Skin.getVirtualPath(request, Skin.REAL_ADVERT_PATH) + fileName);
			model.setAdvertlogourl(Skin.getVirtualPath(request, Skin.REAL_ADVERT_PATH) + filelogname);

			advertsService.add(model);
			return "添加成功";
		} catch (Exception e) {
			return "添加失败";
		}
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Update", method = RequestMethod.POST)
	public String update(Adverts model, HttpServletRequest request) {
		try {
			MultipartHttpServletRequest mRequst = (MultipartHttpServletRequest) request;
			String filename = FileHelper.getFileName(model.getAdvertimgurl());
			String logoname = FileHelper.getFileName(model.getAdvertlogourl());
			if (filename.contains("?"))
				filename = filename.substring(0, filename.indexOf("?"));
			if (logoname.contains("?"))
				logoname = logoname.substring(0, logoname.indexOf("?"));

			MultipartFile logofile = mRequst.getFile("logo");
			MultipartFile imgfile = mRequst.getFile("img");
			String path = "";
			// 保存logo
			if (logofile != null && !logofile.isEmpty()) {
				path = WebMapHelper.getWebRoot() + Skin.REAL_ADVERT_PATH + logoname;
				FileHelper.saveFile(path, logofile.getBytes());
				Thumbnails.of(path).size(200, 200).outputQuality(0.7).toFile(path);
				model.setAdvertimgurl(
						Skin.getVirtualPath(request, Skin.REAL_ADVERT_PATH) + logoname + "?a=" + UUID.randomUUID());
			}
			// 保存图片
			if (imgfile != null && !imgfile.isEmpty()) {
				path = WebMapHelper.getWebRoot() + Skin.REAL_ADVERT_PATH + filename;
				FileHelper.saveFile(path, filename.getBytes());
				Thumbnails.of(path).size(800, 500).outputQuality(0.7).toFile(path);
				model.setAdvertimgurl(
						Skin.getVirtualPath(request, Skin.REAL_ADVERT_PATH) + filename + "?a=" + UUID.randomUUID());
			}

			advertsService.update(model);
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
			advertsService.delete(list, WebMapHelper.getWebRoot() + Skin.REAL_ADVERT_PATH);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
		}
	}
}
