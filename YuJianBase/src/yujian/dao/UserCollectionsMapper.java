package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.UserCollectionAndUser;
import yujian.models.UserCollections;

public interface UserCollectionsMapper {
	public List<UserCollectionAndUser> getListByPage(@Param("skip") long skip, @Param("take") long take,
			@Param("userid") long userid);

	public int add(UserCollections model);

	public int delete(UserCollections model);

	public int deleteByUserID(long userid);
}
