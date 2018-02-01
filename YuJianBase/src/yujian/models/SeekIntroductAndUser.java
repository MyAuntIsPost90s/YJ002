package yujian.models;

public class SeekIntroductAndUser extends SeekIntroducts {
	private String fromrealname;
	private int fromsex;
	private String fromheadimgurl;
	private String torealname;
	private String toheadimgurl;
	private int tosex;
	
	public String getTorealname() {
		return torealname;
	}
	public void setTorealname(String torealname) {
		this.torealname = torealname;
	}
	public String getFromrealname() {
		return fromrealname;
	}
	public void setFromrealname(String fromrealname) {
		this.fromrealname = fromrealname;
	}
	public String getFromheadimgurl() {
		return fromheadimgurl;
	}
	public void setFromheadimgurl(String fromheadimgurl) {
		this.fromheadimgurl = fromheadimgurl;
	}
	public String getToheadimgurl() {
		return toheadimgurl;
	}
	public void setToheadimgurl(String toheadimgurl) {
		this.toheadimgurl = toheadimgurl;
	}
	public int getFromsex() {
		return fromsex;
	}
	public void setFromsex(int fromsex) {
		this.fromsex = fromsex;
	}
	public int getTosex() {
		return tosex;
	}
	public void setTosex(int tosex) {
		this.tosex = tosex;
	}
}
