package yujianroom.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import yujian.models.Banners;
import yujian.service.BannersService;
import yujian.utilities.FileHelper;
import yujian.utilities.WebMapHelper;
import yujianroom.common.EUIComboBoxData;
import yujianroom.common.RandomNum;
import yujianroom.common.ResultEasyUIList;
import yujianroom.common.Skin;

@Controller
@RequestMapping(value = "Banners", produces = "application/json;charset=utf-8")
public class BannersController {
	@Autowired
	private BannersService bannersService;

	/**
	 * 获取集合
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetList", method = RequestMethod.POST)
	public ResultEasyUIList<Banners> getList(int page, int rows, Integer bannertype, String address) {
		ResultEasyUIList<Banners> result = new ResultEasyUIList<Banners>();
		try {
			List<Banners> list = bannersService.getList(bannertype, address);
			long total = bannersService.getCount(bannertype, address);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	/**
	 * 获取banner地址
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetAddress", method = RequestMethod.POST)
	public List<EUIComboBoxData> getAddress() {
		try {
			List<EUIComboBoxData> list = new ArrayList<>();

			List<String> strs = bannersService.getAddress();
			strs.forEach(o -> {
				EUIComboBoxData item = new EUIComboBoxData();
				item.setId(o);
				item.setText(o.equals("") ? "全国" : o);

				list.add(item);
			});
			return list;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取单条信息
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetSingle", method = RequestMethod.GET)
	public Banners getSingle(String id) {
		try {
			return bannersService.getSingle(id);
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
	public String add(Banners model, HttpServletRequest request) {
		try {
			// 判断文件是否存在
			MultipartHttpServletRequest mRequst = (MultipartHttpServletRequest) request;
			if (!mRequst.getFileNames().hasNext()) {
				return "请上传轮播图";
			}
			MultipartFile file = mRequst.getFile(mRequst.getFileNames().next());
			if (file == null || file.getBytes().length < 1) {
				return "请上传轮播图";
			}

			model.setBannerid(RandomNum.getBannerID());
			// 保存文件
			String fileName = model.getBannerid() + ".png";
			String path = WebMapHelper.getWebRoot() + Skin.REAL_BANNER_PATH + fileName;
			FileHelper.saveFile(path, file.getBytes());
			Thumbnails.of(path).size(800, 500).outputQuality(0.7).toFile(path);
			model.setBannerimgurl(Skin.getVirtualPath(request, Skin.REAL_BANNER_PATH) + fileName);

			bannersService.add(model);
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
	public String update(Banners model, HttpServletRequest request) {
		try {
			MultipartHttpServletRequest mRequst = (MultipartHttpServletRequest) request;
			String filename = FileHelper.getFileName(model.getBannerimgurl());
			if (filename.contains("?"))
				filename = filename.substring(0, filename.indexOf("?"));
			if (mRequst.getFileNames().hasNext()) {
				MultipartFile file = mRequst.getFile(mRequst.getFileNames().next());
				if (file.getBytes().length > 0) {
					String path = WebMapHelper.getWebRoot() + Skin.REAL_BANNER_PATH + filename;
					FileHelper.saveFile(path, file.getBytes());
					Thumbnails.of(path).size(800, 500).outputQuality(0.7).toFile(path);
					model.setBannerimgurl(
							Skin.getVirtualPath(request, Skin.REAL_BANNER_PATH) + filename + "?a=" + UUID.randomUUID());
				}
			}

			bannersService.update(model);
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
			bannersService.delete(list, WebMapHelper.getWebRoot() + Skin.REAL_BANNER_PATH);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
		}
	}
}
