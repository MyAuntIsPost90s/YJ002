package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.Gifts;

public interface GiftsMapper {
	public List<Gifts> getListByPage(@Param("skip") long skip, @Param("take") long take);

	public List<Gifts> getListByIDs(@Param("ids") List<String> ids);

	public Gifts getSingle(String giftid);

	public long getCount();

	public int add(Gifts model);

	public int update(Gifts model);

	public int delete(List<String> ids);
}
