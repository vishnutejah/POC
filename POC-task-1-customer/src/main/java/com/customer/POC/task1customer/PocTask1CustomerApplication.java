package com.customer.POC.task1customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class PocTask1CustomerApplication {
	@Bean
	   public MessageSource messageSource() { //defining messagesource bean for display of messages from properties file
	      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	      messageSource.setBasename("classpath:messages");
	      messageSource.setDefaultEncoding("UTF-8");
	      return messageSource;
	   }
	@Bean
	 public LocalValidatorFactoryBean validator(MessageSource messageSource) {// registering our messageSource bean
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource);
	    return bean;
	 }

	public static void main(String[] args) {
		SpringApplication.run(PocTask1CustomerApplication.class, args);
	}

}
