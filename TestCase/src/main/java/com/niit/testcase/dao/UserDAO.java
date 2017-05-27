package com.niit.testcase.dao;

import java.util.List;

import com.niit.testcase.model.User;

public interface UserDAO {
	public boolean save(User user);                               //to create/register   -save
	public boolean update(User user);							  //to update user details
	public boolean validate(String id, String password);		  //to validate credentials
	public List<User> list();									  // to get all user details(no parameters to get all user details)
	public User get(String id);									  // for getting user details based on userid(constraints)
	
}
