package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.AdvertsMapper;
import yujian.models.Adverts;
import yujian.utilities.FileHelper;

@Service
public class AdvertsService {
	@Autowired
	private AdvertsMapper advertsMapper;
	
	public List<Adverts> getListByPage(int page,int pageSize){
		return advertsMapper.getListByPage((page-1)*pageSize, pageSize);
	}
	public Adverts getSingle(String advertid){
		return advertsMapper.getSingle(advertid);
	}
	
	public long getCount(){
		return advertsMapper.getCount();
	}
	public int add(Adverts model){
		return advertsMapper.add(model);
	}
	public int update (Adverts model){
		return advertsMapper.update(model);
	}
	public int delete(List<String> ids,String basePath) throws Exception{
		for (String id : ids) {
			Adverts model=advertsMapper.getSingle(id);
			String filename=FileHelper.getFileName(model.getAdvertimgurl());
			String logoname=FileHelper.getFileName(model.getAdvertlogourl());
			if(filename.contains("?"))
				filename=filename.substring(0,filename.indexOf("?"));
			if(logoname.contains("?"))
				logoname=logoname.substring(0,logoname.indexOf("?"));
			FileHelper.delete(basePath + filename);
			FileHelper.delete(basePath + logoname);
		}
		return advertsMapper.delete(ids);
	}
}
