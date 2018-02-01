package yujian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.SysConfigsMapper;
import yujian.models.SysConfigs;

@Service
public class SysConfigsService {
	@Autowired
	private SysConfigsMapper sysConfigsMapper;
	
	public SysConfigs getSingle(){
		return sysConfigsMapper.getSingle();
	}
	public int add(SysConfigs model){
		return sysConfigsMapper.add(model);
	}
	public int update(SysConfigs model){
		return sysConfigsMapper.update(model);
	}
}
