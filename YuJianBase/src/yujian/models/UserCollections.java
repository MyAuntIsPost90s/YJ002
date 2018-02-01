package yujian.models;

/**
 * (USERCOLLECTIONS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class UserCollections {
    
    /** 用户编号 */
    private Long userid;
    
    /** 被收藏用户编号 */
    private Long usercollectionid;
    
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
     * 获取被收藏用户编号
     * 
     * @return 被收藏用户编号
     */
    public Long getUsercollectionid() {
        return this.usercollectionid;
    }
     
    /**
     * 设置被收藏用户编号
     * 
     * @param usercollectionid
     *          被收藏用户编号
     */
    public void setUsercollectionid(Long usercollectionid) {
        this.usercollectionid = usercollectionid;
    }
}