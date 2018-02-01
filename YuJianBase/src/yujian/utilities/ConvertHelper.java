package yujian.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConvertHelper {

	/**
	 * 尝试String转换UUID
	 * 
	 * @param uuid
	 * @return UUID 失败为空
	 */
	public static UUID convertToUUID(String uuid) {
		try {
			UUID id = UUID.fromString(uuid);
			return id;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 尝试转int
	 * 
	 * @param num
	 * @return
	 */
	public static int convertToInt(String num) {
		try {
			return Integer.valueOf(num);
		} catch (Exception e) {
			return 0;
		}
	}

	public static List<Integer> convertToInts(List<String> list) {
		try {
			List<Integer> intList = new ArrayList<>();
			for (String string : list) {
				intList.add(convertToInt(string));
			}
			return intList;
		} catch (Exception e) {
			return null;
		}
	}

	public static List<Integer> convertToInts(String[] strs) {
		try {
			List<Integer> intList = new ArrayList<>();
			for (String string : strs) {
				intList.add(convertToInt(string));
			}
			return intList;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 尝试转int
	 * 
	 * @param num
	 * @return
	 */
	public static long convertToLong(String num) {
		try {
			return Long.valueOf(num);
		} catch (Exception e) {
			return 0;
		}
	}

	public static List<Long> convertToLongs(List<String> list) {
		try {
			List<Long> intList = new ArrayList<>();
			for (String string : list) {
				intList.add(convertToLong(string));
			}
			return intList;
		} catch (Exception e) {
			return null;
		}
	}

	public static List<Long> convertToLongs(String[] strs) {
		try {
			List<Long> intList = new ArrayList<>();
			for (String string : strs) {
				intList.add(convertToLong(string));
			}
			return intList;
		} catch (Exception e) {
			return null;
		}
	}
}
