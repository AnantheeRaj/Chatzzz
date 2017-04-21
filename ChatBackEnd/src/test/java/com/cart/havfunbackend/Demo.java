package com.cart.havfunbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.cart.dao.UserDao;
import com.cart.dao.UserDaoImpl;
import com.cart.model.User;

public class Demo {

	static AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    
    static{
              ctx.scan("com.niit.cart");
              ctx.refresh();
    }

    @Autowired
    static UserDao ud;

public static void main(String[] args) {
	ud = new UserDaoImpl();
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
	if(ud.saveUser(user))
		System.out.println("saved");
	else
		System.out.println("not saved");
}

}
