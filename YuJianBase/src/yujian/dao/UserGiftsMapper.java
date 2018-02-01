package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.UserGifts;
import yujian.models.UserGiftsAndUG;
import yujian.models.UsersSortView;

public interface UserGiftsMapper {
	public List<UserGiftsAndUG> getUserGifts(@Param("userid") long userid, @Param("skip") long skip,
			@Param("take") long take);

	public List<UsersSortView> getUserGiftsRank(@Param("userid") long userid, @Param("skip") long skip,
			@Param("take") long take);

	public UsersSortView getMyGiftRank(@Param("userid") long userid, @Param("myid") long myid);

	public int add(UserGifts model);

	public int addMore(@Param("models") List<UserGifts> models);

	public int deleteByUserID(long userid);
}
