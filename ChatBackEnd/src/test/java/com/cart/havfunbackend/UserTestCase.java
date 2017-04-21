package com.cart.havfunbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cart.dao.UserDao;
import com.cart.model.User;
import com.cart.service.UserService;

public class UserTestCase {
	@Autowired
		static AnnotationConfigWebApplicationContext context;

	@Autowired
		static User user;

	@Autowired
		static UserDao userdao;

	@BeforeClass
	@Autowired
		public static void init()
		{
	
			context=new  AnnotationConfigWebApplicationContext();
			context.scan("com.cart");
			context.refresh();
			userdao=(UserDao) context.getBean("userdao");
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
				assertTrue(userdao.saveUser(u));
				//Boolean status=userservice.saveUser(user);
				//Assert.assertEquals("save user test case",true,status);
			}
	

@Test
	public void updateUserTestCase()
	{
		user.setUserId("123");
		Boolean status=userdao.updateUser(user);
		Assert.assertEquals("update user test case",true,status);
	}
		

}
