package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.SeekIntroductLogsMapper;
import yujian.models.SeekIntroductLogs;
import yujian.models.SeekIntroductLogsAndUser;

@Service
public class SeekIntroductLogsService {
	@Autowired
	private SeekIntroductLogsMapper seekIntroductLogsMapper;
	
	public int add(SeekIntroductLogs model){
		return seekIntroductLogsMapper.add(model);
	}
	public List<SeekIntroductLogsAndUser> getList(int page,int pageSize,Integer userid,Integer relativeid){
		return seekIntroductLogsMapper.getList((page-1)*pageSize, pageSize, userid, relativeid);
	}
	public SeekIntroductLogsAndUser getSingle(String seekintroductlogid){
		return seekIntroductLogsMapper.getSingle(seekintroductlogid);
	}

	public int getCount(Integer userid,Integer relativeid){
		return seekIntroductLogsMapper.getCount(userid,relativeid);
	}
	public int update(SeekIntroductLogs model){
		return seekIntroductLogsMapper.update(model);
	}
	public int delete(List<String> ids){
		return seekIntroductLogsMapper.delete(ids);
	}
}
