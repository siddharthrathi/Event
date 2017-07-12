package com.event.java.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.event.java.Entity.Person;


@Repository
public class PersonDAOImpl implements PersonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addPerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(p);
		logger.info("Person saved successfully, Person Details="+p);
	}

	@Override
	public void updatePerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Person updated successfully, Person Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		Session session = sessionFactory.getCurrentSession();
		List<Person> personsList = session.createQuery("from Person").list();
		return personsList;
	}

	@Override
	public Person getPersonById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Person p = (Person) session.get(Person.class, new Long(id));
		logger.info("Person loaded successfully, Person details="+p);
		return p;
	}

	@Override
	public void removePerson(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, new Long(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Person deleted successfully, person details="+p);
	}

	@Override
	public boolean checkByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Person where email=:email");
		query.setParameter("email", email);
		List<Person> person = query.list();
		if(person.size()>0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public Person getPersonByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Person where email=:email");
		query.setParameter("email", email);
		List<Person> person = query.list();
		return person.get(0);
	}

}
