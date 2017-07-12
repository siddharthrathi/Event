package com.event.java.dao;

import java.util.List;

import com.event.java.Entity.City;

public interface CityDao {
	public List<City> listCity();
	public City getCityById(Long cityId);
}
