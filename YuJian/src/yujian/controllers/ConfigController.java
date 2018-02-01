package yujian.controllers;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import yujian.common.Skin;
import yujian.utilities.WebMapHelper;

@Controller
@RequestMapping("Config")
public class ConfigController {

	/**
	 * 加载提示信息
	 */
	@ResponseBody
	@RequestMapping(value = "LoadTipMap", method = RequestMethod.POST)
	public Map<String, String> loadTipMap() {
		// 加载提示信息
		String path = WebMapHelper.getWebRoot() + "WEB-INF/classes/systip.properties";
		Properties properties = new Properties();
		try {
			try (InputStream in = new BufferedInputStream(new FileInputStream(path));
					Reader reader = new InputStreamReader(in, Charset.forName("utf-8"))) {
				properties.load(reader);
				Iterator<String> it = properties.stringPropertyNames().iterator();
				if (Skin.TipMap == null)
					Skin.TipMap = new HashMap<>();
				
				Skin.TipMap.clear();
				while (it.hasNext()) {
					String name = it.next();
					if (!Skin.TipMap.containsKey(name)) {
						Skin.TipMap.put(name, properties.get(name).toString());
					}
				}

				return Skin.TipMap;
			}
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "GetTipMap", method = RequestMethod.GET)
	public Map<String, String> getTipMap() {
		if (Skin.TipMap == null || Skin.TipMap.isEmpty())
			loadTipMap();
		return Skin.TipMap;
	}

	@ResponseBody
	@RequestMapping(value = "UpdateTipMap", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String updateTipMap(@RequestParam Map<String, String> map) {
		// 加载提示信息
		try {
			String path = WebMapHelper.getWebRoot() + "WEB-INF/classes/systip.properties";
			Properties properties = new Properties();
			try (OutputStream outputStream = new FileOutputStream(path);
					Writer writer = new OutputStreamWriter(outputStream, "utf-8")) {
				Iterator<String> keys = map.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					properties.setProperty(key, map.get(key));
				}
				properties.store(writer, "");
			}

			loadTipMap();
			return "修改成功";
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
			return "修改失败";
		}
	}
}
