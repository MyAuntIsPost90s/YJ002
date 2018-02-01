package yujian.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * (USERLOVERS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class UserLovers {
    
    /** 甲方编号 */
    private Long auserid;
    
    /** 乙方编号 */
    private Long buserid;
    
    /** 爱情基金 */
    private Float lovecost;
    
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date userlovedate;
    
    /**
     * 获取甲方编号
     * 
     * @return 甲方编号
     */
    public Long getAuserid() {
        return this.auserid;
    }
     
    /**
     * 设置甲方编号
     * 
     * @param auserid
     *          甲方编号
     */
    public void setAuserid(Long auserid) {
        this.auserid = auserid;
    }
    
    /**
     * 获取乙方编号
     * 
     * @return 乙方编号
     */
    public Long getBuserid() {
        return this.buserid;
    }
     
    /**
     * 设置乙方编号
     * 
     * @param buserid
     *          乙方编号
     */
    public void setBuserid(Long buserid) {
        this.buserid = buserid;
    }
    
    /**
     * 获取爱情基金
     * 
     * @return 爱情基金
     */
    public Float getLovecost() {
        return this.lovecost;
    }
     
    /**
     * 设置爱情基金
     * 
     * @param lovecost
     *          爱情基金
     */
    public void setLovecost(Float lovecost) {
        this.lovecost = lovecost;
    }
    
    public Date getUserlovedate() {
		return userlovedate;
	}
    public void setUserlovedate(Date userlovedate) {
		this.userlovedate = userlovedate;
	}
}