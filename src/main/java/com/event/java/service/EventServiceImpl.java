package com.event.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.java.Entity.City;
import com.event.java.Entity.Event;
import com.event.java.dao.EventDao;

@Service
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private EventDao eventDao;
	
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	@Transactional
	public void addEvent(Event event) {
		this.eventDao.addEvent(event);
	}

	@Override
	@Transactional
	public void updateEvent(Event event) {
		this.eventDao.updateEvent(event);
	}

	@Override
	@Transactional
	public List<Event> listEvents() {
		return this.eventDao.listEvents();
	}

	@Override
	@Transactional
	public Event getEventById(Long id) {
		return this.eventDao.getEventById(id);
	}

	@Override
	@Transactional
	public void removeEvent(Long id) {
		this.eventDao.removeEvent(id);
	}

	@Override
	@Transactional
	public List<Event> getEventsByCity(City city) {
		return this.eventDao.getEventsByCity(city);
	}

}
