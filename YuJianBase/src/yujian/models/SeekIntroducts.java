package yujian.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SeekIntroducts {
	private String seekintroductid;
	private long fromuserid;
	private long touserid;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date seekintroducttime;
	private long userid;
	private int seekintroductstatus;
	private int seekintroducttype;
	
	public long getFromuserid() {
		return fromuserid;
	}
	public void setFromuserid(long fromuserid) {
		this.fromuserid = fromuserid;
	}
	public String getSeekintroductid() {
		return seekintroductid;
	}
	public void setSeekintroductid(String seekintroductid) {
		this.seekintroductid = seekintroductid;
	}
	public Date getSeekintroducttime() {
		return seekintroducttime;
	}
	public void setSeekintroducttime(Date seekintroducttime) {
		this.seekintroducttime = seekintroducttime;
	}
	public long getTouserid() {
		return touserid;
	}
	public void setTouserid(long touserid) {
		this.touserid = touserid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public int getSeekintroductstatus() {
		return seekintroductstatus;
	}
	public void setSeekintroductstatus(int seekintroductstatus) {
		this.seekintroductstatus = seekintroductstatus;
	}
	public int getSeekintroducttype() {
		return seekintroducttype;
	}
	public void setSeekintroducttype(int seekintroducttype) {
		this.seekintroducttype = seekintroducttype;
	}
}
