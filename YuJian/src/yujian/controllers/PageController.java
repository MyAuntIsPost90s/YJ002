package yujian.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import yujian.common.OtherFunction;
import yujian.common.Skin;
import yujian.models.Users;
import yujian.service.UsersService;

@Controller
public class PageController {
	@Autowired
	private UsersService usersService;
	Logger log = LogManager.getLogger(PageController.class);

	/**
	 * 注册上传头像
	 * 
	 * @return
	 */
	@RequestMapping(value = "RegisterHeadImg", method = RequestMethod.GET)
	public ModelAndView registerHeadImg(HttpServletRequest request) {
		/*
		 * Users users =(Users)request.getSession().getAttribute(Skin.USER);
		 * if(users==null) { users = usersService.getSingleByUserID(21);
		 * request.getSession().setAttribute(Skin.USER, users); }
		 */
		ModelAndView modelAndView = new ModelAndView("Pages/Users/registerheadimg");
		return modelAndView;
	}

	/**
	 * 注册基本信息填写
	 * 
	 * @return
	 */
	@RequestMapping(value = "RegisterBase", method = RequestMethod.GET)
	public ModelAndView registerBase() {
		return new ModelAndView("Pages/Users/registerbase");
	}

	/**
	 * 注册兴趣信息填写
	 * 
	 * @return
	 */
	@RequestMapping(value = "RegisterHobby", method = RequestMethod.GET)
	public ModelAndView registerHobby() {
		return new ModelAndView("Pages/Users/registerhobby");
	}

	/**
	 * 首页
	 * 
	 * @param isvip
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/CommonUsers", method = RequestMethod.GET)
	public ModelAndView commonUsers(Integer isvip, HttpServletRequest request) {
		Users users = (Users) request.getSession().getAttribute(Skin.USER);
		if (users == null) {
			users = usersService.getSingleByUserID(21);
			request.getSession().setAttribute(Skin.USER, users);
		}
		ModelAndView modelAndView = new ModelAndView("Pages/Users/commonuser");
		modelAndView.addObject("usertype", users.getUsertype());
		modelAndView.addObject("viptip", Skin.TipMap.get("indexviptip"));
		return modelAndView;
	}

	/**
	 * 筛选
	 * 
	 * @return
	 */
	@RequestMapping(value = "/Filter", method = RequestMethod.GET)
	public ModelAndView filter() {
		return new ModelAndView("Pages/filter");
	}

	/**
	 * 搜索
	 * 
	 * @return
	 */
	@RequestMapping(value = "Search", method = RequestMethod.GET)
	public ModelAndView search() {
		return new ModelAndView("Pages/search");
	}

	/**
	 * 表情商城
	 * 
	 * @return
	 */
	@RequestMapping(value = "ExpressionShop", method = RequestMethod.GET)
	public ModelAndView expressionShop() {
		return new ModelAndView("Pages/ExpressionBags/expressionshop");
	}

	/**
	 * 表情商城
	 * 
	 * @return
	 */
	@RequestMapping(value = "MinExpressionShop", method = RequestMethod.GET)
	public ModelAndView minExpressionShop() {
		return new ModelAndView("Pages/ExpressionBags/minexpressionshop");
	}

	/**
	 * 表情包内容
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "Expressions", method = RequestMethod.GET)
	public ModelAndView expressions(String id) {
		ModelAndView modelAndView = new ModelAndView("Pages/ExpressionBags/expressions");
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	/**
	 * 排行榜
	 * 
	 * @param isriches
	 * @return
	 */
	@RequestMapping(value = "/SortUsers", method = RequestMethod.GET)
	public ModelAndView sortUsers(Integer isriches) {
		if (isriches != null && isriches == 1)
			return new ModelAndView("Pages/Users/richesuser");
		return new ModelAndView("Pages/Users/hotcountuser");
	}

