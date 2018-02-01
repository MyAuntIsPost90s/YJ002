package yujian.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserSeekIntroductsAndUser extends UserSeekIntroducts {
	private String fromrealname;
	private int fromsex;
	private String fromheadimgurl;
	private String torealname;
	private String toheadimgurl;
	@JSONField (format="yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tobirthday;
	private String tosigncontent;
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
	public Date getTobirthday() {
		return tobirthday;
	}
	public void setTobirthday(Date tobirthday) {
		this.tobirthday = tobirthday;
	}
	public String getTosigncontent() {
		return tosigncontent;
	}
	public void setTosigncontent(String tosigncontent) {
		this.tosigncontent = tosigncontent;
	}
}
