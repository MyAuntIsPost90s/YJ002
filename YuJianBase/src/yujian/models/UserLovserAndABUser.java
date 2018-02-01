package yujian.models;

public class UserLovserAndABUser extends UserLovers {
	private String aphone;
	private String arealname;
	private String bphone;
	private String brealname;
	
	public String getArealname() {
		return arealname;
	}
	public void setArealname(String arealname) {
		this.arealname = arealname;
	}
	public String getBrealname() {
		return brealname;
	}
	public void setBrealname(String brealname) {
		this.brealname = brealname;
	}
	public String getBphone() {
		return bphone;
	}
	public void setBphone(String bphone) {
		this.bphone = bphone;
	}
	public String getAphone() {
		return aphone;
	}
	public void setAphone(String aphone) {
		this.aphone = aphone;
	}
}
