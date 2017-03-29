package com.cart.havfunbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.cart.model.User;
import com.cart.service.UserService;

public class UserTestCase {
	@Autowired
		static AnnotationConfigApplicationContext context;

	@Autowired
		static User user;

	@Autowired
		static UserService userservice;

	@BeforeClass
	@Autowired
		public static void init()
		{
	
			context=new  AnnotationConfigApplicationContext();
			context.scan("com.cart");
			context.refresh();
			userservice=(UserService) context.getBean("userservice");
	 		//user=(User) context.getBean("user");
	 		System.out.println("objects are created");
		}
	
	@Test
		public void saveUserTestCase()
			{
		        User u=new User();
		        u.setUserId("123");
				u.setfName("testing");
				u.setlName("r");
				u.setAddress("chennai");
				u.setEmailId("testing@gmail.com");
				System.out.println("inside ");
				u.setPhoneNo("6789076543");
				u.setPassword("145678987");
				u.setIsOnline('N');
				u.setStatus('N');
				u.setRole("STUDENT");
				u.setReason("sample");
				assertTrue(userservice.saveUser(u));
				//Boolean status=userservice.saveUser(user);
				//Assert.assertEquals("save user test case",true,status);
			}
	

@Test
	public void updateUserTestCase()
	{
		user.setUserId("123");
		Boolean status=userservice.updateUser(user);
		Assert.assertEquals("update user test case",true,status);
	}
		

}
