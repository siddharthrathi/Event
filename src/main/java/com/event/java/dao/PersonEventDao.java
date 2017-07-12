package com.event.java.dao;

import com.event.java.Entity.Event;
import com.event.java.Entity.Person;
import com.event.java.Entity.PersonEvent;

public interface PersonEventDao {
	public void addPersonEvent(PersonEvent personEvent);
	public void updatePersonEvent(PersonEvent personEvent);
	public boolean checkPersonEventAvailable(Person personId, Event eventId);
}
