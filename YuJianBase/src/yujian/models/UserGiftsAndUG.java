package yujian.models;

import java.util.List;

public class UserGiftsAndUG extends UserGifts {
	private String realname;
	private String headimgurl;
	private String gifttitle;
	private String gifturl;
	private String giftcontent;
	private List<UserGiftsAndUG> gifts;
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getGifturl() {
		return gifturl;
	}
	public void setGifturl(String gifturl) {
		this.gifturl = gifturl;
	}
	public String getGifttitle() {
		return gifttitle;
	}
	public void setGifttitle(String gifttitle) {
		this.gifttitle = gifttitle;
	}
	public List<UserGiftsAndUG> getGifts() {
		return gifts;
	}
	public void setGifts(List<UserGiftsAndUG> gifts) {
		this.gifts = gifts;
	}
	public String getGiftcontent() {
		return giftcontent;
	}
	public void setGiftcontent(String giftcontent) {
		this.giftcontent = giftcontent;
	}
}
