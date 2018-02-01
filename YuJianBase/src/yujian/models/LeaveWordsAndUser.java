package yujian.models;

public class LeaveWordsAndUser extends LeaveWords {
	private String realname;
	private String headimgurl;
	private String torealname;
	private String toheadimgurl;
	private LeaveWordsAndUser leavewordsanduser;
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getTorealname() {
		return torealname;
	}
	public void setTorealname(String torealname) {
		this.torealname = torealname;
	}
	public String getToheadimgurl() {
		return toheadimgurl;
	}
	public void setToheadimgurl(String toheadimgurl) {
		this.toheadimgurl = toheadimgurl;
	}
	public LeaveWordsAndUser getLeavewordsanduser() {
		return leavewordsanduser;
	}
	public void setLeavewordsanduser(LeaveWordsAndUser leavewordsanduser) {
		this.leavewordsanduser = leavewordsanduser;
	}
}
