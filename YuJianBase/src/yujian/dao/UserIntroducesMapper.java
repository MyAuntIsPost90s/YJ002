package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.UserIntroduces;
import yujian.models.UserIntroducesAndUser;

public interface UserIntroducesMapper {
	public List<UserIntroducesAndUser> getListByPage(@Param("skip") long skip, @Param("take") long take,
			@Param("condition") String condition, @Param("userintroducestatus") Integer userintroducestatus,
			@Param("userintroducetype") Integer userintroducetype, @Param("userid") Long userid,
			@Param("address") String address);

	public long getCount(@Param("condition") String condition,
			@Param("userintroducestatus") Integer userintroducestatus,
			@Param("userintroducetype") Integer userintroducetype, @Param("address") String address);

	public int addMore(@Param("list") List<UserIntroduces> list);

	public UserIntroducesAndUser getSingle(long uiuserid);

	public int update(UserIntroduces model);

	public int add(UserIntroduces model);

	public int delete(UserIntroduces model);

	public int deleteMore(@Param("list") List<UserIntroduces> list);
}
