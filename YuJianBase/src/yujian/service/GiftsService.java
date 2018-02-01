package yujian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yujian.dao.GiftsMapper;
import yujian.models.Gifts;
import yujian.utilities.FileHelper;

@Service
public class GiftsService {
	@Autowired
	private GiftsMapper giftsMapper;

	public List<Gifts> getListByPage(long page, long pageSize) {
		return giftsMapper.getListByPage((page - 1) * pageSize, pageSize);
	}

	public List<Gifts> getListByIDs(List<String> ids) {
		return giftsMapper.getListByIDs(ids);
	}

	public Gifts getSingle(String giftid) {
		return giftsMapper.getSingle(giftid);
	}

	public long getCount() {
		return giftsMapper.getCount();
	}

	public int add(Gifts model) {
		return giftsMapper.add(model);
	}

	public int update(Gifts model) {
		return giftsMapper.update(model);
	}

	public int delete(List<String> ids, String basePath) {
		try {
			for (String id : ids) {
				Gifts model = getSingle(id);
				if (model.getGifturl() != null && !model.getGifturl().isEmpty()) {
					String filename = FileHelper.getFileName(model.getGifturl());
					if (filename.contains("?")){
						filename = filename.substring(0, filename.indexOf("?"));
					}
					FileHelper.delete(basePath + filename);
				}
			}
			return giftsMapper.delete(ids);
		} catch (Exception e) {
			throw e;
		}
	}
}
