package yujian.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.UsersMapper;
import yujian.models.Users;
import yujian.models.UsersSortView;
import yujian.models.UsersView;
import yujian.utilities.FileHelper;

@Service
public class UsersService {
	@Autowired
	private UsersMapper usersMapper;
	
	public Users getSingleByPhone(String phone){
		return usersMapper.getSingleByPhone(phone);
	}
	public Users getSingleByUserID(long userid){
		return usersMapper.getSingleByUserID(userid);
	}
	public Users getSingleByWXUID(String weixinuid) {
		return usersMapper.getSingleByWXUID(weixinuid);
	}
	public Users getSingleByWXOID(String weixinoid) {
		return usersMapper.getSingleByWXOID(weixinoid);
	}
	public Users getSingleByMatchmakerType(int matchmakertype) {
		return usersMapper.getSingleByMatchmakerType(matchmakertype);
	}
	
	public List<UsersView> getListByPage(int page,int pageSize,String address,Date btime,Date etime
			,int sex,String wage,String record,String height,String bloodtype
			,Integer usertype,Long myuserid,Integer otherfunction,String condition){
		if(address!=null&&!address.isEmpty()) {
			address="%"+address+"%";
		}
		if(condition!=null&&!condition.isEmpty()) {
			condition="%"+condition+"%";
		}
		return usersMapper.getListByPage((page-1)*pageSize, pageSize, address, btime, etime, sex, record, wage, bloodtype, height, usertype, myuserid,otherfunction,condition);
	}
	
	public List<Users> searchListByPage(String condition,int page,int pageSize){
		if(condition!=null&&!condition.isEmpty()) {
			condition="%"+condition+"%";
		}
		return usersMapper.searchListByPage(condition,(page-1)*pageSize, pageSize);
	}
	
	public List<Users> getListByPageNoRed(int page,int pageSize,String condition,Integer otherfunction
			,String address,String record,String height,Integer sex,Integer usertype){
		if(condition!=null&&!condition.isEmpty()) {
			condition="%"+condition+"%";
		}
		if(address!=null&&!address.isEmpty()) {
			address="%"+address+"%";
		}
		return usersMapper.getListByPageNoRed((page-1)*pageSize, pageSize, condition, otherfunction, address, sex, record, usertype, height);
	}
	
	public List<Users> getListByPageOnlyRed(int page,int pageSize,String condition,String address){
		if(condition!=null&&!condition.isEmpty()) {
			condition="%"+condition+"%";
		}
		if(address!=null&&!address.isEmpty()) {
			address="%"+address+"%";
		}
		return usersMapper.getListByPageOnlyRed((page-1)*pageSize, pageSize, condition,address);
	}
	public List<UsersSortView> getListByHotCount(int page,int pageSize){
		List<UsersSortView> list=usersMapper.getListByHotCount((page-1)*pageSize, pageSize);
		if(list==null)
			return list;
		for(int i=0;i<list.size();i++) {
			list.get(i).setUsersortid((page-1)*pageSize+i+1);
		}
		return list;
	}
	public List<UsersSortView> getListByRiches(int page,int pageSize){
		List<UsersSortView> list=usersMapper.getListByRiches((page-1)*pageSize, pageSize);
		if(list==null)
			return list;
		for(int i=0;i<list.size();i++) {
			list.get(i).setUsersortid((page-1)*pageSize+i+1);
		}
		return list;
	}
	public List<String> getUserAddress(Integer usertype){
		return usersMapper.getUserAddress(usertype);
	}
	
	public UsersSortView getMyHotCountIndex(long userid) {
		return usersMapper.getMyHotCountIndex(userid);
	}
	public UsersSortView getMyRichesIndex(long userid) {
		return usersMapper.getMyRichesIndex(userid);
	}
	public long getCount(String condition,Integer otherfunction){
		if(condition!=null&&!condition.isEmpty()) {
			condition="%"+condition+"%";
		}
		
		return usersMapper.getCount(condition,otherfunction);
	}
	public long getCountNoRed(String condition,Integer otherfunction,String address,Integer sex,String record,Integer usertype,String height) {
		if(condition!=null&&!condition.isEmpty()) {
			condition="%"+condition+"%";
		}
		if(address!=null&&!address.isEmpty()) {
			address="%"+address+"%";
		}
		return usersMapper.getCountNoRed(condition, otherfunction, address, sex, record, usertype, height);
	}
	public long getCountOnlyRed(String condition,String address) {
		if(condition!=null&&!condition.isEmpty()) {
			condition="%"+condition+"%";
		}
		if(address!=null&&!address.isEmpty()) {
			address="%"+address+"%";
		}
		return usersMapper.getCountOnlyRed(condition,address);
	}
	public int add(Users model){
		return usersMapper.add(model);
	}
	public int update(Users model){
		return usersMapper.update(model);
	}
	public int delete(List<Long> ids,String basePath)throws Exception{
		for (Long id : ids) {
			Users model = usersMapper.getSingleByUserID(id);
			if(model.getHeadimgurl()!=null&&!model.getHeadimgurl().isEmpty()) {
				String filename=FileHelper.getFileName(model.getHeadimgurl());
				if(filename.contains("?"))
					filename=filename.substring(0,filename.indexOf("?"));
				
				FileHelper.delete(basePath+filename);
			}
			if(model.getVideourl()!=null&&!model.getVideourl().isEmpty()) {
				String filename=FileHelper.getFileName(model.getVideourl());
				if(filename.contains("?"))
					filename=filename.substring(0,filename.indexOf("?"));
				
				FileHelper.delete(basePath+filename);
			}
		}
		return usersMapper.delete(ids);
	}
	
	public int resetMatchmakertype() {
		return usersMapper.resetMatchmakertype();
	}
}
