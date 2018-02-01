package yujian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.FeedBacks;
import yujian.models.FeedBacksAndUser;

public interface FeedBacksMapper {
	public List<FeedBacksAndUser> getListByPage(@Param("skip") long skip, @Param("take") long take,
			@Param("feedbacktype") int feedbacktype, @Param("feedbackstatus") int feedbackstatus);

	public List<FeedBacksAndUser> getListByIDAndType(@Param("userid") long userid,
			@Param("feedbacktype") int feedbacktype, @Param("feedbackstatus") int feedbackstatus);

	public FeedBacksAndUser getSingle(String feedbackid);

	public int getAllCount(@Param("feedbackstatus") int feedbackstatus);

	public long getCount(@Param("feedbacktype") int feedbacktype, @Param("feedbackstatus") int feedbackstatus);

	public int add(FeedBacks model);

	public int update(FeedBacks model);

	public int delete(@Param("ids") List<String> ids);
}
