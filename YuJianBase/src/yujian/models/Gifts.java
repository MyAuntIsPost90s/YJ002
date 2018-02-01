package yujian.models;

/**
 * (GIFTS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class Gifts {
    
    /** 礼物编号 */
    private String giftid;
    
    /** 礼物路径 */
    private String gifturl;
    
    /** 礼物价格 */
    private Float giftcost;
    
    /** 礼物标题 **/
    private String gifttitle;
    
    /** 礼物内容 **/
    private String giftcontent;
    
    /**
     * 获取礼物编号
     * 
     * @return 礼物编号
     */
    public String getGiftid() {
        return this.giftid;
    }
     
    /**
     * 设置礼物编号
     * 
     * @param giftid
     *          礼物编号
     */
    public void setGiftid(String giftid) {
        this.giftid = giftid;
    }
    
    /**
     * 获取礼物路径
     * 
     * @return 礼物路径
     */
    public String getGifturl() {
        return this.gifturl;
    }
     
    /**
     * 设置礼物路径
     * 
     * @param gifturl
     *          礼物路径
     */
    public void setGifturl(String gifturl) {
        this.gifturl = gifturl;
    }
    
    /**
     * 获取礼物价格
     * 
     * @return 礼物价格
     */
    public Float getGiftcost() {
        return this.giftcost;
    }
     
    /**
     * 设置礼物价格
     * 
     * @param giftcost
     *          礼物价格
     */
    public void setGiftcost(Float giftcost) {
        this.giftcost = giftcost;
    }
    
    public String getGifttitle() {
		return gifttitle;
	}
    public void setGifttitle(String gifttitle) {
		this.gifttitle = gifttitle;
	}
    public String getGiftcontent() {
		return giftcontent;
	}
    public void setGiftcontent(String giftcontent) {
		this.giftcontent = giftcontent;
	}
}