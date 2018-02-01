package yujian.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserIntroducesAndUser extends UserIntroduces {
    /** 头像路径 */
    private String headimgurl;
    
    /** 地址（籍贯） */
    private String address;
    
    /** 手机号 */
    private String phone;
    
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
    
    /** -1红人用户 0普通用户 1VIP 2管理员 3临时占位*/
    private Integer usertype;
    
    /** 创建时间 */
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    
    /** 推荐人真实姓名 */
    private String uirealname;
    
    private int sex;
    
    /** 年纪 */
    @JSONField (format="yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  birthday;
    
    private String height;
    
    private String videourl;
    
    private String record;
    
    /** 用户签名 **/
    private String signcontent;
    
    /** 薪资 **/
    private String wage;
    
    /** 血型 **/
    private String bloodtype;
    
    /** 择偶条件 **/
    private String selectcondition;
    
    private String married;
    
    //不做映射
    private List<UserImgs> userimgs;
    
    public List<UserImgs> getUserimgs() {
		return userimgs;
	}
    public void setUserimgs(List<UserImgs> userimgs) {
		this.userimgs = userimgs;
	}
    public String getVideourl() {
		return videourl;
	}
    public void setVideourl(String videourl) {
		this.videourl = videourl;
	} 
    public String getUirealname() {
		return uirealname;
	}
    public void setUirealname(String uirealname) {
		this.uirealname = uirealname;
	}
    
    public int getSex() {
		return sex;
	}
    public void setSex(int sex) {
		this.sex = sex;
	}
    public String getHeight() {
		return height;
	}
    public void setHeight(String height) {
		this.height = height;
	}
   public Date getBirthday() {
	   return birthday;
   }
   public void setBirthday(Date birthday) {
	   this.birthday = birthday;
   }
    public String getRecord() {
		return record;
	}
    public void setRecord(String record) {
		this.record = record;
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
    public String getSigncontent() {
		return signcontent;
	}
    public void setSigncontent(String signcontent) {
		this.signcontent = signcontent;
	}
    public String getWage() {
		return wage;
	}
    public void setWage(String wage) {
		this.wage = wage;
	}
    public String getMarried() {
		return married;
	}
    public void setMarried(String married) {
		this.married = married;
	}
}
