package yujian.models;

/**
 * (EXPRESSIONBAGS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class ExpressionBags {
    
    /** 表情包编号 */
    private String expressionbagid;
    
    /** 表情包描述 */
    private String expressionbagtitle;
    
    /** 表情包价格 */
    private Float ebcost;
    
    /** 表情包图片路径 **/
    private String expressionbagurl;
    
    /** 是否为用户默认表情包 **/
    private int expressionbagisdefault;
    
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
    
    /**
     * 获取表情包描述
     * 
     * @return 表情包描述
     */
    public String getExpressionbagtitle() {
        return this.expressionbagtitle;
    }
     
    /**
     * 设置表情包描述
     * 
     * @param expressionbagtitle
     *          表情包描述
     */
    public void setExpressionbagtitle(String expressionbagtitle) {
        this.expressionbagtitle = expressionbagtitle;
    }
    
    /**
     * 获取表情包价格
     * 
     * @return 表情包价格
     */
    public Float getEbcost() {
        return this.ebcost;
    }
     
    /**
     * 设置表情包价格
     * 
     * @param ebcost
     *          表情包价格
     */
    public void setEbcost(Float ebcost) {
        this.ebcost = ebcost;
    }
    
    public String getExpressionbagurl() {
		return expressionbagurl;
	}
    
    public void setExpressionbagurl(String expressionbagurl) {
		this.expressionbagurl = expressionbagurl;
	}
    
    public int getExpressionbagisdefault() {
		return expressionbagisdefault;
	}
    public void setExpressionbagisdefault(int expressionbagisdefault) {
		this.expressionbagisdefault = expressionbagisdefault;
	}
}