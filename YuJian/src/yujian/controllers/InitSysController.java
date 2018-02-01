package yujian.controllers;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitSysController implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		new ConfigController().loadTipMap(); // 加载提示信息
	}

}
