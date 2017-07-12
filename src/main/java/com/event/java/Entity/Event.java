package com.event.java.Entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EVENTID")
	private Long eventId;
	
	@Column(name="EVENTNAME", nullable=false, unique=true)
	private String eventName;
	
	@Column(name="EVENTSTART", nullable=false)
	private Date eventStart;
	
	@Column(name="EVENTEND", nullable=false)
	private Date eventEnd;

	@ManyToOne
	@JoinColumn(name="CITYID")
	private City city;
	
	@Column(name="CAPACITY", nullable=false)
	private Long capacity;
	
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventStart() {
		return eventStart;
	}

	public void setEventStart(Date eventStart) {
		this.eventStart = eventStart;
	}

	public Date getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", eventStart=" + eventStart + ", eventEnd="
				+ eventEnd + ", city=" + city + ", capacity=" + capacity + "]";
	}
}
