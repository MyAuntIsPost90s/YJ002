package yujian.models;

public class Banners {
	private String bannerid;
	private String bannerimgurl;
	private String bannerlink;
	private String address;
	private int bannertype;
	
	public String getBannerid() {
		return bannerid;
	}
	public void setBannerid(String bannerid) {
		this.bannerid = bannerid;
	}
	public String getBannerimgurl() {
		return bannerimgurl;
	}
	public void setBannerimgurl(String bannerimgurl) {
		this.bannerimgurl = bannerimgurl;
	}
	public String getBannerlink() {
		return bannerlink;
	}
	public void setBannerlink(String bannerlink) {
		this.bannerlink = bannerlink;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getBannertype() {
		return bannertype;
	}
	public void setBannertype(int bannertype) {
		this.bannertype = bannertype;
	}
}
