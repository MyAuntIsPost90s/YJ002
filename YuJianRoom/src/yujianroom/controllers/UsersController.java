package yujianroom.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import yujian.models.RespJson;
import yujian.models.UserIntroduces;
import yujian.models.Users;
import yujian.service.UserImgsService;
import yujian.service.UserIntroducesService;
import yujian.service.UsersService;
import yujian.utilities.ConvertHelper;
import yujian.utilities.FileHelper;
import yujian.utilities.ImageHelper;
import yujian.utilities.WebMapHelper;
import yujianroom.common.EUIComboBoxData;
import yujianroom.common.MatchmakerType;
import yujianroom.common.ResultEasyUIList;
import yujianroom.common.ResultStatus;
import yujianroom.common.Skin;
import yujianroom.common.UserIntroduceStatus;
import yujianroom.common.UserIntroduceType;
import yujianroom.common.UserType;

@Controller
@RequestMapping(value = "/Users", produces = "application/json;charset=utf-8")
public class UsersController {
	@Autowired
	private UsersService userService;
	@Autowired
	private UserImgsService userImgsService;
	@Autowired
	private UserIntroducesService uiservice;

	/**
	 * 获取集合
	 * 
	 * @param page
	 * @param rows
	 * @param condition
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetList", method = RequestMethod.POST)
	public ResultEasyUIList<Users> getList(int page, int rows, String condition, Integer otherfunction, String address,
			String record, String height, Integer usertype, Integer sex) {
		ResultEasyUIList<Users> result = new ResultEasyUIList<>();
		try {
			List<Users> list = userService.getListByPageNoRed(page, rows, condition, otherfunction, address, record,
					height, sex, usertype);
			long total = userService.getCountNoRed(condition, otherfunction, address, sex, record, usertype, height);
			result.setRows(list);
			result.setTotal(total);
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	/**
	 * 获取用户地址
	 * 
	 * @param usertype
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetUserAddress", method = RequestMethod.GET)
	public List<EUIComboBoxData> getUserAddress(HttpServletRequest request, Integer usertype) {
		try {
			Users user = (Users) request.getSession().getAttribute(Skin.USER);
			List<EUIComboBoxData> list = new ArrayList<>();
			EUIComboBoxData first = new EUIComboBoxData();
			// 当不是超级管理员时，只有自己的地区
			if (user.getUsertype() != UserType.ROOT) {
				first.setId(user.getAddress());
				first.setText(user.getAddress());
				list.add(first);
				return list;
			}
			first.setId("");
			first.setText("全部");
			list.add(first);

			List<String> strs = userService.getUserAddress(usertype);
			strs.forEach(o -> {
				EUIComboBoxData item = new EUIComboBoxData();
				item.setId(o);
				item.setText(o);

				list.add(item);
			});
			return list;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取单个用户
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetSingle", method = RequestMethod.GET)
	public Users getSingle(HttpServletRequest request,long id) {
		try {
			Users users = (Users)request.getSession().getAttribute(Skin.USER);
			Users model = userService.getSingleByUserID(id);
			if(users.getUsertype()!=UserType.ROOT&&!model.getAddress().contains(users.getAddress())){
				model.setHeight("权限不足");
				model.setBloodtype("权限不足");
				model.setHobby("权限不足");
				model.setOccupation("权限不足");
				model.setRecord("权限不足");
				model.setMarried(-1);
				model.setSigncontent("权限不足");
				model.setAddress("权限不足");
			}
			return model;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取当前用户
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetNowUser", method = RequestMethod.GET)
	public Users getNowUser(HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			users = userService.getSingleByUserID(users.getUserid());
			request.getSession().setAttribute(Skin.USER, users);

			return users;
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
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
	@RequestMapping(value = "/Add", method = RequestMethod.POST)
	public String add(Users model, HttpServletRequest request) {
		try {
			// 判断重复
			Users temp = userService.getSingleByPhone(model.getPhone());
			if (temp != null)
				return "该手机号已经存在";

			// 判断是否需要存文件
			String filename = saveImg(request, model);

			model.setCreatetime(new Date());
			if (!filename.isEmpty())
				model.setHeadimgurl(Skin.getVirtualPath(request, Skin.REAL_HEADIMG_PATH) + filename);
			else
				model.setHeadimgurl("");
			model.setWeixinuid("");
			model.setWeixinoid("");
			model.setRiches((long) 0);
			model.setHotcount(0);
			userService.add(model);

			if (model.getUsertype() == UserType.NOMAL || model.getUsertype() == UserType.VIP
					|| model.getUsertype() == UserType.MATCHMAKER) {
				Users defaultuser = userService.getSingleByMatchmakerType(MatchmakerType.DEFAULT);
				// 配置默认红娘
				UserIntroduces uIntroduces = new UserIntroduces();
				uIntroduces.setUiuserid(model.getUserid());
				uIntroduces.setUserid(defaultuser.getUserid());
				uIntroduces.setUserintroducestatus(UserIntroduceStatus.PASS);
				uIntroduces.setUserintroducetype(UserIntroduceType.USER);

				List<UserIntroduces> list = new ArrayList<>();
				list.add(uIntroduces);
				uiservice.deleteMore(list);
				uiservice.add(uIntroduces);
			}
			return "添加成功";
		} catch (Exception e) {
			return "添加失败";
		}
	}

	/**
	 * 克隆账号
	 * 
	 * @param users
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/CloneUser", method = RequestMethod.POST)
	public String cloneUser(Users users) {
		try {
			// 判断重复
			Users temp = userService.getSingleByPhone(users.getPhone());
			if (temp != null)
				return "该手机号已经存在";
			users.setBirthday(new Date());
			users.setUsertype(UserType.ADMIN);
			users.setHeadimgurl("");
			userService.add(users);

			return "操作成功";
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return "操作失败";
		}
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/Update", method = RequestMethod.POST)
	public String update(Users model, HttpServletRequest request) {
		try {
			// 判断是否需要存文件
			String filename = saveImg(request, model);

			// 修改参数
			if (!filename.isEmpty()) {
				model.setHeadimgurl(
						Skin.getVirtualPath(request, Skin.REAL_HEADIMG_PATH) + filename + "?a=" + UUID.randomUUID());
			}
			// 判断是否需要配置红娘关系
			if (model.getUsertype() == UserType.NOMAL || model.getUsertype() == UserType.VIP
					|| model.getUsertype() == UserType.MATCHMAKER) {
				Users defaultuser = userService.getSingleByMatchmakerType(MatchmakerType.DEFAULT);
				// 配置默认红娘
				UserIntroduces uIntroduces = new UserIntroduces();
				uIntroduces.setUiuserid(model.getUserid());
				uIntroduces.setUserid(defaultuser.getUserid());
				uIntroduces.setUserintroducestatus(UserIntroduceStatus.PASS);
				uIntroduces.setUserintroducetype(UserIntroduceType.USER);

				List<UserIntroduces> list = new ArrayList<>();
				list.add(uIntroduces);
				uiservice.deleteMore(list);
				uiservice.add(uIntroduces);
			}

			userService.update(model);
			return "修改成功";
		} catch (Exception e) {
			return "修改失败";
		}
	}

	/**
	 * 修改红娘类型
	 * 
	 * @param userid
	 * @param matchmakertype
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/UpdateMatchmakertype", method = RequestMethod.POST)
	public String updateMatchmakertype(long userid, int matchmakertype) {
		try {
			Users user = userService.getSingleByUserID(userid);
			user.setMatchmakertype(matchmakertype);

			userService.resetMatchmakertype();
			userService.update(user);
			return "修改成功";
		} catch (Exception e) {
			return "修改失败";
		}
	}

	/**
	 * 修改用户视频
	 * 
	 * @param userid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/UpdateVideo", method = RequestMethod.POST)
	public RespJson updateVideo(long userid, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		try {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Iterator<String> fileNames = mRequest.getFileNames();
			if (!fileNames.hasNext()) {
				respJson.setStatus(ResultStatus.USERDEFINED);
				respJson.setMsg("未上传文件");
				return respJson;
			}

			Users model = userService.getSingleByUserID(userid);
			String oldname = "";
			if (model.getVideourl() != null && !model.getVideourl().isEmpty())
				oldname = FileHelper.getFileName(model.getVideourl());

			// 保存文件
			MultipartFile file = mRequest.getFile(fileNames.next());
			if (file == null || file.getBytes().length < 1) {
				respJson.setStatus(ResultStatus.USERDEFINED);
				respJson.setMsg("未上传文件");
				return respJson;
			}
			String ex = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')); // 获取文件后缀名
			String newname = UUID.randomUUID() + ex;
			FileHelper.saveFile(WebMapHelper.getWebRoot() + Skin.REAL_HEADIMG_PATH + newname, file.getBytes()); // 保存文件

			model.setVideourl(Skin.getVirtualPath(request, Skin.REAL_HEADIMG_PATH) + newname);
			userService.update(model);

			// 删除原始视频
			if (!oldname.isEmpty()) {
				FileHelper.delete(WebMapHelper.getWebRoot() + Skin.REAL_HEADIMG_PATH + oldname);
			}
			respJson.setStatus(ResultStatus.SUCCESS);
			respJson.setMsg("上传成功");
			respJson.setJsonData(model.getVideourl());
			return respJson;
		} catch (Exception e) {
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg("修改失败");
			return respJson;
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param pwd
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/UpdatePwd", method = RequestMethod.POST)
	public String updatePwd(String pwd, HttpServletRequest request) {
		try {
			if (pwd.isEmpty())
				return "修改失败";
			Users user = (Users) request.getSession().getAttribute(Skin.USER);
			user.setPassword(pwd);
			userService.update(user);

			request.getSession().setAttribute(Skin.USER, user);
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
	@RequestMapping(value = "/Del", method = RequestMethod.POST)
	public String del(String ids, HttpServletRequest request) {
		try {
			List<Long> list = ConvertHelper.convertToLongs(ids.split(","));
			for (Long id : list) {
				userImgsService.deleteByUserID(id, WebMapHelper.getWebRoot() + Skin.REAL_PHOTO_PATH);
			}
			userService.delete(list, WebMapHelper.getWebRoot() + Skin.REAL_HEADIMG_PATH);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
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
				// 判断是否需要生成缩略图
				Thumbnails.of(path).size(600, 600).toFile(path);
			}
		}
		return filename;
	}
}
