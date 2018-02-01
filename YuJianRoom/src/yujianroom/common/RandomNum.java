package yujianroom.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomNum {
	private static int index=10;
	public static String getGiftID(){
		return "G"+getRandom();
	}
	public static String getEBID() {
		return "EB"+getRandom();
	}
	public static String getExpressionID(){
		return "E"+getRandom();
	}
	public static String getAdvertID() {
		return "A"+getRandom();
	}
	public static String getBannerID() {
		return "B"+getRandom();
	}
	public static String getLogID() {
		return "LG"+getRandom();
	}
	
	private static String getRandom(){
		if(index==100)
			index=10;
		
		int num = (int)(Math.random()*900)+100;
		return num+""+new SimpleDateFormat("HHmmssSSS").format(new Date())+(index++);
	}
}
