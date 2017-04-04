package com.cart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.source.annotations.entity.ConfiguredClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cart.model.BlogComment;
import com.cart.model.User;

@Repository
public class BlogCommentDaoImpl implements BlogCommentDao {
	
	Logger log = LoggerFactory.getLogger(BlogCommentDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<BlogComment> commentList() {
		Session session = sessionFactory.openSession();
		log.debug("Starting of the blog list method in DaoImpl");
		Query query = session.createQuery("from BlogComment");
		List<BlogComment> comments = query.list();
		session.close();
		return comments;
	}

	@Transactional
	public List<BlogComment> getCommentById(String blogId) {

		Session session = sessionFactory.openSession();
		log.debug("Starting of the list comment method in DaoImpl");
		String hql = "from BlogComment where blogId ='" + blogId + "'";
		Query query = session.createQuery(hql);
		List<BlogComment> comments = query.list();
		return comments;
	}

	@Transactional
	public BlogComment getById(String blogId) {
		log.debug("Starting of the getById method in DaoImpl");
		Session session = sessionFactory.openSession();
		BlogComment comment = (BlogComment) session.get(BlogComment.class, blogId);
		/*
		 * String hql = "from User where id = '" + id + "'";
		 * 
		 * Query query = sessionFactory.getCurrentSession().createQuery(hql);
		 * user = (User) query.uniqueResult();
		 */
		return comment;
	}

	@Transactional
	public void saveComment(BlogComment blogComment) {
		Session session = sessionFactory.openSession();
		log.debug("Starting of the blog comment method in DaoImpl");
		session.save(blogComment);
		session.flush();
		session.close();

	}

	@Transactional
	public BlogComment updateComment(String commentId, BlogComment blogComment) {

		Session session = sessionFactory.openSession();
		log.debug("Starting of the update blog method in DaoImpl");
		if (session.get(BlogComment.class, commentId) == null)
			return null;
		session.merge(blogComment);
		BlogComment updatedComment = (BlogComment) session.get(BlogComment.class, commentId);
		session.flush();
		session.close();
		return updatedComment;
	}

	@Transactional
	public void deleteComment(String CommentId) {
		Session session = sessionFactory.openSession();
		log.debug("Starting of the delete comment method in DaoImpl");
		BlogComment comment = (BlogComment) session.get(BlogComment.class, CommentId);
		session.delete(comment);
		session.flush();
		session.close();
	}

}
