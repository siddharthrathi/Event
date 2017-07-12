package com.event.java.service;

import java.util.List;

import com.event.java.Entity.Person;

public interface PersonService {

	public void addPerson(Person person);
	public void updatePerson(Person person);
	public List<Person> listPersons();
	public Person getPersonById(Long id);
	public void removePerson(Long id);
	public boolean checkByEmail(String email);
	public Person getPersonByEmail(String email);
}
