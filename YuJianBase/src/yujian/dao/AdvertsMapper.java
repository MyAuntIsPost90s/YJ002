package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.Adverts;

public interface AdvertsMapper {
	public List<Adverts> getListByPage(@Param("skip") long skip, @Param("take") long take);

	public Adverts getSingle(String advertid);

	public long getCount();

	public int add(Adverts model);

	public int update(Adverts model);

	public int delete(List<String> ids);
}
