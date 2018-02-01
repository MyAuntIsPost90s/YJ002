package yujian.utilities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

public class ImageHelper {
	// 裁剪图片
	public static void cutImage(String path, String outpath, int x, int y, int width, int height) throws Exception {
		String type = path.substring(path.lastIndexOf(".") + 1);

		// 文件地址
		File file = new File(path);
		Iterator<ImageReader> readers = ImageIO.getImageReaders(new FileImageInputStream(file));
		ImageReader reader = readers.next();

		try (InputStream input = new FileInputStream(file);
				ImageInputStream imageStream = ImageIO.createImageInputStream(input)) {
			reader.setInput(imageStream, true);
			// 参数
			ImageReadParam param = reader.getDefaultReadParam();
			// 图片裁剪范围
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);
			// 裁剪出图片
			BufferedImage buff = reader.read(0, param);
			// 输出达到文件夹
			ImageIO.write(buff, type, new File(outpath));
		}
	}

	/**
	 * 剪切正方形
	 * 
	 * @param path
	 * @param outpath
	 * @throws Exception
	 */
	public static void cutSquare(String path, String outpath) throws Exception {
		File file = new File(path);
		try (InputStream is = new FileInputStream(file)) {
			BufferedImage buff = ImageIO.read(is);
			int height = buff.getHeight();
			int width = buff.getWidth();

			int cutlen = height > width ? width : height;
			int y = height > width ? (height - width) / 2 : 0;
			int x = height > width ? 0 : (width - height) / 2;
			cutImage(path, outpath, x, y, cutlen, cutlen);
		}
	}
}
