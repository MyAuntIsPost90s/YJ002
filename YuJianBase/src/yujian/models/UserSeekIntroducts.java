package yujian.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserSeekIntroducts {
	private String userseekintroductid;
	private long fromuserid;
	private long touserid;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date userseekintroducttime;
	
    public long getFromuserid() {
		return fromuserid;
	}
    public void setFromuserid(long fromuserid) {
		this.fromuserid = fromuserid;
	}
    public long getTouserid() {
		return touserid;
	}
    public void setTouserid(long touserid) {
		this.touserid = touserid;
	}
    public String getUserseekintroductid() {
		return userseekintroductid;
	}
    public void setUserseekintroductid(String userseekintroductid) {
		this.userseekintroductid = userseekintroductid;
	}
    public Date getUserseekintroducttime() {
		return userseekintroducttime;
	}
    public void setUserseekintroducttime(Date userseekintroducttime) {
		this.userseekintroducttime = userseekintroducttime;
	}
}
