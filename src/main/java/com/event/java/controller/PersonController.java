package com.event.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.event.java.Entity.City;
import com.event.java.Entity.Person;
import com.event.java.service.CityService;
import com.event.java.service.PersonService;


@Controller
@RequestMapping(value="/Person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private CityService cityService;
	
	private final static String SUCCESS = "Success";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewPersonJsp() {
		return "addPerson";
	}
	
	@RequestMapping(value="/listCity", method = RequestMethod.GET)
	@ResponseBody
	public List<City> listCities(){
		List<City> cities = cityService.listCity();
		return cities;
	}
	
	@RequestMapping(value="/checkAvailable", method=RequestMethod.POST)
	@ResponseBody
	public boolean checkAvailable(@RequestParam(value="email", required=false) String email, HttpServletRequest request){
		boolean available;
		available = personService.checkByEmail(email);
		if(available == true){
			return available;
		}else{
			return available;
		}
	}
	
	@RequestMapping(value="/getPersonByEmail", method=RequestMethod.POST)
	@ResponseBody
	public Long getPersonByEmail(@RequestParam(value="email", required=false) String email, HttpServletRequest request){
		if(StringUtils.isNotBlank(email)){
			Person person = personService.getPersonByEmail(email);
			Long personid = person.getPersonId();
			return personid;
		}else{
			return null;
		}
	}
	
	@RequestMapping(value="/addPerson", method=RequestMethod.POST)
	@ResponseBody
	public Person addPerson(@RequestParam(value="personid", required=false) Long personid, @RequestParam(value="personname", required=false) String personname,
			@RequestParam(value="city", required=false) String city, @RequestParam(value="age", required=false) Long age,
			@RequestParam(value="email", required=false) String email, @RequestParam(value="phone") Long phoneNo,
			HttpServletRequest request){
		if(personid == null){
			Person person = new Person();
			person.setPersonName(personname);
			person.setAge(age);
			person.setEmail(email);
			person.setPhoneNo(phoneNo);
			person.setCity(city.toUpperCase());
			personService.addPerson(person);
			return person;
		}else{
			return null;
		}
	}
}
