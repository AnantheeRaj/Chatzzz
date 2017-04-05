package com.cart.havfunbackend;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.cart.dao.UserDao;
import com.cart.dao.UserDaoImpl;
import com.cart.model.User;


public class DemoTest {
	
	public static void main(String[] args) {
		UserDao userdao;
		AnnotationConfigWebApplicationContext ct=new AnnotationConfigWebApplicationContext();
		 ct.scan("com.cart.config");
		 ct.refresh();
		 userdao=(UserDao)ct.getBean("UserDaoImpl");
		 User user=new User();
		 user.setUserId("123");
		 user.setStatus('N');
		 user.setRole("STUDENT");
		 user.setReason("");
		 user.setPhoneNo("8798765434");
		 user.setlName("mar");
		 user.setIsOnline('N');
		 user.setfName("DemoUser");
		 user.setErrorMessage("null values");
		 user.setErrorCode("404");
		 user.setEmailId("demo@gmail.com");
		 user.setAddress("chennai");
		 userdao.saveUser(user);
			 
			 
		 }
	}
	
