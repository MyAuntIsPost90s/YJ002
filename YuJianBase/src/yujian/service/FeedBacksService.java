package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.FeedBacksMapper;
import yujian.models.FeedBacks;
import yujian.models.FeedBacksAndUser;

@Service
public class FeedBacksService {
	@Autowired
	private FeedBacksMapper feedBacksMapper;
	
	public List<FeedBacksAndUser> getListByPage(int page,int pageSize,int feedbacktype,int feedbackstatus,String address){
		if(address!=null&&!address.isEmpty()){
			address="%"+address+"%";
		}
		return feedBacksMapper.getListByPage((page-1)*pageSize, pageSize, feedbacktype, feedbackstatus,address);
	}
	public List<FeedBacksAndUser> getListByIDAndType(long userid,int feedbacktype,int feedbackstatus){
		return feedBacksMapper.getListByIDAndType(userid, feedbacktype,feedbackstatus);
	}
	public int getAllCount(String address,int feedbackstatus) {
		if(address!=null&&!address.isEmpty()){
			address="%"+address+"%";
		}
		return feedBacksMapper.getAllCount(address,feedbackstatus);
	}
	public FeedBacksAndUser getSingle(String feedbackid) {
		return feedBacksMapper.getSingle(feedbackid);
	}
	public long getCount(String address,int feedbacktype,int feedbackstatus) {
		if(address!=null&&!address.isEmpty()){
			address="%"+address+"%";
		}
		return feedBacksMapper.getCount(address,feedbacktype, feedbackstatus);
	}
	public int add(FeedBacks model) {
		return feedBacksMapper.add(model);
	}
	public int update(FeedBacks model) {
		return feedBacksMapper.update(model);
	}
	public int delete(List<String> ids) {
		return feedBacksMapper.delete(ids);
	}
}
