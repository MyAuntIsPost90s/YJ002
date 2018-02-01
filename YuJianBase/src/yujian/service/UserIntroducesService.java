package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.UserIntroducesMapper;
import yujian.models.UserIntroduces;
import yujian.models.UserIntroducesAndUser;

@Service
public class UserIntroducesService {
	@Autowired
	private UserIntroducesMapper userIntroducesMapper;
	
	public List<UserIntroducesAndUser> getListByPage(int page
			,int pageSize,String condition,Integer userintroducestatus
			,Integer userintroducetype,Long userid
			,String address){
		if(condition!=null&&!condition.isEmpty()) {
			condition="%"+condition+"%";
		}
		if(address!=null&&!address.isEmpty()) {
			address="%"+address+"%";
		}
		return userIntroducesMapper.getListByPage((page-1)*pageSize
				, pageSize, condition,userintroducestatus,userintroducetype,userid,address);
	}
	public UserIntroducesAndUser getSingle(long uiuserid) {
		return userIntroducesMapper.getSingle(uiuserid);
	}
	public long getCount(String condition,Integer userintroducestatus,Integer userintroducetype,String address){
		if(condition!=null&&!condition.isEmpty()) {
			condition="%"+condition+"%";
		}
		if(address!=null&&!address.isEmpty()) {
			address="%"+address+"%";
		}
		return userIntroducesMapper.getCount(condition,userintroducestatus,userintroducetype,address);
	}
	public int addMore(List<UserIntroduces> list) {
		return userIntroducesMapper.addMore(list);
	}
	public int add(UserIntroduces model){
		return userIntroducesMapper.add(model);
	}
	public int update(UserIntroduces model) {
		return userIntroducesMapper.update(model);
	}
	public int delete(UserIntroduces model){
		return userIntroducesMapper.delete(model);
	}
	public int deleteMore(List<UserIntroduces> list) {
		return userIntroducesMapper.deleteMore(list);
	}
}
