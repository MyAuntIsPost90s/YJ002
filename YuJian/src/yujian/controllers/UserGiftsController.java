package yujian.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.common.OrderType;
import yujian.common.RandomNum;
import yujian.common.ResultStatus;
import yujian.common.Skin;
import yujian.common.UserType;
import yujian.models.Gifts;
import yujian.models.Orders;
import yujian.models.RespJson;
import yujian.models.SysConfigs;
import yujian.models.UserGifts;
import yujian.models.UserGiftsAndUG;
import yujian.models.Users;
import yujian.models.UsersSortView;
import yujian.service.GiftsService;
import yujian.service.OrdersService;
import yujian.service.SysConfigsService;
import yujian.service.UserGiftsService;
import yujian.service.UsersService;
import yujian.utilities.ConvertHelper;

@Controller
@RequestMapping(value = "UserGifts")
public class UserGiftsController {
	@Autowired
	private UserGiftsService userGiftsService;
	@Autowired
	private GiftsService giftsService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private SysConfigsService sysConfigsService;
	@Autowired
	private OrdersService ordersService;

	/**
	 * 赠送礼物
	 * 
	 * @param giftids
	 * @param counts
	 * @param touserid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "SendGift", method = RequestMethod.POST)
	public RespJson sendGift(String giftids, String counts, long touserid, HttpServletRequest request) {
		RespJson respJson = new RespJson();
		Skin.PayLock.lock();
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			SysConfigs configs = sysConfigsService.getSingle();
			float discount = 1;
			if (users.getUsertype() == UserType.VIP)
				discount = configs.getVipdiscount();

			List<Gifts> gList = giftsService.getListByIDs(Arrays.asList(giftids.split(",")));
			List<Integer> nums = ConvertHelper.convertToInts(counts.split(","));
			List<UserGifts> models = new ArrayList<>();

			Float allcost = (float) 0;
			Date date = new Date();
			String groupid = RandomNum.getGroupID();
			for (int i = 0; i < gList.size(); i++) {
				if (nums.get(i) < 1)
					continue;
				UserGifts model = new UserGifts();
				model.setFromuserid(users.getUserid());
				model.setTouserid(touserid);
				model.setGiftid(gList.get(i).getGiftid());
				model.setUsergifttime(date);
				model.setGiftcount(nums.get(i));
				model.setUsergiftcost(nums.get(i) * gList.get(i).getGiftcost() * discount);
				model.setGroupid(groupid);

				allcost += model.getUsergiftcost();
				models.add(model);
			}

			if (allcost > 0) { // 判断合法
				// 判断金币是否足够
				if (users.getGoldbalance() >= allcost) {
					users.setGoldbalance(users.getGoldbalance() - allcost); // 修改数量

					userGiftsService.addMore(models); // 先add

					// 生成订单
					Orders orders = new Orders();
					orders.setOrdercost(allcost / 10);
					orders.setOrderid(RandomNum.getOrderID());
					orders.setOrderdate(new Date());
					orders.setOrdertype(OrderType.GIFT);
					orders.setUserid(users.getUserid());

					// 修改火热度
					Users toUsers = usersService.getSingleByUserID(touserid);
					toUsers.setHotcount(toUsers.getHotcount() + (int) (float) allcost);
					usersService.update(toUsers);
					ordersService.add(orders);
					usersService.update(users); // 后update

					request.getSession().setAttribute(Skin.USER, users);

					respJson.setStatus(ResultStatus.SUCCESS);
					respJson.setMsg(Skin.TipMap.get("buygiftsuccess"));
				} else {
					respJson.setStatus(ResultStatus.USERDEFINED);
					respJson.setMsg(Skin.TipMap.get("buygiftless") + "<br/><a href='/YuJian/Recharge'>立即充值<a>");
				}
			} else { // 非法请求处理
				Logger.getRootLogger().error("存在非法提交数据，账户ID为：" + users.getUserid());
				respJson.setStatus(ResultStatus.FAIL);
				respJson.setMsg("系统已记录非法请求");
			}

		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			respJson.setStatus(ResultStatus.FAIL);
			respJson.setMsg(Skin.TipMap.get("buygiftfail"));
		}
		Skin.PayLock.unlock();
		return respJson;
	}

	/**
	 * 获取礼物列表
	 * 
	 * @param userid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetUserGifts", method = RequestMethod.POST)
	public List<UserGiftsAndUG> getUserGifts(long userid, int page, int pageSize) {
		try {
			return userGiftsService.getUserGifts(userid, page, pageSize);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取用户排名
	 * 
	 * @param userid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetUserRank", method = RequestMethod.POST)
	public List<UsersSortView> getUserRank(long userid, int page, int pageSize) {
		try {
			return userGiftsService.getUserGiftsRank(userid, page, pageSize);
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	/**
	 * 获取我的排名
	 * 
	 * @param userid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetMyRank", method = RequestMethod.POST)
	public UsersSortView getMyRank(long userid, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute(Skin.USER);
			return userGiftsService.getMyGiftRank(userid, users.getUserid());
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}
}
