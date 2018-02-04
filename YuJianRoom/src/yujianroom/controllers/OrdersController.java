package yujianroom.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.models.SimpleOrders;
import yujian.service.OrdersService;
import yujianroom.common.EUIComboBoxData;

@Controller
@RequestMapping(value = "Orders", produces = "application/json;charset=utf-8")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;

	/**
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetYears", method = RequestMethod.GET)
	public List<EUIComboBoxData> getYears() {
		List<EUIComboBoxData> list = new ArrayList<>();
		int now = Calendar.getInstance().get(Calendar.YEAR) + 1;
		for (int i = -20; i <= 0; i++) {
			EUIComboBoxData model = new EUIComboBoxData();
			model.setId((now + i) + "");
			model.setText((now + i) + "");
			list.add(model);
		}

		return list;
	}

	/**
	 * 获取月
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetMonths", method = RequestMethod.GET)
	public List<EUIComboBoxData> getMonths() {
		List<EUIComboBoxData> list = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			EUIComboBoxData model = new EUIComboBoxData();
			model.setId(i + "");
			model.setText(i + "");
			list.add(model);
		}
		return list;
	}

	/**
	 * 获取年订单统计信息
	 * 
	 * @param begin
	 * @param end
	 * @param ordertype
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetOrdersByYears", method = RequestMethod.POST)
	public List<SimpleOrders> getOrdersByYears(@DateTimeFormat(pattern = "yyyy") Date begin,
			@DateTimeFormat(pattern = "yyyy") Date end, int ordertype,String address) {
		try {
			return ordersService.getListByYears(address,begin, end, ordertype);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取月订单统计信息
	 * 
	 * @param begin
	 * @param end
	 * @param ordertype
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetOrdersByMonths", method = RequestMethod.POST)
	public List<SimpleOrders> getOrdersByMonths(@DateTimeFormat(pattern = "yyyy-MM") Date begin,
			@DateTimeFormat(pattern = "yyyy-MM") Date end, int ordertype,String address) {
		try {
			return ordersService.getListByMonths(address,begin, end, ordertype);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取天订单统计信息
	 * 
	 * @param begin
	 * @param end
	 * @param ordertype
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GetOrdersByDays", method = RequestMethod.POST)
	public List<SimpleOrders> getOrdersByDays(@DateTimeFormat(pattern = "yyyy-MM-dd") Date begin,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date end, int ordertype,String address) {
		try {
			return ordersService.getListByDays(address,begin, end, ordertype);
		} catch (Exception e) {
			return null;
		}
	}
}
