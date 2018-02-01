package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.UserGiftsMapper;
import yujian.models.UserGifts;
import yujian.models.UserGiftsAndUG;
import yujian.models.UsersSortView;

@Service
public class UserGiftsService {
	@Autowired
	private UserGiftsMapper userGiftsMapper;
	
	public List<UserGiftsAndUG> getUserGifts(long userid,int page,int pageSize){
		return userGiftsMapper.getUserGifts(userid,(page-1)*pageSize , pageSize);
	}
	public List<UsersSortView> getUserGiftsRank(long userid,int page,int pageSize){
		return userGiftsMapper.getUserGiftsRank(userid,(page-1)*pageSize , pageSize);
	}
	public UsersSortView getMyGiftRank(long userid,long myid){
		return userGiftsMapper.getMyGiftRank(userid, myid);
	}
	
	public int add(UserGifts model) {
		return userGiftsMapper.add(model);
	}
	public int addMore(List<UserGifts> models) {
		return userGiftsMapper.addMore(models);
	}
	public int deleteByUserID(long userid) {
		return userGiftsMapper.deleteByUserID(userid);
	}
}
