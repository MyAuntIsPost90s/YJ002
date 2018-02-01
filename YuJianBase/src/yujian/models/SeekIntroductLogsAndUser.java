package yujian.models;

public class SeekIntroductLogsAndUser extends SeekIntroductLogs {
	private String fromrealname;
	private int fromcount;
	private String torealname;
	private int tocount;
	private String realname;
	private int count;
	
	public String getFromrealname() {
		return fromrealname;
	}
	public void setFromrealname(String fromrealname) {
		this.fromrealname = fromrealname;
	}
	public String getTorealname() {
		return torealname;
	}
	public void setTorealname(String torealname) {
		this.torealname = torealname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getFromcount() {
		return fromcount;
	}
	public void setFromcount(int fromcount) {
		this.fromcount = fromcount;
	}
	public int getTocount() {
		return tocount;
	}
	public void setTocount(int tocount) {
		this.tocount = tocount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
