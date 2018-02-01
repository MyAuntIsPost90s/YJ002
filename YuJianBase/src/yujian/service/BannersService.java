package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.BannersMapper;
import yujian.models.Banners;
import yujian.utilities.FileHelper;

@Service
public class BannersService {
	@Autowired
	private BannersMapper bannersMapper;
	
	public List<Banners> getList(Integer bannertype,String address){
		return bannersMapper.getList(bannertype,address);
	}
	public List<String> getAddress(){
		return bannersMapper.getAddress();
	}
	public Banners getSingle(String bannerid) {
		return bannersMapper.getSingle(bannerid);
	}
	public long getCount(Integer bannertype,String address) {
		return bannersMapper.getCount(bannertype,address);
	}
	public int add(Banners model) {
		return bannersMapper.add(model);
	}
	public int update (Banners model) {
		return bannersMapper.update(model);
	}
	public int delete(List<String> ids,String basePath) {
		for (String id : ids) {
			Banners model=bannersMapper.getSingle(id);
			String filename=FileHelper.getFileName(model.getBannerimgurl());
			if(filename.contains("?"))
				filename=filename.substring(0,filename.indexOf("?"));
			FileHelper.delete(basePath + filename);
		}
		return bannersMapper.delete(ids);
	}
}
