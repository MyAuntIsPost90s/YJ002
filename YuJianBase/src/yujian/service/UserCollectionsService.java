package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.UserCollectionsMapper;
import yujian.models.UserCollectionAndUser;
import yujian.models.UserCollections;

@Service
public class UserCollectionsService {
	@Autowired
	private UserCollectionsMapper userCollectionsMapper;
	
	public List<UserCollectionAndUser> getListByPage(int page,int pageSize,long userid){
		return userCollectionsMapper.getListByPage((page-1)*pageSize, pageSize, userid);
	}
	public int add(UserCollections model){
		return userCollectionsMapper.add(model);
	}
	public int delete(UserCollections model){
		return userCollectionsMapper.delete(model);
	}
	public int deleteByUserID(long userid) {
		return userCollectionsMapper.deleteByUserID(userid);
	}
}
