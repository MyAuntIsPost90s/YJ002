package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.SeekIntroductLogs;
import yujian.models.SeekIntroductLogsAndUser;

public interface SeekIntroductLogsMapper {
	public int add(SeekIntroductLogs model);

	public List<SeekIntroductLogsAndUser> getList(@Param("skip") int skip, @Param("take") int take,
			@Param("userid") Integer userid, @Param("relativeid") Integer relativeid);

	public SeekIntroductLogsAndUser getSingle(String seekintroductlogid);

	public int getCount(@Param("userid") Integer userid, @Param("relativeid") Integer relativeid);

	public int update(SeekIntroductLogs model);

	public int delete(@Param("ids") List<String> ids);
}
