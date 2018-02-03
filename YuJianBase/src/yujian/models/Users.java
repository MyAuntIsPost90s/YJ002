package yujian.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * (USERS)
 * 
 * @author bianj
 * @version 1.0.0 2017-09-04
 */
public class Users {
    
    /** 用户编号 */
    private Long userid;
    
    /** 头像路径 */
    private String headimgurl;
    
    /** 地址（籍贯） */
    private String address;
    
    /** 手机号 */
    private String phone;
    
    /** 密码 */
    private String password;
    
    /** 性别 */
    private int sex;
    
    /** 职业 */
    private String occupation;
    
    /** 爱好 */
    private String hobby;
    
    /** 真实姓名 */
    private String realname;
    
    /** 微信号 */
    private String weixinnum;
    
    /** qq号 */
    private String qqnum;
    
    /** 热度 */
    private Integer hotcount;
    
    /** -1红人用户 0普通用户 1VIP 2管理员  4超级管理员*/
    private Integer usertype;
    
    /** 金币数 */
    private Float goldbalance;
    
    /** 视频路径 */
    private String videourl;
    
    private int married;
    
    /** 创建时间 */
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    
    /** 财富 */
    private Long riches;
    
    /** 身高 */
    private String height;
    
    /** 年纪 */
    @JSONField (format="yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  birthday;
    
    /** 学历 **/
    private String record;
    
    /** 排序号 **/
    private int usersortindex;
    
    /** 微信UID **/
    private String weixinuid;
    
    /** 微信OpenID **/
    private String weixinoid;
    
    /** 用户签名 **/
    private String signcontent;
    
    /** 薪资 **/
    private String wage;
    
    /** 血型 **/
    private String bloodtype;
    
    /** 择偶条件 **/
    private String selectcondition;
    
    /** 红娘简介 **/
    private String matchmakerintroduct;
    
    /** 擅长领域 **/
    private String matchmakergoodat;
    
    /** 婚恋建议 **/
    private String matchmakeradvise;
    
    /** 职能 **/
    private int otherfunction;
    
    /** 红娘类型 **/
    private int matchmakertype;
    
    /** 自定义备注 **/
    private String remark;
    
    public Users() {
		this.address="";
		this.birthday=new Date();
		this.bloodtype="";
		this.createtime=new Date();
		this.goldbalance=(float)0;
		this.headimgurl="";
		this.height="";
		this.hobby="";
		this.hotcount=0;
		this.married=0;
		this.matchmakeradvise="";
		this.matchmakergoodat="";
		this.matchmakerintroduct="";
		this.matchmakertype=0;
		this.occupation="";
		this.otherfunction=0;
		this.password="";
		this.phone="";
		this.qqnum="";
		this.realname="";
		this.record="";
		this.riches=(long)0;
		this.selectcondition="";
		this.sex=0;
		this.signcontent="";
		this.userid=(long)0;
		this.usertype=0;
		this.usersortindex=0;
		this.videourl="";
		this.wage="";
		this.weixinnum="";
		this.weixinoid="";
		this.weixinuid="";
		this.remark="";
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
     * 获取头像路径
     * 
     * @return 头像路径
     */
    public String getHeadimgurl() {
        return this.headimgurl;
    }
     
    /**
     * 设置头像路径
     * 
     * @param headimgurl
     *          头像路径
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
    
    /**
     * 获取地址（籍贯）
     * 
     * @return 地址（籍贯）
     */
    public String getAddress() {
        return this.address;
    }
     
    /**
     * 设置地址（籍贯）
     * 
     * @param address
     *          地址（籍贯）
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * 获取手机号
     * 
     * @return 手机号
     */
    public String getPhone() {
        return this.phone;
    }
     
    /**
     * 设置手机号
     * 
     * @param phone
     *          手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * 获取密码
     * 
     * @return 密码
     */
    public String getPassword() {
        return this.password;
    }
     
    /**
     * 设置密码
     * 
     * @param password
     *          密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getSex() {
		return sex;
	}
    public void setSex(int sex) {
		this.sex = sex;
	}
    
    /**
     * 获取职业
     * 
     * @return 职业
     */
    public String getOccupation() {
        return this.occupation;
    }
     
    /**
     * 设置职业
     * 
     * @param occupation
     *          职业
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    
    /**
     * 获取爱好
     * 
     * @return 爱好
     */
    public String getHobby() {
        return this.hobby;
    }
     
    /**
     * 设置爱好
     * 
     * @param hobby
     *          爱好
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    
    /**
     * 获取真实姓名
     * 
     * @return 真实姓名
     */
    public String getRealname() {
        return this.realname;
    }
     
    /**
     * 设置真实姓名
     * 
     * @param realname
     *          真实姓名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }
    
    /**
     * 获取微信号
     * 
     * @return 微信号
     */
    public String getWeixinnum() {
        return this.weixinnum;
    }
     
    /**
     * 设置微信号
     * 
     * @param weixinnum
     *          微信号
     */
    public void setWeixinnum(String weixinnum) {
        this.weixinnum = weixinnum;
    }
    
    /**
     * 获取qq号
     * 
     * @return qq号
     */
    public String getQqnum() {
        return this.qqnum;
    }
     
    /**
     * 设置qq号
     * 
     * @param qqnum
     *          qq号
     */
    public void setQqnum(String qqnum) {
        this.qqnum = qqnum;
    }
    
    /**
     * 获取热度
     * 
     * @return 热度
     */
    public Integer getHotcount() {
        return this.hotcount;
    }
     
    /**
     * 设置热度
     * 
     * @param hotcount
     *          热度
     */
    public void setHotcount(Integer hotcount) {
        this.hotcount = hotcount;
    }
    
    /**
     * 获取-1红人用户 0普通用户 1VIP 2管理员
     * 
     * @return -1红人用户 0普通用户 1VIP 2管理员
     */
    public Integer getUsertype() {
        return this.usertype;
    }
     
    /**
     * 设置-1红人用户 0普通用户 1VIP 2管理员
     * 
     * @param usertype
     *          -1红人用户 0普通用户 1VIP 2管理员
     */
    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }
    
    /**
     * 获取金币数
     * 
     * @return 金币数
     */
    public Float getGoldbalance() {
        return this.goldbalance;
    }
     
    /**
     * 设置金币数
     * 
     * @param goldbalance
     *          金币数
     */
    public void setGoldbalance(Float goldbalance) {
        this.goldbalance = goldbalance;
    }
    
    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getCreatetime() {
        return this.createtime;
    }
     
    /**
     * 设置创建时间
     * 
     * @param createtime
     *          创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    
    /**
     * 获取财富
     * 
     * @return 财富
     */
    public Long getRiches() {
        return this.riches;
    }
     
    /**
     * 设置财富
     * 
     * @param riches
     *          财富
     */
    public void setRiches(Long riches) {
        this.riches = riches;
    }
    
    public Date getBirthday() {
		return birthday;
	}
    public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
    public String getHeight() {
		return height;
	}
    public void setHeight(String height) {
		this.height = height;
	}
    public String getVideourl() {
		return videourl;
	}
    public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
    public String getRecord() {
		return record;
	}
    public void setRecord(String record) {
		this.record = record;
	}
    public int getUsersortindex() {
		return usersortindex;
	}
    public void setUsersortindex(int usersortindex) {
		this.usersortindex = usersortindex;
	}
    public String getWeixinuid() {
		return weixinuid;
	}
    public void setWeixinuid(String weixinuid) {
		this.weixinuid = weixinuid;
	}
    public String getWeixinoid() {
		return weixinoid;
	}
    public void setWeixinoid(String weixinoid) {
		this.weixinoid = weixinoid;
	}
    public String getSigncontent() {
		return signcontent;
	}
    public void setSigncontent(String signcontent) {
		this.signcontent = signcontent;
	}
    public int getMarried() {
		return married;
	}
    public void setMarried(int married) {
		this.married = married;
	}
    public String getWage() {
		return wage;
	}
    public void setWage(String wage) {
		this.wage = wage;
	}
    public String getBloodtype() {
		return bloodtype;
	}
    public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}
    public String getSelectcondition() {
		return selectcondition;
	}
    public void setSelectcondition(String selectcondition) {
		this.selectcondition = selectcondition;
	}
    public String getMatchmakerintroduct() {
		return matchmakerintroduct;
	}
    public void setMatchmakerintroduct(String matchmakerintroduct) {
		this.matchmakerintroduct = matchmakerintroduct;
	}
    public String getMatchmakeradvise() {
		return matchmakeradvise;
	}
    public void setMatchmakeradvise(String matchmakeradvise) {
		this.matchmakeradvise = matchmakeradvise;
	}
    public String getMatchmakergoodat() {
		return matchmakergoodat;
	}
    public void setMatchmakergoodat(String matchmakergoodat) {
		this.matchmakergoodat = matchmakergoodat;
	}
    public int getOtherfunction() {
		return otherfunction;
	}
    public void setOtherfunction(int otherfunction) {
		this.otherfunction = otherfunction;
	}
    public int getMatchmakertype() {
		return matchmakertype;
	}
    public void setMatchmakertype(int matchmakertype) {
		this.matchmakertype = matchmakertype;
	}
    public String getRemark() {
		return remark;
	}
    public void setRemark(String remark) {
		this.remark = remark;
	}
}