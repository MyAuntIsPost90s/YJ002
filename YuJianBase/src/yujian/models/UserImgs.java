package yujian.models;

/**
 * (USERIMGS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class UserImgs {
    
    /** 用户图片编号 */
    private String userimgid;
    
    /** 用户编号 */
    private Long userid;
    
    /** 用户图片路径 */
    private String userimgurl;
    
    /**
     * 获取用户图片编号
     * 
     * @return 用户图片编号
     */
    public String getUserimgid() {
        return this.userimgid;
    }
     
    /**
     * 设置用户图片编号
     * 
     * @param userimgid
     *          用户图片编号
     */
    public void setUserimgid(String userimgid) {
        this.userimgid = userimgid;
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
    
    /**
     * 获取用户图片路径
     * 
     * @return 用户图片路径
     */
    public String getUserimgurl() {
        return this.userimgurl;
    }
     
    /**
     * 设置用户图片路径
     * 
     * @param userimgurl
     *          用户图片路径
     */
    public void setUserimgurl(String userimgurl) {
        this.userimgurl = userimgurl;
    }
}