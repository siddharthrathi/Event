package com.event.java.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.java.Entity.City;
import com.event.java.dao.CityDao;

@Service
@Transactional
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityDao cityDao;
	
	@Override
	@Transactional
	public List<City> listCity() {
		return this.cityDao.listCity();
	}

	@Override
	@Transactional
	public City getCityById(Long cityId) {
		return this.cityDao.getCityById(cityId);
	}

}
