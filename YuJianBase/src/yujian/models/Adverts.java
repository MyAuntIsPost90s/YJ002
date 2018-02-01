package yujian.models;

/**
 * (ADVERTS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class Adverts {
    
    /** 广告编号 */
    private String advertid;
    
    /** 广告图片路径 */
    private String advertimgurl;
    
    /** 广告标题 */
    private String adverttitle;
    
    /** 广告链接 */
    private String advertlink;
    
    /** 广告logo链接 */
    private String advertlogourl;
    
    /** 广告内容 */
    private String advertcontent;
    
    /** 广告排序号 */
    private int advertsortindex;
    
    /**
     * 获取广告编号
     * 
     * @return 广告编号
     */
    public String getAdvertid() {
        return this.advertid;
    }
     
    /**
     * 设置广告编号
     * 
     * @param advertid
     *          广告编号
     */
    public void setAdvertid(String advertid) {
        this.advertid = advertid;
    }
    
    /**
     * 获取广告图片路径
     * 
     * @return 广告图片路径
     */
    public String getAdvertimgurl() {
        return this.advertimgurl;
    }
     
    /**
     * 设置广告图片路径
     * 
     * @param advertimgurl
     *          广告图片路径
     */
    public void setAdvertimgurl(String advertimgurl) {
        this.advertimgurl = advertimgurl;
    }
    
    /**
     * 获取广告标题
     * 
     * @return 广告标题
     */
    public String getAdverttitle() {
        return this.adverttitle;
    }
     
    /**
     * 设置广告标题
     * 
     * @param adverttitle
     *          广告标题
     */
    public void setAdverttitle(String adverttitle) {
        this.adverttitle = adverttitle;
    }
    
    /**
     * 获取广告链接
     * 
     * @return 广告链接
     */
    public String getAdvertlink() {
        return this.advertlink;
    }
     
    /**
     * 设置广告链接
     * 
     * @param advertlink
     *          广告链接
     */
    public void setAdvertlink(String advertlink) {
        this.advertlink = advertlink;
    }
    
    public String getAdvertcontent() {
		return advertcontent;
	}
    public void setAdvertcontent(String advertcontent) {
		this.advertcontent = advertcontent;
	}
    public String getAdvertlogourl() {
		return advertlogourl;
	}
    public void setAdvertlogourl(String advertlogourl) {
		this.advertlogourl = advertlogourl;
	}
    public int getAdvertsortindex() {
		return advertsortindex;
	}
    public void setAdvertsortindex(int advertsortindex) {
		this.advertsortindex = advertsortindex;
	}
}