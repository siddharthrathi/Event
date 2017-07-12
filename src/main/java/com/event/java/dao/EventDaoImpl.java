package com.event.java.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.event.java.Entity.City;
import com.event.java.Entity.Event;

@Repository
public class EventDaoImpl implements EventDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addEvent(Event event) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(event);
	}

	@Override
	public void updateEvent(Event event) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(event);
	}

	@Override
	public List<Event> listEvents() {
		Session session = sessionFactory.getCurrentSession();
		List<Event> eventList = session.createQuery("from Event").list();
		return eventList;
	}

	@Override
	public Event getEventById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Event event = (Event) session.get(Event.class, new Long(id));
		return event;
	}

	@Override
	public void removeEvent(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Event event = (Event) session.load(Event.class, new Long(id));
		if(null != event){
			session.delete(event);
		}
	}

	@Override
	public List<Event> getEventsByCity(City city) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Event where city=:city");
		query.setParameter("city", city);
		List<Event> events = query.list();
		return events;
	}

}
