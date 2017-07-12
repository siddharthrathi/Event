package com.event.java.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.event.java.Entity.City;

@Repository
public class CityDaoImpl implements CityDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<City> listCity() {
		Session session = sessionFactory.getCurrentSession();
		List<City> cityList = session.createQuery("from City").list();
		return cityList;
	}

	@Override
	public City getCityById(Long cityId) {
		Session session = this.sessionFactory.getCurrentSession();
		City role = (City) session.load(City.class, new Long(cityId));
		return role;
	}

}
