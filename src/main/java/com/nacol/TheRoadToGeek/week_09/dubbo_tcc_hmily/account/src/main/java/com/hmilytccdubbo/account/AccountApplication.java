package com.hmilytccdubbo.account;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:spring-dubbo.xml"})
public class AccountApplication implements ApplicationContextAware {

	private static  ApplicationContext applicationContext;
	public static void main(String[] args) {
//		SpringApplication.run(AccountApplication.class, args);
		try {
			SpringApplication springApplication = new SpringApplication(AccountApplication.class);
			springApplication.setWebApplicationType(WebApplicationType.NONE);
			springApplication.run(args);
			System.out.println("accountService : " + applicationContext.getBean("accountService"));
			System.out.println(SpringBootVersion.getVersion());
			while(true){

			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
