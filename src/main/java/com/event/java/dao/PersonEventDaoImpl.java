package com.event.java.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.event.java.Entity.Event;
import com.event.java.Entity.Person;
import com.event.java.Entity.PersonEvent;

@Repository
public class PersonEventDaoImpl implements PersonEventDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addPersonEvent(PersonEvent personEvent) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(personEvent);
	}

	@Override
	public void updatePersonEvent(PersonEvent personEvent) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(personEvent);
	}

	@Override
	public boolean checkPersonEventAvailable(Person personId, Event eventId) {
		System.out.println("coming : "+personId +" : "+eventId);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PersonEvent where person=:personid AND event=:eventid");
		query.setParameter("personid", personId);
		query.setParameter("eventid", eventId);
		List<PersonEvent> events = query.list();
		if(events.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
}
