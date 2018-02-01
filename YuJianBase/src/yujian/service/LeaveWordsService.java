package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.LeaveWordsMapper;
import yujian.models.LeaveWords;
import yujian.models.LeaveWordsAndUser;
import yujian.utilities.FileHelper;

@Service
public class LeaveWordsService {
	@Autowired
	private LeaveWordsMapper leaveWordsMapper;

	public List<LeaveWords> getToListByPage(long userid, int page, int pageSize) {
		return leaveWordsMapper.getToListByPage(userid, (page - 1) * pageSize, pageSize);
	}

	public List<LeaveWordsAndUser> getLeaveWordsAndUserByPage(long userid, String parentid, int page, int pageSize) {
		return leaveWordsMapper.getLeaveWordsAndUserByPage(userid, parentid, (page - 1) * pageSize, pageSize);
	}

	public List<LeaveWordsAndUser> getLeaveWordsAndUserByParentId(String parentid, int page, int pageSize) {
		return leaveWordsMapper.getLeaveWordsAndUserByParentId(parentid, (page - 1) * pageSize, pageSize);
	}

	public LeaveWordsAndUser getSingle(String leavewordid) {
		return leaveWordsMapper.getSingle(leavewordid);
	}

	public int add(LeaveWords model) {
		return leaveWordsMapper.add(model);
	}

	public int delete(String leavewordid, String basePath) {
		LeaveWords model = leaveWordsMapper.getSingle(leavewordid);
		if (model.getLeavewordurl() != null && !model.getLeavewordurl().isEmpty()) {
			String filename = FileHelper.getFileName(model.getLeavewordurl());
			if (filename.contains("?")) {
				filename = filename.substring(0, filename.indexOf("?"));
			}
			FileHelper.delete(basePath + filename);
		}
		return leaveWordsMapper.delete(leavewordid);
	}

	public int deleteByUserID(long userid, String basePath) {
		List<LeaveWords> list = getToListByPage(userid, 1, 999);
		for (LeaveWords model : list) {
			if (model.getLeavewordurl() != null && !model.getLeavewordurl().isEmpty()) {
				String filename = FileHelper.getFileName(model.getLeavewordurl());
				if (filename.contains("?"))
					filename = filename.substring(0, filename.indexOf("?"));
				FileHelper.delete(basePath + filename);
			}
		}
		return leaveWordsMapper.deleteByUserID(userid);
	}
}
