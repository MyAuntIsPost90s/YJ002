package yujianroom.controllers;

import java.util.Date;
import java.util.Iterator;
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
import yujian.models.UserImgs;
import yujian.models.UserIntroduces;
import yujian.models.Users;
import yujian.service.UserImgsService;
import yujian.service.UserIntroducesService;
import yujian.service.UsersService;
import yujian.utilities.FileHelper;
import yujian.utilities.ImageHelper;
import yujian.utilities.WebMapHelper;
import yujianroom.common.ResultStatus;
import yujianroom.common.Skin;
import yujianroom.common.UserIntroduceStatus;
import yujianroom.common.UserIntroduceType;
import yujianroom.common.UserType;

@Controller
@RequestMapping(value = "Common")
public class CommonController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private UserImgsService userImgsService;
	@Autowired
	private UserIntroducesService userIntroducesService;

	/**
	 * 更改用户头像
	 * 
	 * @param userid
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "UploadUserHeadImgs", method = RequestMethod.POST)
	public Users uploadUserHeadImgs(long userid, String pwd, HttpServletRequest request) {
		try {
			Users model = usersService.getSingleByUserID(userid);
			if (!model.getPassword().equals(pwd)) {
				return null;
			}
			// 判断是否需要存文件
			String filename = saveImg(request, model);

			// 修改参数
			if (!filename.isEmpty()) {
				model.setHeadimgurl(
						Skin.getVirtualPath(request, Skin.REAL_HEADIMG_PATH) + filename + "?a=" + UUID.randomUUID());
			}
			usersService.update(model);

			return model;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
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
	@RequestMapping(value = "UploadUserImgs", method = RequestMethod.POST)
	public UserImgs uploadUserImgs(long userid, String pwd, HttpServletRequest request) {
		try {
			Users temp = usersService.getSingleByUserID(userid);
			if (!temp.getPassword().equals(pwd)) {
				return null;
			}
			UserImgs model = new UserImgs();
			model.setUserid(userid);
			model.setUserimgid(UUID.randomUUID() + "");

			// 保存文件
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Iterator<String> filenames = mRequest.getFileNames();
			if (!filenames.hasNext()) {
				return null;
			}

			MultipartFile file = mRequest.getFile(filenames.next());
			if (file == null || file.getBytes().length < 1) {
				return null;
			}
			String filename = model.getUserimgid() + ".png";
			String path = WebMapHelper.getWebRoot() + Skin.REAL_PHOTO_PATH + filename;
			String minipath = WebMapHelper.getWebRoot() + Skin.REAL_PHOTO_PATH + "mini_" + filename;
			FileHelper.saveFile(path, file.getBytes());
			// 生成缩略图
			Thumbnails.of(path).size(80, 80).toFile(minipath);
			if (file.getBytes().length / 1024 / 1024 > 1) {
				Thumbnails.of(path).size(700, 700).outputQuality(0.7).toFile(path);
			}

			model.setUserimgurl(Skin.getVirtualPath(request, Skin.REAL_PHOTO_PATH) + filename);

			userImgsService.add(model);
			return model;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 删除用户图片
	 * 
	 * @param userid
	 * @param pwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "DelUserImg", method = RequestMethod.POST)
	public RespJson delUserImg(long userid, String pwd, String userimgid) {
		RespJson respJson = new RespJson();
		try {
			Users temp = usersService.getSingleByUserID(userid);
			if (!temp.getPassword().equals(pwd)) {
				respJson.setStatus(ResultStatus.FAIL);
				respJson.setMsg("删除失败");
				return respJson;
			}

			userImgsService.delete(userimgid, WebMapHelper.getWebRoot() + Skin.REAL_PHOTO_PATH);
			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg("删除成功");
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg("删除失败");
		}
		return respJson;
	}

	/**
	 * 添加红人
	 * 
	 * @param userid
	 * @param pwd
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "AddIntroduce", method = RequestMethod.POST)
	public RespJson addIntroduce(long myid, String pwd, Users model, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		try {
			Users user = usersService.getSingleByUserID(myid);
			if (user == null || !user.getPassword().equals(pwd)) {
				respJson.setStatus(ResultStatus.USERDEFINED);
				respJson.setMsg("添加失败T^T");
				return respJson;
			}

			// 判断文件是否至少为1
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Iterator<String> filenames = mRequest.getFileNames();
			if (!filenames.hasNext()) {
				respJson.setStatus(ResultStatus.USERDEFINED);
				respJson.setMsg("至少需要一张靓照哦");
				return respJson;
			}

			// 保存红人
			MultipartFile file = mRequest.getFile(filenames.next());
			if (file == null || file.getBytes().length < 1) {
				respJson.setStatus(ResultStatus.USERDEFINED);
				respJson.setMsg("至少需要一张靓照哦");
				return respJson;
			}
			String filename = UUID.randomUUID() + ".png";
			String path = WebMapHelper.getWebRoot() + Skin.REAL_HEADIMG_PATH + filename;
			FileHelper.saveFile(path, file.getBytes());
			ImageHelper.cutSquare(path, path); // 剪裁正方形图片
			// 判断是否需要生成缩略图
			Thumbnails.of(path).size(200, 200).toFile(path);
			model.setHeadimgurl(Skin.getVirtualPath(request, Skin.REAL_HEADIMG_PATH) + filename);
			model.setCreatetime(new Date());
			model.setVideourl("");
			model.setPassword("");
			model.setWeixinoid("");
			model.setWeixinuid("");
			model.setUsertype(UserType.RED);
			model.setGoldbalance((float) 0);
			model.setRiches((long) 0);
			model.setHotcount(0);
			usersService.add(model);

			// 添加关系
			UserIntroduces userIntroduces = new UserIntroduces();
			userIntroduces.setUiuserid(model.getUserid());
			userIntroduces.setUserid(user.getUserid());
			userIntroduces.setUserintroducestatus(UserIntroduceStatus.UNPASS);
			userIntroduces.setUserintroducetype(UserIntroduceType.RED);
			userIntroducesService.add(userIntroduces);

			// 保存生活照
			while (filenames.hasNext()) {
				file = mRequest.getFile(filenames.next());
				if (file == null || file.getBytes().length < 1)
					continue;

				// 存为图片
				UserImgs userImgs = new UserImgs();
				userImgs.setUserimgid(UUID.randomUUID() + "");
				userImgs.setUserid(model.getUserid());

				filename = userImgs.getUserimgid() + ".png";
				path = WebMapHelper.getWebRoot() + Skin.REAL_PHOTO_PATH + filename;
				String minipath = WebMapHelper.getWebRoot() + Skin.REAL_PHOTO_PATH + "mini_" + filename;

				FileHelper.saveFile(path, file.getBytes());
				Thumbnails.of(path).size(700, 700).outputQuality(0.7).toFile(path);
				// 生成缩略图
				Thumbnails.of(path).size(80, 80).toFile(minipath);

				userImgs.setUserimgurl(Skin.getVirtualPath(request, Skin.REAL_PHOTO_PATH + filename));
				userImgsService.add(userImgs);
			}

			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg("保存成功~");
			respJson.setJsonData(model);
			return respJson;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg("添加失败T^T，发生错误。");
			return respJson;
		}
	}

	/**
	 * 修改自己的视频
	 * 
	 * @param userid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "UpdateVideo", method = RequestMethod.POST)
	public Users updateVideo(long userid, String pwd, HttpServletRequest request) {
		try {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Iterator<String> fileNames = mRequest.getFileNames();
			if (!fileNames.hasNext()) {
				return null;
			}

			Users model = usersService.getSingleByUserID(userid);
			if (!model.getPassword().equals(pwd)) {
				return null;
			}
			String oldname = "";
			if (model.getVideourl() != null && !model.getVideourl().isEmpty())
				oldname = FileHelper.getFileName(model.getVideourl());

			// 保存文件
			MultipartFile file = mRequest.getFile(fileNames.next());
			if (file == null || file.getBytes().length < 1) {
				return null;
			}
			String ex = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')); // 获取文件后缀名
			String newname = UUID.randomUUID() + ex;
			FileHelper.saveFile(WebMapHelper.getWebRoot() + Skin.REAL_HEADIMG_PATH + newname, file.getBytes()); // 保存文件

			model.setVideourl(Skin.getVirtualPath(request, Skin.REAL_HEADIMG_PATH) + newname);
			usersService.update(model);

			// 删除原始视频
			if (!oldname.isEmpty()) {
				FileHelper.delete(WebMapHelper.getWebRoot() + Skin.REAL_HEADIMG_PATH + oldname);
			}
			return model;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	private String saveImg(HttpServletRequest request, Users model) throws Exception {
		String filename = "";
		MultipartHttpServletRequest mrequst = (MultipartHttpServletRequest) request;
		Iterator<String> filenames = mrequst.getFileNames();
		if (filenames.hasNext()) {
			MultipartFile file = mrequst.getFile(filenames.next());
			if (file != null && file.getBytes().length > 0) {

				if (model.getHeadimgurl() == null || model.getHeadimgurl().isEmpty()) {
					filename = UUID.randomUUID() + ".png";
				} else {
					filename = FileHelper.getFileName(model.getHeadimgurl());
					if (filename.contains("?")) {
						filename = filename.substring(0, filename.indexOf("?"));
					}
				}

				String path = WebMapHelper.getWebRoot() + Skin.REAL_HEADIMG_PATH + filename;
				FileHelper.saveFile(path, file.getBytes());
				ImageHelper.cutSquare(path, path); // 剪裁正方形图片
				if (file.getBytes().length / 1024 / 1024 > 1) {
					// 判断是否需要生成缩略图
					Thumbnails.of(path).size(600, 600).toFile(path);
				}
			}
		}
		return filename;
	}
}