	/**
	 * 我的
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "Mine", method = RequestMethod.GET)
	public ModelAndView mine(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("Pages/Users/mine");
		Users user = (Users) request.getSession().getAttribute(Skin.USER);
		if (user != null)
			modelAndView.addObject("id", user.getUserid());
		return modelAndView;
	}

	/**
	 * 我的修改信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "MyInfo", method = RequestMethod.GET)
	public ModelAndView myInfo() {
		return new ModelAndView("Pages/Users/myinfo");
	}

	/**
	 * 我的红娘修改信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "MyInfoMatchmaker", method = RequestMethod.GET)
	public ModelAndView myInfo(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute(Skin.USER);
		if (user != null && user.getOtherfunction() == OtherFunction.MATCHMAKER)
			return new ModelAndView("Pages/Users/myinfo_matchmaker");
		return new ModelAndView("Pages/Users/myinfo");
	}

	/**
	 * 修改兴趣爱好
	 * 
	 * @return
	 */
	@RequestMapping(value = "UpdateHobby", method = RequestMethod.GET)
	public ModelAndView updateHobby() {
		return new ModelAndView("Pages/Users/updatehobby");
	}

	/**
	 * 修改红人兴趣爱好
	 * 
	 * @return
	 */
	@RequestMapping(value = "UpdateRedHobby", method = RequestMethod.GET)
	public ModelAndView updateRedHobby(long userid) {
		ModelAndView modelAndView = new ModelAndView("Pages/UserIntroduces/updatehobby");
		modelAndView.addObject("id", userid);
		return modelAndView;
	}

	/**
	 * 修改基本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "UpdateBaseInfo", method = RequestMethod.GET)
	public ModelAndView updateBaseInfo() {
		return new ModelAndView("Pages/Users/updatebaseinfo");
	}

	/**
	 * 修改红人基本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "UpdateRedBaseInfo", method = RequestMethod.GET)
	public ModelAndView updateRedBaseInfo(long userid) {
		ModelAndView modelAndView = new ModelAndView("Pages/UserIntroduces/updatebaseinfo");
		modelAndView.addObject("id", userid);
		return modelAndView;
	}

	/**
	 * 修改择偶条件
	 * 
	 * @return
	 */
	@RequestMapping(value = "UpdateSelectCondition", method = RequestMethod.GET)
	public ModelAndView updateSelectCondition() {
		return new ModelAndView("Pages/Users/updateselectcondition");
	}

	/**
	 * 修改红人择偶条件
	 * 
	 * @return
	 */
	@RequestMapping(value = "UpdateRedSelectCondition", method = RequestMethod.GET)
	public ModelAndView updateRedSelectCondition(long userid) {
		ModelAndView modelAndView = new ModelAndView("Pages/UserIntroduces/updateselectcondition");
		modelAndView.addObject("id", userid);
		return modelAndView;
	}

	/**
	 * 修改内心独白
	 * 
	 * @param signcontent
	 * @return
	 */
	@RequestMapping(value = "UpdateSignContent", method = RequestMethod.GET)
	public ModelAndView updateSignContent(String signcontent) {
		return new ModelAndView("Pages/Users/updatesigncontent");
	}

	/**
	 * 修改红人内心独白
	 * 
	 * @param userid
	 * @param signcontent
	 * @return
	 */
	@RequestMapping(value = "UpdateRedSignContent", method = RequestMethod.GET)
	public ModelAndView updateRedSignContent(long userid, String signcontent) {
		ModelAndView modelAndView = new ModelAndView("Pages/UserIntroduces/updatesigncontent");
		modelAndView.addObject("id", userid);
		return modelAndView;
	}

	/**
	 * 修改红娘简介
	 * 
	 * @param matchmakerintroduct
	 * @return
	 */
	@RequestMapping(value = "UpdateMatchmakerIntroduct", method = RequestMethod.GET)
	public ModelAndView updateMatchmakerIntroduct(String matchmakerintroduct) {
		return new ModelAndView("Pages/Users/updatematchmakerintroduct");
	}

	/**
	 * 修改擅长
	 * 
	 * @param matchmakerintroduct
	 * @return
	 */
	@RequestMapping(value = "UpdateMatchmakerGoodAt", method = RequestMethod.GET)
	public ModelAndView updateMatchmakerGoodAt(String matchmakerintroduct) {
		return new ModelAndView("Pages/Users/updatematchmakergoodat");
	}

	/**
	 * 修改红娘建议
	 * 
	 * @param matchmakerintroduct
	 * @return
	 */
	@RequestMapping(value = "UpdateMatchmakerAdvise", method = RequestMethod.GET)
	public ModelAndView updateMatchmakerAdvice(String matchmakerintroduct) {
		return new ModelAndView("Pages/Users/updatematchmakeradvise");
	}

