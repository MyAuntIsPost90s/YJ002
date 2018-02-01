package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.ExpressionBags;
import yujian.models.UserExpressionBags;

public interface UserExpressionBagsMapper {
	public List<ExpressionBags> getList(@Param("userid") long userid);

	public int add(UserExpressionBags model);
}
