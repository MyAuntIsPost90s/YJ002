package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.SeekIntroductAndUser;
import yujian.models.SeekIntroducts;

public interface SeekIntroductsMapper {
	public int add(SeekIntroducts model);

	public List<SeekIntroductAndUser> getList(@Param("skip") int skip, @Param("take") int take,
			@Param("userid") long userid, @Param("seekintroductstatus") Integer seekintroductstatus);

	public List<SeekIntroductAndUser> getSingleSeekList(@Param("skip") int skip, @Param("take") int take,
			@Param("userid") long userid, @Param("seekintroductstatus") Integer seekintroductstatus);

	public int getCount(@Param("userid") long userid, @Param("seekintroductstatus") Integer seekintroductstatus);

	public SeekIntroductAndUser getSingle(@Param("fromuserid") long fromuserid, @Param("touserid") long touserid,
			@Param("seekintroductstatus") Integer seekintroductstatus);

	public int updateStatus(@Param("userid") long userid, @Param("seekintroductstatus") int seekintroductstatus);

	public int delete(String seekintroductid);
}
