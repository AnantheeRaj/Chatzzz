package com.cart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cart.model.Blog;

@Repository
public class BlogDaoImpl implements BlogDao {
	
	Logger log = LoggerFactory.getLogger(BlogDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Blog> getAllblogs() {
		log.debug("===>Ending Blogs method in DaoImpl===>");
		Session session = sessionFactory.openSession();
		List<Blog> blogs = session.createQuery("from Blog").list();
		
		return blogs;
	}

	@Transactional
	public boolean saveBlog(Blog blog) {
		Session session = sessionFactory.openSession();
		log.debug("Starting save Blog method in DaoImpl");
		try {
			session.save(blog);
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean updateStatus(Blog blog) {
		Session session = sessionFactory.openSession();
		log.debug("Starting update method in DaoImpl");
		try {
			session.update(blog);
			session.flush();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public void deleteBlog(String blogId) {
		Session session = sessionFactory.openSession();
		log.debug("Starting delete method in DaoImpl");
		Blog blog = (Blog) session.get(Blog.class, blogId);
		session.delete(blog);
		session.flush();
		session.close();
	};

	@Transactional
	public Blog getBlogById(String blogId) {
		Session session = sessionFactory.openSession();
		log.debug("Starting get blog id method in DaoImpl");
		Blog blog = (Blog) session.get(Blog.class, blogId);
		session.close();
		return blog;
	}

	public Blog updateBlog(String blogId, Blog blog) {
		Session session = sessionFactory.openSession();
		log.debug("Starting update blog method in DaoImpl");
		Blog currentBlog = (Blog) session.get(Blog.class, blogId);
		if (currentBlog == null) {
			return null;
		}
		session.merge(blog);
		Blog updatedBlog = (Blog) session.get(Blog.class, blogId);
		session.flush();
		session.close();
		return updatedBlog;
	}

	public void increaseLikes(String blogId) {
		Session session = sessionFactory.openSession();
		log.debug("---starting likes ");
		Blog blog = getBlogById(blogId);
		log.debug("Before Like: "+ blog.getLikes());
		blog.setLikes(blog.getLikes() + 1);
		log.debug("After Like: "+ blog.getLikes());
		session.update(blog);
		session.flush();
		session.close();
	}

	public void increaseDislikes(String blogId) {
		Session session = sessionFactory.openSession();
		log.debug("---starting likes ");
		Blog blog = getBlogById(blogId);
		log.debug("Before Dislike: "+ blog.getDislikes());
		blog.setDislikes(blog.getDislikes() + 1);
		log.debug("After Dislike: "+ blog.getDislikes());
		session.update(blog);
		session.flush();
		session.close();
	}

}
