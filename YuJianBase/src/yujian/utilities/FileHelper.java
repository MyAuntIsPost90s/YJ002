package yujian.utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper {

	/**
	 * 保存文件方法
	 * 
	 * @param filePath
	 * @param content
	 * @throws IOException
	 */
	public static void saveFile(String filePath, byte[] content) throws IOException {
		File file = new File(filePath);
		// 判断文件路径是否存在
		if (!file.getParentFile().exists()) {
			// 文件路径不存在时，创建保存文件所需要的路径
			file.getParentFile().mkdirs();
		}

		// 创建文件（这是个空文件，用来写入上传过来的文件的内容）
		file.createNewFile();
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
			bos.write(content);
		}
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isEixt(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean delete(String filePath) {
		if (isEixt(filePath)) {
			File file = new File(filePath);
			return file.delete();
		}
		return false;
	}

	/**
	 * 获取文件名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		File file = new File(filePath);
		return file.getName();
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileExt(String filePath) {
		String ext = filePath.substring(filePath.lastIndexOf(".") + 1);
		return ext;
	}
}
