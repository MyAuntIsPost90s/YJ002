package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.UserLoversMapper;
import yujian.models.UserLovers;
import yujian.models.UserLovserAndABUser;

@Service
public class UserLoversService {
	@Autowired
	private UserLoversMapper userLoversMapper;
	
	public List<UserLovserAndABUser> getListByPage(int page,int pageSize,String condition){
		if(condition!=null&&!condition.isEmpty()) {
			condition="%"+condition+"%";
		}
		return userLoversMapper.getListByPage((page-1)*pageSize, pageSize, condition);
	}
	public long getCount(String condition){
		if(condition!=null&&!condition.isEmpty()) {
			condition="%"+condition+"%";
		}
		return userLoversMapper.getCount(condition);
	}
	public UserLovserAndABUser getSingle(UserLovers model) {
		return userLoversMapper.getSingle(model);
	}
	public int add(UserLovers model){
		return userLoversMapper.add(model);
	}
	public int update(UserLovers model){
		return userLoversMapper.update(model);
	}
	public int delete(UserLovers model){
		return userLoversMapper.delete(model);
	}
}
