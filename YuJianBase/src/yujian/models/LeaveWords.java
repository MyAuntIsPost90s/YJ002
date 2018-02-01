package yujian.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * (LEAVEWORDS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class LeaveWords {

	/** 留言编号 */
	private String leavewordid;

	/** 留言图片 */
	private String leavewordurl;

	/** 来源用户编号 */
	private Long fromuserid;

	/** 目标用户编号 */
	private Long touserid;

	/** 留言时间 **/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date leavewordtime;

	private long toleaveworduserid;

	private String parentid;

	/**
	 * 获取留言编号
	 * 
	 * @return 留言编号
	 */
	public String getLeavewordid() {
		return this.leavewordid;
	}

	/**
	 * 设置留言编号
	 * 
	 * @param leavewordid
	 *            留言编号
	 */
	public void setLeavewordid(String leavewordid) {
		this.leavewordid = leavewordid;
	}

	/**
	 * 获取留言图片
	 * 
	 * @return 留言图片
	 */
	public String getLeavewordurl() {
		return this.leavewordurl;
	}

	/**
	 * 设置留言图片
	 * 
	 * @param leavewordurl
	 *            留言图片
	 */
	public void setLeavewordurl(String leavewordurl) {
		this.leavewordurl = leavewordurl;
	}

	/**
	 * 获取来源用户编号
	 * 
	 * @return 来源用户编号
	 */
	public Long getFromuserid() {
		return this.fromuserid;
	}

	/**
	 * 设置来源用户编号
	 * 
	 * @param fromuserid
	 *            来源用户编号
	 */
	public void setFromuserid(Long fromuserid) {
		this.fromuserid = fromuserid;
	}

	/**
	 * 获取目标用户编号
	 * 
	 * @return 目标用户编号
	 */
	public Long getTouserid() {
		return this.touserid;
	}

	/**
	 * 设置目标用户编号
	 * 
	 * @param touserid
	 *            目标用户编号
	 */
	public void setTouserid(Long touserid) {
		this.touserid = touserid;
	}

	public Date getLeavewordtime() {
		return leavewordtime;
	}

	public void setLeavewordtime(Date leavewordtime) {
		this.leavewordtime = leavewordtime;
	}

	public long getToleaveworduserid() {
		return toleaveworduserid;
	}

	public void setToleaveworduserid(long toleaveworduserid) {
		this.toleaveworduserid = toleaveworduserid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
}