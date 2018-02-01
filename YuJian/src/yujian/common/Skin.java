package yujian.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Skin {
	public final static String USER = "user";
	public final static String DESKEY = "yujian201796";

	public static Lock PayLock = new ReentrantLock();
	public static Lock AddUserLock = new ReentrantLock();
	public static Map<String, String> TipMap = new HashMap<>();
}
