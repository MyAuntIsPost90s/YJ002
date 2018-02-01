package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.UserImgsMapper;
import yujian.models.UserImgs;
import yujian.utilities.FileHelper;

@Service
public class UserImgsService {
	@Autowired
	private UserImgsMapper userImgsMapper;
	
	public List<UserImgs> getList(long userid){
		return userImgsMapper.getList(userid);
	}
	public UserImgs getSingle(String userimgid){
		return userImgsMapper.getSingle(userimgid);
	}
	public int add(UserImgs model){
		return userImgsMapper.add(model);
	}
	public int update(UserImgs model){
		return userImgsMapper.update(model);
	}
	public int delete(String userimgid,String basePath){
		try{
			UserImgs model = getSingle(userimgid);
			if(model.getUserimgurl()!=null&&!model.getUserimgurl().isEmpty()) {
				String filename = FileHelper.getFileName(model.getUserimgurl());
				if(filename.contains("?"))
					filename=filename.substring(0,filename.indexOf("?"));
				FileHelper.delete(basePath+filename);
				FileHelper.delete(basePath+"mini_"+filename);	//删除缩略图
			}
			return userImgsMapper.delete(userimgid);
		}catch (Exception e) {
			throw e;
		}
	}
	public int deleteByUserID(long userid,String basePath) throws Exception {
		List<UserImgs> list=getList(userid);
		for (UserImgs model : list) {
			if(model.getUserimgurl()!=null&&!model.getUserimgurl().isEmpty()) {
				String filename = FileHelper.getFileName(model.getUserimgurl());
				if(filename.contains("?"))
					filename=filename.substring(0,filename.indexOf("?"));
				FileHelper.delete(basePath+filename);
				FileHelper.delete(basePath+"mini_"+filename);	//删除缩略图
			}
		}
		return userImgsMapper.deleteByUserID(userid);
	}
}
