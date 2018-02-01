package yujian.models;

/**
 * (USERINTRODUCES)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class UserIntroduces {
    
    /** 红人编号 */
    private Long uiuserid;
    
    /** 用户编号 */
    private Long userid;
    
    /** 红人状态 */
    private Integer userintroducestatus;
    
    /** 红人关系类型 **/
    private Integer userintroducetype;
    
    /**
     * 获取红人编号
     * 
     * @return 红人编号
     */
    public Long getUiuserid() {
        return this.uiuserid;
    }
     
    /**
     * 设置红人编号
     * 
     * @param uiuserid
     *          红人编号
     */
    public void setUiuserid(Long uiuserid) {
        this.uiuserid = uiuserid;
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
     * 获取红人状态
     * 
     * @return 红人状态
     */
    public Integer getUserintroducestatus() {
        return this.userintroducestatus;
    }
     
    /**
     * 设置红人状态
     * 
     * @param userintroducestatus
     *          红人状态
     */
    public void setUserintroducestatus(Integer userintroducestatus) {
        this.userintroducestatus = userintroducestatus;
    }
    
    public Integer getUserintroducetype() {
		return userintroducetype;
	}
    public void setUserintroducetype(Integer userintroducetype) {
		this.userintroducetype = userintroducetype;
	}
}