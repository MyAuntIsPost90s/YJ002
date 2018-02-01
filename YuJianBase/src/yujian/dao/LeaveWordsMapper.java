package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.LeaveWords;
import yujian.models.LeaveWordsAndUser;

public interface LeaveWordsMapper {
	public List<LeaveWords> getToListByPage(@Param("touserid") long userid, @Param("skip") long skip,
			@Param("take") long take);

	public List<LeaveWordsAndUser> getLeaveWordsAndUserByPage(@Param("touserid") long userid,
			@Param("parentid") String parentid, @Param("skip") long skip, @Param("take") long take);

	public List<LeaveWordsAndUser> getLeaveWordsAndUserByParentId(@Param("parentid") String parentid,
			@Param("skip") long skip, @Param("take") long take);

	public LeaveWordsAndUser getSingle(String leavewordid);

	public int add(LeaveWords model);

	public int delete(String leavewordid);

	public int deleteByUserID(long userid);
}
