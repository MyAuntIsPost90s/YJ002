package yujian.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class FeedBacks {
	private String feedbackid;
	private String feedbackcontent;
	
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date feedbacktime;
    
    private int feedbackresult;
	
	//0.告白 1.求婚 2.其他 3.反馈
	private int feedbacktype;
	private long userid;
	//0.未处理 1.已处理
	private int feedbackstatus;
	
	public String getFeedbackcontent() {
		return feedbackcontent;
	}
	public void setFeedbackcontent(String feedbackcontent) {
		this.feedbackcontent = feedbackcontent;
	}
	public String getFeedbackid() {
		return feedbackid;
	}
	public void setFeedbackid(String feedbackid) {
		this.feedbackid = feedbackid;
	}
	public Date getFeedbacktime() {
		return feedbacktime;
	}
	public void setFeedbacktime(Date feedbacktime) {
		this.feedbacktime = feedbacktime;
	}
	public int getFeedbacktype() {
		return feedbacktype;
	}
	public void setFeedbacktype(int feedbacktype) {
		this.feedbacktype = feedbacktype;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public int getFeedbackstatus() {
		return feedbackstatus;
	}
	public void setFeedbackstatus(int feedbackStatus) {
		this.feedbackstatus = feedbackStatus;
	}
	public int getFeedbackresult() {
		return feedbackresult;
	}
	public void setFeedbackresult(int feedbackresult) {
		this.feedbackresult = feedbackresult;
	}
}
