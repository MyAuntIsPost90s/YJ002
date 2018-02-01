package yujian.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * (ORDERS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class Orders {
    
    /** 订单编号 */
    private String orderid;
    
    /** 订单花费 */
    private Float ordercost;
    
    /** 订单产生时间 */
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderdate;
    
    /** 1表情 2礼物 3VIp 4基金 */
    private Integer ordertype;
    
    /** 用户编号 */
    private Long userid;
    
    /**
     * 获取订单编号
     * 
     * @return 订单编号
     */
    public String getOrderid() {
        return this.orderid;
    }
     
    /**
     * 设置订单编号
     * 
     * @param orderid
     *          订单编号
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    
    /**
     * 获取订单花费
     * 
     * @return 订单花费
     */
    public Float getOrdercost() {
        return this.ordercost;
    }
     
    /**
     * 设置订单花费
     * 
     * @param ordercost
     *          订单花费
     */
    public void setOrdercost(Float ordercost) {
        this.ordercost = ordercost;
    }
    
    /**
     * 获取订单产生时间
     * 
     * @return 订单产生时间
     */
    public Date getOrderdate() {
        return this.orderdate;
    }
     
    /**
     * 设置订单产生时间
     * 
     * @param orderdate
     *          订单产生时间
     */
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }
    
    /**
     * 获取1表情 2礼物 3VIp 4基金
     * 
     * @return 1表情 2礼物 3VIp 4基金
     */
    public Integer getOrdertype() {
        return this.ordertype;
    }
     
    /**
     * 设置1表情 2礼物 3VIp 4基金
     * 
     * @param ordertype
     *          1表情 2礼物 3VIp 4基金
     */
    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }
    
    /**
     * 获取用户编号
     * 
     * @return 用户编号
     */
    public Long getUserid() {
        return this.userid;
    }
     
    /**
     * 设置用户编号
     * 
     * @param userid
     *          用户编号
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }
}