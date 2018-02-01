package yujian.models;

/**
 * (SYSCONFIGS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class SysConfigs {
    
    /** 系统配置编号 */
    private Long sysconfigid;
    
    /** 间隔广告数 */
    private Integer intervalnum;
    
    /** Vip折扣 */
    private Float vipdiscount;
    
    /** 上传照片数量 **/
    private int photocount;
    
    /** 赠送金币 **/
    private int sendgold;
    
    /** 分享点击返回地址 **/
    private String sharebackurl;
    
    /** 用户头像路径 **/
    private String userbgurl;
    
    /**
     * 获取系统配置编号
     * 
     * @return 系统配置编号
     */
    public Long getSysconfigid() {
        return this.sysconfigid;
    }
     
    /**
     * 设置系统配置编号
     * 
     * @param sysconfigid
     *          系统配置编号
     */
    public void setSysconfigid(Long sysconfigid) {
        this.sysconfigid = sysconfigid;
    }
    
    /**
     * 获取间隔广告数
     * 
     * @return 间隔广告数
     */
    public Integer getIntervalnum() {
        return this.intervalnum;
    }
     
    /**
     * 设置间隔广告数
     * 
     * @param intervalnum
     *          间隔广告数
     */
    public void setIntervalnum(Integer intervalnum) {
        this.intervalnum = intervalnum;
    }
    
    /**
     * 获取Vip折扣
     * 
     * @return Vip折扣
     */
    public Float getVipdiscount() {
        return this.vipdiscount;
    }
     
    /**
     * 设置Vip折扣
     * 
     * @param vipdiscount
     *          Vip折扣
     */
    public void setVipdiscount(Float vipdiscount) {
        this.vipdiscount = vipdiscount;
    }
    
    public int getPhotocount() {
		return photocount;
	}
    public void setPhotocount(int photocount) {
		this.photocount = photocount;
	}
    public int getSendgold() {
		return sendgold;
	}
    public void setSendgold(int sendgold) {
		this.sendgold = sendgold;
	}
    public String getSharebackurl() {
		return sharebackurl;
	}
    public void setSharebackurl(String sharebackurl) {
		this.sharebackurl = sharebackurl;
	}
    public String getUserbgurl() {
		return userbgurl;
	}
    public void setUserbgurl(String userbgurl) {
		this.userbgurl = userbgurl;
	}
}