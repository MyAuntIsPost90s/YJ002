package yujian.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.Orders;
import yujian.models.SimpleOrders;

public interface OrdersMapper {
	public List<Orders> getList(@Param("begin") Date begin, @Param("end") Date end, @Param("orderType") int ordertype);

	public List<SimpleOrders> getListByYears(@Param("begin") Date begin, @Param("end") Date end,
			@Param("orderType") int ordertype);

	public List<SimpleOrders> getListByMonths(@Param("begin") Date begin, @Param("end") Date end,
			@Param("orderType") int ordertype);

	public List<SimpleOrders> getListByDays(@Param("begin") Date begin, @Param("end") Date end,
			@Param("orderType") int ordertype);

	public int add(Orders model);
}
