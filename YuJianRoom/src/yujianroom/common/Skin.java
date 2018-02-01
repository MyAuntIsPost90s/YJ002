package yujianroom.common;

import javax.servlet.http.HttpServletRequest;

public class Skin {
	public final static String USER="user";
	public final static String DESKEY="yujian201796";
	
	public final static String REAL_HEADIMG_PATH="Contents/UploadFile/HeadImgs/";
	public final static String REAL_PHOTO_PATH="Contents/UploadFile/UserPhotos/";
	public final static String REAL_GIFT_PATH="Contents/UploadFile/Gifts/";
	public final static String REAL_EXPRESSION_PATH="Contents/UploadFile/Expressions/";
	public final static String REAL_EXPRESSIONBAG_PATH="Contents/UploadFile/ExpressionBags/";
	public final static String REAL_ADVERT_PATH="Contents/UploadFile/Adverts/";
	public final static String REAL_BANNER_PATH="Contents/UploadFile/Banners/";
	public final static String REAL_SYSCONFIG_PATH="Contents/UploadFile/SysConfigs/";
	
	public static String getVirtualPath(HttpServletRequest request,String realPath) {
		return request.getServletContext().getContextPath()+"/"+realPath;
	}
}
