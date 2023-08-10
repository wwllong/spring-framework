package com.example.spring.learn;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author jack.wen
 * @since 2023/8/7 13:42
 */
public class SpringBeanCyclic implements InitializingBean, ApplicationContextAware {

	private SpringBean springBean;

	public void setSpringBean(SpringBean springBean) {
		this.springBean = springBean;
	}

	public SpringBeanCyclic() {
		System.out.println("SpringBeanOther 构造器...");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("SpringBeanOther afterPropertiesSet...");
	}

	public void print() {
		System.out.println("SpringBeanOther print方法业务逻辑执行");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("SpringBeanOther setApplicationContext...." + applicationContext);
	}


}
