package yujian.dao;

import yujian.models.SysConfigs;

public interface SysConfigsMapper {
	public SysConfigs getSingle();

	public int add(SysConfigs model);

	public int update(SysConfigs model);
}
