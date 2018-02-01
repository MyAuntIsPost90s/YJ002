package yujian.models;

/**
 * (EXPRESSIONS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class Expressions {
    
    /** 表情编号 */
    private String expressionid;
    
    /** 表情包描述 */
    private String expressionurl;
    
    /** 表情包编号 */
    private String expressionbagid;
    
    /**
     * 获取表情编号
     * 
     * @return 表情编号
     */
    public String getExpressionid() {
        return this.expressionid;
    }
     
    /**
     * 设置表情编号
     * 
     * @param expressionid
     *          表情编号
     */
    public void setExpressionid(String expressionid) {
        this.expressionid = expressionid;
    }
    
    /**
     * 获取表情包描述
     * 
     * @return 表情包描述
     */
    public String getExpressionurl() {
        return this.expressionurl;
    }
     
    /**
     * 设置表情包描述
     * 
     * @param expressionurl
     *          表情包描述
     */
    public void setExpressionurl(String expressionurl) {
        this.expressionurl = expressionurl;
    }
    
    /**
     * 获取表情包编号
     * 
     * @return 表情包编号
     */
    public String getExpressionbagid() {
        return this.expressionbagid;
    }
     
    /**
     * 设置表情包编号
     * 
     * @param expressionbagid
     *          表情包编号
     */
    public void setExpressionbagid(String expressionbagid) {
        this.expressionbagid = expressionbagid;
    }
}