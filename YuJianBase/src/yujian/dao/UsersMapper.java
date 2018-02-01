package yujian.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import yujian.models.Users;
import yujian.models.UsersSortView;
import yujian.models.UsersView;

public interface UsersMapper {
	public Users getSingleByPhone(String phone);

	public Users getSingleByWXUID(String weixinuid);

	public Users getSingleByWXOID(String weixinoid);

	public Users getSingleByUserID(long userid);

	public Users getSingleByMatchmakerType(int matchmakertype);

	public List<UsersView> getListByPage(@Param("skip") long skip, @Param("take") long take,
			@Param("address") String address, @Param("btime") Date btime, @Param("etime") Date etime,
			@Param("sex") Integer sex, @Param("record") String record, @Param("wage") String wage,
			@Param("bloodtype") String bloodtype, @Param("height") String height, @Param("usertype") Integer usertype,
			@Param("myuserid") Long myuserid, @Param("otherfunction") Integer otherfunction,
			@Param("condition") String condition);

	public List<Users> getListByPageNoRed(@Param("skip") long skip, @Param("take") long take,
			@Param("condition") String condition, @Param("otherfunction") Integer otherfunction,
			@Param("address") String address, @Param("sex") Integer sex, @Param("record") String record,
			@Param("usertype") Integer usertype, @Param("height") String height);

	public List<Users> getListByPageOnlyRed(@Param("skip") long skip, @Param("take") long take,
			@Param("condition") String condition, @Param("address") String address);

	public List<Users> searchListByPage(@Param("condition") String condition, @Param("skip") long skip,
			@Param("take") long take);

	public List<UsersSortView> getListByHotCount(@Param("skip") long skip, @Param("take") long take);

	public List<UsersSortView> getListByRiches(@Param("skip") long skip, @Param("take") long take);

	public List<String> getUserAddress(@Param("usertype") Integer usertype);

	public UsersSortView getMyHotCountIndex(long userid);

	public UsersSortView getMyRichesIndex(long userid);

	public long getCount(@Param("condition") String condition, @Param("otherfunction") Integer otherfunction);

	public long getCountNoRed(@Param("condition") String condition, @Param("otherfunction") Integer otherfunction,
			@Param("address") String address, @Param("sex") Integer sex, @Param("record") String record,
			@Param("usertype") Integer usertype, @Param("height") String height);

	public long getCountOnlyRed(@Param("condition") String condition, @Param("address") String address);

	public int add(Users model);

	public int update(Users model);

	public int delete(List<Long> ids);

	public int resetMatchmakertype();
}
