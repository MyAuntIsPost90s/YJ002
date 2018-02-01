package yujian.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * (USERGIFTS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class UserGifts {
    
    /** 来源用户编号 */
    private Long fromuserid;
    
    /** 目标用户编号 */
    private Long touserid;
    
    /** 礼物路径 */
    private String giftid;
    
    /** 礼物数量 */
    private Integer giftcount;
    
    /** 消费总值 */
    private Float usergiftcost;
    
    /** 礼物时间 */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date usergifttime;
    
    private String groupid;
    
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
     *          来源用户编号
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
     *          目标用户编号
     */
    public void setTouserid(Long touserid) {
        this.touserid = touserid;
    }
    
    /**
     * 获取礼物路径
     * 
     * @return 礼物路径
     */
    public String getGiftid() {
        return this.giftid;
    }
     
    /**
     * 设置礼物路径
     * 
     * @param gifturl
     *          礼物路径
     */
    public void setGiftid(String giftid) {
        this.giftid = giftid;
    }
    
    /**
     * 获取礼物数量
     * 
     * @return 礼物数量
     */
    public Integer getGiftcount() {
        return this.giftcount;
    }
     
    /**
     * 设置礼物数量
     * 
     * @param giftcount
     *          礼物数量
     */
    public void setGiftcount(Integer giftcount) {
        this.giftcount = giftcount;
    }
    
    /**
     * 获取消费总值
     * 
     * @return 消费总值
     */
    public Float getUsergiftcost() {
        return this.usergiftcost;
    }
     
    /**
     * 设置消费总值
     * 
     * @param usergiftcost
     *          消费总值
     */
    public void setUsergiftcost(Float usergiftcost) {
        this.usergiftcost = usergiftcost;
    }
    
    public Date getUsergifttime() {
		return usergifttime;
	}
    public void setUsergifttime(Date usergifttime) {
		this.usergifttime = usergifttime;
	}
    public String getGroupid() {
		return groupid;
	}
    public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
}