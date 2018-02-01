package yujian.dao;

import java.util.List;

import yujian.models.UserImgs;

public interface UserImgsMapper {
	public List<UserImgs> getList(long userid);

	public UserImgs getSingle(String userimgid);

	public int add(UserImgs model);

	public int update(UserImgs model);

	public int delete(String userimgid);

	public int deleteByUserID(long userid);
}