	/**
	 * 用户图片
	 * 
	 * @return
	 */
	@RequestMapping(value = "UserImgs", method = RequestMethod.GET)
	public ModelAndView userImgs(long userid, HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute(Skin.USER);
		ModelAndView modelAndView = new ModelAndView("Pages/UserImgs/userimgs");
		modelAndView.addObject("id", userid);
		modelAndView.addObject("viptip", Skin.TipMap.get("imgviptip"));
		modelAndView.addObject("usertype", user.getUsertype());
		return modelAndView;
	}

	/**
	 * 分享用户图片
	 * 
	 * @return
	 */
	@RequestMapping(value = "ShareImgs", method = RequestMethod.GET)
	public ModelAndView shareImgs(long userid, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("Pages/UserImgs/shareimgs");
		modelAndView.addObject("id", userid);
		return modelAndView;
	}

	/**
	 * 我的照片
	 * 
	 * @return
	 */
	@RequestMapping(value = "MyImgs", method = RequestMethod.GET)
	public ModelAndView myImgs() {
		return new ModelAndView("Pages/UserImgs/myimgs");
	}

	/**
	 * 红人的照片
	 * 
	 * @return
	 */
	@RequestMapping(value = "UserIntroduceImgs", method = RequestMethod.GET)
	public ModelAndView userIntroduceImgs(long userid, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("Pages/UserImgs/userintroduceimgs");
		modelAndView.addObject("id", userid);
		return modelAndView;
	}

	/**
	 * 用户视频
	 * 
	 * @param videourl
	 * @return
	 */
	@RequestMapping(value = "UserVideo", method = RequestMethod.GET)
	public ModelAndView userVideo(String videourl, HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute(Skin.USER);
		ModelAndView modelAndView = new ModelAndView("Pages/UserImgs/uservideo");
		modelAndView.addObject("videourl", videourl);
		modelAndView.addObject("usertype", user.getUsertype());
		modelAndView.addObject("viptip", Skin.TipMap.get("videoviptip"));
		return modelAndView;
	}
	
	/**
	 * 分享用户视频
	 * @param videourl
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ShareVideo", method = RequestMethod.GET)
	public ModelAndView shareVideo(String videourl, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("Pages/UserImgs/sharevideo");
		modelAndView.addObject("videourl", videourl);
		return modelAndView;
	}

	/**
	 * 用户详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "UserDetail", method = RequestMethod.GET)
	public ModelAndView userDetail(long id, String type, HttpServletRequest request) {
		Users users = (Users) request.getSession().getAttribute(Skin.USER);
		ModelAndView modelAndView = new ModelAndView("Pages/Users/userdetail");
		modelAndView.addObject("id", id);
		modelAndView.addObject("myid", users.getUserid());
		modelAndView.addObject("macthtip", Skin.TipMap.get("makematch"));

		if (type != null && type.equals("matchmaker")) {
			modelAndView.addObject("macthtip", Skin.TipMap.get("outsingle"));
			modelAndView.setViewName("Pages/Users/userdetail_matchmaker");
		}
		return modelAndView;
	}

	/**
	 * 分享页面
	 * 
	 * @param id
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ShareDetail", method = RequestMethod.GET)
	public ModelAndView sharDetail(long id, String type, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("Pages/Users/sharedetail");
		if (type != null && type.equals("matchmaker"))
			modelAndView.setViewName("Pages/Users/sharedetail_matchmaker");
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	/**
	 * 留言
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "LeaveWords", method = RequestMethod.GET)
	public ModelAndView leaveWords(long id) {
		ModelAndView modelAndView = new ModelAndView("Pages/LeaveWords/leavewords");
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	/**
	 * 留言详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "LeaveWordsDetail", method = RequestMethod.GET)
	public ModelAndView leaveWords(String leavewordid, long userid, HttpServletRequest request) {
		Users users = (Users) request.getSession().getAttribute(Skin.USER);
		ModelAndView modelAndView = new ModelAndView("Pages/LeaveWords/leavewordsdetail");
		modelAndView.addObject("leavewordid", leavewordid);
		modelAndView.addObject("userid", userid);
		modelAndView.addObject("myid", users.getUserid());
		return modelAndView;
	}

	/**
	 * 反馈
	 * 
	 * @return
	 */
	@RequestMapping(value = "FeedBacks", method = RequestMethod.GET)
	public ModelAndView feedBacks() {
		return new ModelAndView("Pages/FeedBacks/feedbacks");
	}

