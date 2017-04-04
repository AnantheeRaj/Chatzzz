package com.cart.havfunbackend;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cart.model.Blog;
import com.cart.model.User;
import com.cart.service.UserService;

public class BlogTestCase {

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
 		System.out.println("objects created");
	}

@Test
	public void saveBlogTestCase()
		{
	        Blog b=new Blog();
	        b.setBlogId("b01");
	        b.setTitle("sample");
	        b.setDescription("sample demo");
	        b.setUserId("1");
	        b.setBlogCreatedDate("21/03/2017");
	        b.setStatus("Y");
	        b.setReason("hi");
	        b.setLikes(2);
	        b.setDislikes(1);
	       // assertTrue(userservice.saveUser(b));
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
