package com.niit.collabbackend;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.dao.UserdetailsDAO;
import com.niit.model.Userdetails;
public class UserTestCase  {
		@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static userdetails user;

	@Autowired
	static UserdetailsDAO userdetailsDAO;

	@BeforeClass
	@Autowired
	public static void init()
	{
		
		context=new  AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userdetailsDAO=(UserdetailsDAO) context.getBean("userdetailsDAO");
		 		 user=(Userdetails) context.getBean("user");
		 		
			System.out.println("objects are created");
			}
		@Test
			public void saveUserTestCase()
	{
		
		user.setUserid("1");
		user.setUsername("Sample");
		user.setEmail("Sample@gmail.com");
		user.setContact("7894561234");
		user.setIs_online("");
		user.setAddress("chennai");
		user.setPassword("123456789");
		user.setRole("student");
		Boolean status=userdetailsDAO.save(user);
		Assert.assertEquals("save user test case",true,status);
		}
		@Test
		public void deleteUserTestCase()
		{
			user.setUserid("1");
			Boolean status=userdetailsDAO.delete(user);
			Assert.assertEquals("delete user test case",true,status);
		}
	
	
		
	}