	/**
	 * 用户的撮合记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "UserSeekIntroduct")
	public ModelAndView userSeekIntroduct(HttpServletRequest request) {
		Users users = (Users) request.getSession().getAttribute(Skin.USER);
		ModelAndView modelAndView = new ModelAndView("Pages/Users/userseekintroduct");
		modelAndView.addObject("id", users.getUserid());
		return modelAndView;
	}

	/**
	 * 我的红人
	 * 
	 * @return
	 */
	@RequestMapping(value = "MyIntroduces", method = RequestMethod.GET)
	public ModelAndView myIntroduces(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute(Skin.USER);
		if (user != null && user.getOtherfunction() == OtherFunction.MATCHMAKER)
			return new ModelAndView("Pages/UserIntroduces/myintroduces");
		return new ModelAndView("redirect:/YuJian/Mine");
	}

	/**
	 * 充值页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "Recharge", method = RequestMethod.GET)
	public ModelAndView recharge(String selecttype) {
		ModelAndView modelAndView = new ModelAndView("Pages/Recharges/recharge");
		modelAndView.addObject("selecttype", selecttype == null ? "" : selecttype);
		return modelAndView;
	}

	/**
	 * 添加我的红人
	 * 
	 * @return
	 */
	@RequestMapping(value = "MyIntroducesAdd", method = RequestMethod.GET)
	public ModelAndView myIntroducesAdd() {
		return new ModelAndView("Pages/UserIntroduces/myintroducesadd");
	}

	/**
	 * 修改我的红人
	 * 
	 * @return
	 */
	@RequestMapping(value = "MyIntroducesEdit", method = RequestMethod.GET)
	public ModelAndView myIntroducesEdit(long id) {
		ModelAndView modelAndView = new ModelAndView("Pages/UserIntroduces/myintroducesedit");
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	/**
	 * 礼物商店
	 * 
	 * @return
	 */
	@RequestMapping(value = "GiftShop", method = RequestMethod.GET)
	public ModelAndView giftShop(long id) {
		ModelAndView modelAndView = new ModelAndView("Pages/Gifts/giftshop");
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	/**
	 * 用户礼物
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "UserGifts", method = RequestMethod.GET)
	public ModelAndView userGifts(long id) {
		ModelAndView modelAndView = new ModelAndView("Pages/Gifts/usergifts");
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	/**
	 * 礼物排行榜
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "GiftRank", method = RequestMethod.GET)
	public ModelAndView giftRank(long id) {
		ModelAndView modelAndView = new ModelAndView("Pages/Gifts/giftrank");
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	/**
	 * 我的收藏
	 * 
	 * @return
	 */
	@RequestMapping(value = "CollectUser", method = RequestMethod.GET)
	public ModelAndView collectUser() {
		return new ModelAndView("Pages/Users/collectuser");
	}

	/**
	 * 支付成功提示
	 * 
	 * @return
	 */
	@RequestMapping(value = "PayTip", method = RequestMethod.GET)
	public ModelAndView payTip() {
		return new ModelAndView("Pages/Recharges/paytip");
	}

	/**
	 * 求撮合
	 * 
	 * @return
	 */
	@RequestMapping(value = "MySeekIntroduct", method = RequestMethod.GET)
	public ModelAndView mySeekIntroduct(String type) {
		if (type != null && type.equals("single"))
			return new ModelAndView("Pages/SeekIntroducts/myseekintroduct_single");
		return new ModelAndView("Pages/SeekIntroducts/myseekintroduct");
	}

	/**
	 * 登陆错误界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "LoginErr", method = RequestMethod.GET)
	public ModelAndView loginErr() {
		ModelAndView modelAndView = new ModelAndView("Pages/err");
		modelAndView.addObject("msg", "登陆失败，参数错误T^T");
		return modelAndView;
	}

	@RequestMapping("404")
	public ModelAndView page404() {
		ModelAndView modelAndView = new ModelAndView("Pages/err");
		modelAndView.addObject("msg", "404页面走丢了");
		return modelAndView;
	}

	@RequestMapping("500")
	public ModelAndView page500() {
		log.error(500);
		ModelAndView modelAndView = new ModelAndView("Pages/err");
		modelAndView.addObject("msg", "T^T内部错误~~~");
		return modelAndView;
	}
}
