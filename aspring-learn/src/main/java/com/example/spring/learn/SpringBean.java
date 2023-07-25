package com.example.spring.learn;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author jack.wen
 * @since 2023/7/6 23:11
 */
public class SpringBean implements InitializingBean, ApplicationContextAware {

	public SpringBean() {
		System.out.println("SpringBean 构造器...");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("SpringBean afterPropertiesSet...");
	}

	public void print() {
		System.out.println("print方法业务逻辑执行");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("setApplicationContext...." + applicationContext);
	}

}
