package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.UserSeekIntroductsMapper;
import yujian.models.UserSeekIntroducts;
import yujian.models.UserSeekIntroductsAndUser;

@Service
public class UserSeekIntroductsService {
	@Autowired
	private UserSeekIntroductsMapper userSeekIntroductsMapper;
	
	public int add(UserSeekIntroducts model){
		return userSeekIntroductsMapper.add(model);
	}
	public int delete(String userseekintroductid){
		return userSeekIntroductsMapper.delete(userseekintroductid);
	}
	public List<UserSeekIntroductsAndUser> getListByPage(int page,int pageSize,Long fromuserid){
		return userSeekIntroductsMapper.getListByPage((page-1)*pageSize, pageSize, fromuserid);
	}
}
