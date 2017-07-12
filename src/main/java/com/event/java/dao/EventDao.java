package com.event.java.dao;

import java.util.List;

import com.event.java.Entity.City;
import com.event.java.Entity.Event;

public interface EventDao {
	public void addEvent(Event event);
	public void updateEvent(Event event);
	public List<Event> listEvents();
	public Event getEventById(Long id);
	public void removeEvent(Long id);
	public List<Event> getEventsByCity(City city);
}
