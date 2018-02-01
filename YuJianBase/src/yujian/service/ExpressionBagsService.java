package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.ExpressionBagsMapper;
import yujian.models.ExpressionBags;
import yujian.utilities.FileHelper;

@Service
public class ExpressionBagsService {
	@Autowired
	private ExpressionBagsMapper expressionBagsMapper;
	
	public List<ExpressionBags> getListByPage(int page,int pageSize){
		return expressionBagsMapper.getListByPage((page-1)*pageSize, pageSize);
	}
	public List<ExpressionBags> getExpressionBagsNoUser(int page,int pageSize,long userid){
		return expressionBagsMapper.getExpressionBagsNoUser((page-1)*pageSize, pageSize,userid);
	}
	public ExpressionBags getSingle(String expressionbagid){
		return expressionBagsMapper.getSingle(expressionbagid);
	}
	public long getCount(){
		return expressionBagsMapper.getCount();
	}
	public int add(ExpressionBags model){
		return expressionBagsMapper.add(model);
	}
	public int update(ExpressionBags model){
		return expressionBagsMapper.update(model);
	}
	public int delete(List<String> ids,String basePath){
		for(String id :ids){
			ExpressionBags model = getSingle(id);
			if(model.getExpressionbagurl()!=null&&!model.getExpressionbagurl().isEmpty()) {
				String filename = FileHelper.getFileName(model.getExpressionbagurl());
				if(filename.contains("?"))
					filename=filename.substring(0,filename.indexOf("?"));
				FileHelper.delete(basePath+filename);
			}
		}
		return expressionBagsMapper.delete(ids);
	}
}
