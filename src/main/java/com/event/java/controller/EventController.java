package com.event.java.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.event.java.Entity.City;
import com.event.java.Entity.Event;
import com.event.java.Entity.Person;
import com.event.java.Entity.PersonEvent;
import com.event.java.service.CityService;
import com.event.java.service.EventService;
import com.event.java.service.PersonEventService;
import com.event.java.service.PersonService;

@Controller
@RequestMapping(value="/Event")
public class EventController {
	private final static String SUCCESS = "Success";
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonEventService personEventService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewEventJsp() {
		return "event";
	}
	
	@RequestMapping(value = "/saveEvent", method = RequestMethod.GET)
	public String saveEventJsp() {
		return "personevent";
	}
	
	@RequestMapping(value="/listCity", method = RequestMethod.GET)
	@ResponseBody
	public List<City> listCities(){
		List<City> cities = cityService.listCity();
		return cities;
	}
	
	@RequestMapping(value="/listEvents", method = RequestMethod.GET)
	@ResponseBody
	public List<Event> listEvents(){
		List<Event> events = eventService.listEvents();
		return events;
	}
	
	@RequestMapping(value="/viewAllPerson", method = RequestMethod.GET)
	@ResponseBody
	public List<Person> viewAllPerson(){
		List<Person> persons = personService.listPersons();
		return persons;
	}
	
	@RequestMapping(value="/addEvent", method=RequestMethod.POST)
	@ResponseBody
	public String addEvent(@RequestParam(value="eventid", required=false) Long eventId, @RequestParam(value="eventname") String eventName,
			@RequestParam(value="city", required=false) Long city, @RequestParam(value="startdate", required=false) String startdate,
			@RequestParam(value="enddate", required=false) String enddate, @RequestParam(value="capacity", required=false) Long capacity,
			HttpServletRequest request){
		java.sql.Date firstDate = null, lastDate = null;
		if(StringUtils.isNotBlank(startdate) && StringUtils.isNotBlank(enddate)){
			SimpleDateFormat startDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
			SimpleDateFormat endDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
			Date startedDate = null;
			Date endedDate = null;
			try {
				startedDate = startDateFormat.parse(startdate);
				endedDate = endDateFormat.parse(enddate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			firstDate = new java.sql.Date(startedDate.getTime());
			lastDate = new java.sql.Date(endedDate.getTime());
		}
		
		if(eventId == null){
			Event event = new Event();
			event.setEventName(eventName);
			event.setEventStart(firstDate);
			event.setEventEnd(lastDate);
			event.setCapacity(capacity);
			eventService.addEvent(event);
			City cityObject = cityService.getCityById(city);
			event.setCity(cityObject);
			eventService.updateEvent(event);
			return SUCCESS;
		}else{
			return null;
		}
	}
	

	@RequestMapping(value="/getEvent", method=RequestMethod.POST)
	@ResponseBody
	public List<Event> getEvents(@RequestParam("city") Long city, HttpServletRequest request){
		City cityObject = cityService.getCityById(city);
		List<Event> events = eventService.getEventsByCity(cityObject);
		return events;
	}
	
	@RequestMapping(value="/addPersonEvent", method=RequestMethod.POST)
	@ResponseBody
	public String addPersonEvent(@RequestParam(value="personeventid") Long personeventid, @RequestParam(value="personid") Long personid, @RequestParam(value="eventid") Long eventid, HttpServletRequest request){
		Person person = personService.getPersonById(personid);
		Event event = eventService.getEventById(eventid);
		boolean check = personEventService.checkPersonEventAvailable(person, event);
		System.out.println(check);
		if(check == false){
			PersonEvent personEvent = new PersonEvent();
			personEvent.setPerson(person);
			personEvent.setEvent(event);
			personEventService.addPersonEvent(personEvent);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(person.getEmail());
			mailMessage.setSubject("Event Registration");
			String text = "Hello, "+person.getPersonName()+" You are going to this Event, "+event.getEventName()+" city"+event.getCity().getCityName();
			mailMessage.setText(text);
			
			mailSender.send(mailMessage);
			return SUCCESS;
		}else{
			return "Used";
		}
	}
	
}
