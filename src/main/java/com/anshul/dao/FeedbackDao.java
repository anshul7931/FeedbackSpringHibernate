package com.anshul.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.anshul.entity.Feedback;

//Hibernate will be configured in dispatcher servelet.
//1.Provide xsd and schema location
//2.Configure the database
//3.Configure sessionFactory
//4.Add transaction manager

@Transactional
@Component
public class FeedbackDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addFeedback(Feedback feedback) {
		Session session = sessionFactory.getCurrentSession();
		session.save(feedback);
	}

	public List<Feedback> getFeedbacks() {
		Session session = sessionFactory.getCurrentSession();
		Query<Feedback> q=session.createQuery("from Feedback",Feedback.class);//HQL
		List<Feedback> feedbacks = (List<Feedback>)q.list();
		return feedbacks;
	}

	public List<Feedback> getRandomFeedbacks() {
		Session session = sessionFactory.getCurrentSession();
		Query<Feedback> q=session.createNativeQuery("select * from Feedback order by rand() limit 3",Feedback.class);//SQL
		List<Feedback> feedbacks = (List<Feedback>)q.list();
		return feedbacks;
	}

}
