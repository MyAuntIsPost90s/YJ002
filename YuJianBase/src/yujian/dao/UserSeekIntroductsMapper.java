package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.UserSeekIntroducts;
import yujian.models.UserSeekIntroductsAndUser;

public interface UserSeekIntroductsMapper {
	public int add(UserSeekIntroducts model);

	public int delete(String userseekintroductid);

	public List<UserSeekIntroductsAndUser> getListByPage(@Param("skip") int skip, @Param("take") int take,
			@Param("fromuserid") Long fromuserid);
}
