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
	
	public List<Orders> getList(String address,Date begin,Date end,int ordertype){
		if(address!=null&&!address.isEmpty()){
			address="%"+address+"%";
		}
		return ordersMapper.getList(address,begin, end, ordertype);
	}
	public List<SimpleOrders> getListByYears(String address,Date begin,Date end,int ordertype){
		if(address!=null&&!address.isEmpty()){
			address="%"+address+"%";
		}
		return ordersMapper.getListByYears(address,begin, end, ordertype);
	}
	public List<SimpleOrders> getListByMonths(String address,Date begin,Date end,int ordertype){
		if(address!=null&&!address.isEmpty()){
			address="%"+address+"%";
		}
		return ordersMapper.getListByMonths(address,begin, end, ordertype);
	}
	public List<SimpleOrders> getListByDays(String address,Date begin,Date end,int ordertype){
		if(address!=null&&!address.isEmpty()){
			address="%"+address+"%";
		}
		return ordersMapper.getListByDays(address,begin, end, ordertype);
	}
	public int add(Orders model){
		return ordersMapper.add(model);
	}
}
