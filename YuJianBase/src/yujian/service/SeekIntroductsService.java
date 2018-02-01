package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.SeekIntroductsMapper;
import yujian.models.SeekIntroductAndUser;
import yujian.models.SeekIntroducts;

@Service
public class SeekIntroductsService {
	@Autowired
	private SeekIntroductsMapper seekIntroductsMapper;

	public int add(SeekIntroducts model) {
		return seekIntroductsMapper.add(model);
	}

	public List<SeekIntroductAndUser> getList(int page, int pagesize, long userid, Integer seekintroductstatus) {
		return seekIntroductsMapper.getList((page - 1) * pagesize, pagesize, userid, seekintroductstatus);
	}

	public List<SeekIntroductAndUser> getSingleSeekList(int page, int pagesize, long userid,
			Integer seekintroductstatus) {
		return seekIntroductsMapper.getSingleSeekList((page - 1) * pagesize, pagesize, userid, seekintroductstatus);
	}

	public int getCount(long userid, Integer seekintroductstatus) {
		return seekIntroductsMapper.getCount(userid, seekintroductstatus);
	}

	public SeekIntroductAndUser getSingle(long fromuserid, long touserid, Integer seekintroductstatus) {
		return seekIntroductsMapper.getSingle(fromuserid, touserid, seekintroductstatus);
	}

	public int updateStatus(long userid, int seekintroductstatus) {
		return seekIntroductsMapper.updateStatus(userid, seekintroductstatus);
	}

	public int delete(String seekintroductid) {
		return seekIntroductsMapper.delete(seekintroductid);
	}
}
