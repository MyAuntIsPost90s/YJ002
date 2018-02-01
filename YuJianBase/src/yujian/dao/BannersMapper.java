package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.Banners;

public interface BannersMapper {
	public List<Banners> getList(@Param("bannertype") Integer bannertype, @Param("address") String address);

	public List<String> getAddress();

	public Banners getSingle(String bannerid);

	public long getCount(@Param("bannertype") Integer bannertype, @Param("address") String address);

	public int add(Banners model);

	public int update(Banners model);

	public int delete(List<String> ids);
}
