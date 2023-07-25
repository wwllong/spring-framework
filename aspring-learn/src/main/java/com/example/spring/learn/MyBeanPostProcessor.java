package com.example.spring.learn;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author jack.wen
 * @since 2023/7/17 23:41
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

	public MyBeanPostProcessor() {
		System.out.println("BeanPostProcessor 实现类构造函数...");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if("springBean".equals(beanName)) {
			System.out.println("MyBeanPostProcessor postProcessBeforeInitialization 方法被调用中......");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if("springBean".equals(beanName)) {
			System.out.println("MyBeanPostProcessor postProcessAfterInitialization 方法被调用中......");
		}
		return bean;
	}

}
