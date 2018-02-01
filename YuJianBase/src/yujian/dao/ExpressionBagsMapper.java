package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.ExpressionBags;

public interface ExpressionBagsMapper {
	public List<ExpressionBags> getListByPage(@Param("skip") long skip, @Param("take") long take);

	public List<ExpressionBags> getExpressionBagsNoUser(@Param("skip") long skip, @Param("take") long take,
			@Param("userid") long userid);

	public ExpressionBags getSingle(String expressionbagid);

	public long getCount();

	public int add(ExpressionBags model);

	public int update(ExpressionBags model);

	public int delete(List<String> ids);
}
