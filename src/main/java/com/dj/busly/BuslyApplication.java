package com.dj.busly;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class BuslyApplication extends SpringBootServletInitializer implements ApplicationContextAware  {

	private static ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		SpringApplication.run(BuslyApplication.class, args);
		
		System.out.println("Every");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		BuslyApplication.applicationContext = applicationContext;
		
	}

	/**
	 * Get bean from application
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return BuslyApplication.applicationContext.getBean(clazz);
	}
}

