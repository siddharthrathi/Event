package com.event.java.service;

import java.util.List;

import com.event.java.Entity.City;

public interface CityService {
	public List<City> listCity();
	public City getCityById(Long cityId);
}
