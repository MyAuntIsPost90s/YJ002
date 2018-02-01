package yujian.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.OrdersMapper;
import yujian.models.Orders;
import yujian.models.SimpleOrders;

@Service
public class OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;
	
	public List<Orders> getList(Date begin,Date end,int ordertype){
		return ordersMapper.getList(begin, end, ordertype);
	}
	public List<SimpleOrders> getListByYears(Date begin,Date end,int ordertype){
		return ordersMapper.getListByYears(begin, end, ordertype);
	}
	public List<SimpleOrders> getListByMonths(Date begin,Date end,int ordertype){
		return ordersMapper.getListByMonths(begin, end, ordertype);
	}
	public List<SimpleOrders> getListByDays(Date begin,Date end,int ordertype){
		return ordersMapper.getListByDays(begin, end, ordertype);
	}
	public int add(Orders model){
		return ordersMapper.add(model);
	}
}
