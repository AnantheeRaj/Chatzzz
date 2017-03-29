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

@Repository
public class FriendDaoImpl implements FriendDao {
	
	public static final Logger log = LoggerFactory.getLogger(FriendDaoImpl.class);

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public FriendDaoImpl() {

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// creating Auto generation in Own Way
	private Integer getMaxId() {
		log.debug("-----starting method getMaxId");
		String hql = "select max(id) from Friend";
		Query query = sessionFactory.openSession().createQuery(hql);
		Integer maxId;
		try {
			maxId = (Integer) query.uniqueResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100;
		}
		log.debug("-----MaxId : " + maxId);
		return maxId;
	}

	@Transactional
	public List<Friend> getMyFriend(String userId) {
		String hql = "from Friend where userID='" + userId + "' and status ='A'";
		Query query = sessionFactory.openSession().createQuery(hql);
		List<Friend> list = (List<Friend>) query.list();
		return list;
	}

	@Transactional
	public Friend getBId(String userId, String friendId) {
		String hql = "from Friend where userID='" + userId + "' and friendId= '" + friendId + "'";
		log.debug("hql :" + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		return (Friend) query.uniqueResult();

	}

	@Transactional
	public boolean saveFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean updateFriend(Friend friend) {
		log.debug("FriendIId : " + friend.getFriendId() + " USer ID: " + friend.getUserID() + "Status: "
				+ friend.getStatus() + "Id: " + friend.getId());
		log.debug("starting update method in daoimpl ");
		log.debug("ISONLINE VALUE IS [BEFORE UPDATE]" + friend.getStatus());
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(friend);
			tx.commit();
			log.debug("ISONLINE VALUE IS [AFTER UPDATE] " + friend.getStatus());
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public void deleteFriend(String userId, String friendId) {
		Friend friend = new Friend();
		friend.setFriendId(friendId);
		friend.setUserID(userId);
		sessionFactory.openSession().delete(friend);
	}

	@Transactional
	public List<Friend> getNewFriendRequest(String userId) {
		String hql = "from Friend where friendId='" + userId + "' and status ='N'";
		Query query = sessionFactory.openSession().createQuery(hql);
		List<Friend> list = (List<Friend>) query.list();
		return list;
	}

	@Transactional
	public void setOnline(String userId) {
		log.debug("-----staring setnline method in frienddao");
		String hql = "UPDATE Friend SET isOnline = 'Y' where userId='" + userId + "'";
		log.debug("-----hql : " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		query.executeUpdate();
		log.debug("-----Ending the method setOnline");

	}

	@Transactional
	public void setOffLine(String userId) {
		log.debug("-----starting the mehod setOffline");
		String hql = "UPDATE Friend SET isOnline = 'N' where userId='" + userId + "'";
		log.debug("-----hql : " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		query.executeUpdate();
		log.debug("-----Ending the method setOnline");

	}

}
