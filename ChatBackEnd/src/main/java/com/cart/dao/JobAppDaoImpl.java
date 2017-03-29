package com.cart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cart.model.Friend;
import com.cart.model.JobApplication;

@Repository
public class JobAppDaoImpl implements JobAppDao {
	
	public static final Logger log = LoggerFactory.getLogger(JobAppDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public boolean applyJob(JobApplication jobapp) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(jobapp);
			tx.commit();
			log.debug("Applied by: " + jobapp.getAppliedBy());
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<JobApplication> getAppliedJobs(String userId) {
		String hql = "from Friend where userId='" + userId + "' and status ='N'";
		Query query = sessionFactory.openSession().createQuery(hql);
		List<JobApplication> list = (List<JobApplication>) query.list();
		return list;
	}
}
