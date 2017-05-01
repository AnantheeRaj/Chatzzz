package com.cart.havfunbackend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cart.dao.UserDao;
import com.cart.model.User;

public class UserTest {

	@Autowired static AnnotationConfigApplicationContext context;
	
	@Autowired static UserDao userDAO;
	
	@Autowired static User user;

	
@BeforeClass
	
	public static void initialize(){
		context= new AnnotationConfigApplicationContext();
		context.scan("com.cart.config");
		context.refresh();
		//get userdao from context
				userDAO=(UserDao) context.getBean("userDAO");
				
				//get user from context
				user=(User) context.getBean("user");
				}
			
			//TestCAses

@Test
public void createUserTestCase(){
	user.setUserId("123");
	user.setfName("Bala");
	user.setlName("K");
	user.setAddress("Chennai");
	user.setEmailId("haibala@gmail.com");
	user.setPhoneNo("9442527345");
	user.setPassword("bala123456");
	user.setIsOnline('N');
	user.setStatus('N');
	user.setRole("ADMIN");
	user.setReason("Yes");
	boolean flag=userDAO.saveUser(user);
	assertEquals("createUserTestCase",true,flag);

}
}