package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.UserExpressionBagsMapper;
import yujian.models.ExpressionBags;
import yujian.models.UserExpressionBags;

@Service
public class UserExpressionBagsService {
	@Autowired
	private UserExpressionBagsMapper userExpressionBagsMapper;
	
	public List<ExpressionBags> getList(long userid){
		return userExpressionBagsMapper.getList(userid);
	}

	public int add(UserExpressionBags model) {
		return userExpressionBagsMapper.add(model);
	}
	
}
