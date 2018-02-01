package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.ExpressionsMapper;
import yujian.models.Expressions;
import yujian.utilities.FileHelper;

@Service
public class ExpressionsService {
	@Autowired
	private ExpressionsMapper expressionsMapper;
	
	public List<Expressions> getList(String expressionbagid){
		return expressionsMapper.getList(expressionbagid);
	}
	public List<Expressions> getListByEBIDs(List<String> ids){
		return expressionsMapper.getListByEBIDs(ids);
	}
	public Expressions getSingle(String expressionid){
		return expressionsMapper.getSingle(expressionid);
	}
	public int add(Expressions model){
		return expressionsMapper.add(model);
	}
	public int delete(String expressionid,String basePath){
		try{
			Expressions model=getSingle(expressionid);
			if(model.getExpressionurl()!=null&&!model.getExpressionurl().isEmpty()) {
				String filename = FileHelper.getFileName(model.getExpressionurl());
				if(filename.contains("?"))
					filename=filename.substring(0,filename.indexOf("?"));
				FileHelper.delete(basePath+filename);
			}
			return expressionsMapper.delete(expressionid);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public int deleteByExpressionBagID(String expressionbagid,String basePath) {
		List<Expressions> list=getList(expressionbagid);
		for (Expressions expressions : list) {
			if(expressions.getExpressionurl()!=null&&!expressions.getExpressionurl().isEmpty()) {
				String filename = FileHelper.getFileName(expressions.getExpressionurl());
				if(filename.contains("?"))
					filename=filename.substring(0,filename.indexOf("?"));
				FileHelper.delete(basePath+filename);
			}
		}
		return expressionsMapper.deleteByExpressionBagID(expressionbagid);
	}
}
