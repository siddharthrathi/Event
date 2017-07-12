package com.event.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.java.Entity.Event;
import com.event.java.Entity.Person;
import com.event.java.Entity.PersonEvent;
import com.event.java.dao.PersonEventDao;

@Service
@Transactional
public class PersonEventServiceImpl implements PersonEventService{

	@Autowired
	private PersonEventDao personEventDao;
	
	public void setPersonEventDao(PersonEventDao personEventDao) {
		this.personEventDao = personEventDao;
	}

	@Override
	@Transactional
	public void addPersonEvent(PersonEvent personEvent) {
		this.personEventDao.addPersonEvent(personEvent);
	}

	@Override
	@Transactional
	public void updatePersonEvent(PersonEvent personEvent) {
		this.personEventDao.updatePersonEvent(personEvent);
	}

	@Override
	public boolean checkPersonEventAvailable(Person personId, Event eventId) {
		return this.personEventDao.checkPersonEventAvailable(personId, eventId);
	}

}
