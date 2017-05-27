package com.niit.testcase.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.niit.testcase.dao.UserDAO;
import com.niit.testcase.model.User;
@Component
@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	//user defined constructor with one argument

	public UserDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	
	//save mtd creates record in User table
	//
	
	public boolean save(User user) {
	try{
		sessionFactory.getCurrentSession().save(user);		//getCurrentSession will establish connection to existing DB 
	}
	catch(Exception e){
		e.printStackTrace();								//printStackTrace will print all the exceptions.
		return false;
	}
	return true;
	}


	public boolean update(User user) {
		try{
			sessionFactory.getCurrentSession().update(user);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		}

	
		//validate mtd will return true if all credentials are true else false.
	
	public boolean validate(String id, String password) {
	log.debug("Starting of validate method");
		Query query=sessionFactory.getCurrentSession().createQuery("from User where name=? and password=?");
									//id=? &pwd=? -->?is used to get from UI
		query.setString(0,id);
		query.setString(1, password);
		if(query.uniqueResult()==null){							//unique result will return object if row exists else returns null.
			return false;
		}
		else{
			return true;
		}
	}

	
	@SuppressWarnings("unchecked")
	
	public List<User> list() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}
	
	
	public User get(String id){															//get method will return object
		return (User) sessionFactory.getCurrentSession().get(User.class,id);			//return(User)-->typecast the User into object
		
	}
}
