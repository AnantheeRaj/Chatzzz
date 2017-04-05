package com.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.dao.BlogCommentDao;
import com.cart.model.BlogComment;

@Service
public class BlogCommentServiceImpl implements BlogCommentService {

	@Autowired
	private BlogCommentDao blogCommentDao;
	
	
	public List<BlogComment> commentList() {
		return blogCommentDao.commentList();
	}

	public List<BlogComment> getCommentById(String commentId) {
		return blogCommentDao.getCommentById(commentId);
	}

	public void saveComment(BlogComment blogComment) {
		blogCommentDao.saveComment(blogComment);
	}

	public BlogComment updateComment(String commentId, BlogComment blogComment) {
		return blogCommentDao.updateComment(commentId, blogComment);
	}

	public void deleteComment(String CommentId) {
		blogCommentDao.deleteComment(CommentId);
	}

	public BlogComment getById(String blogId) {
		return blogCommentDao.getById(blogId);
	}

}
