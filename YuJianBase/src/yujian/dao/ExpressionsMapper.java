package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.Expressions;

public interface ExpressionsMapper {
	public List<Expressions> getList(String expressionbagid);

	public List<Expressions> getListByEBIDs(@Param("ids") List<String> ids);

	public Expressions getSingle(String expressionid);

	public int add(Expressions model);

	public int delete(String expressionid);

	public int deleteByExpressionBagID(String expressionbagid);
}
