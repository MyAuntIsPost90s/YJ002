package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.UserLovers;
import yujian.models.UserLovserAndABUser;

public interface UserLoversMapper {
	public List<UserLovserAndABUser> getListByPage(@Param("skip") long skip, @Param("take") long take,
			@Param("condition") String condition);

	public long getCount(@Param("condition") String condition);

	public UserLovserAndABUser getSingle(UserLovers model);

	public int add(UserLovers model);

	public int update(UserLovers model);

	public int delete(UserLovers model);
}
