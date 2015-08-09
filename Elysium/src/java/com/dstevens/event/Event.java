package com.dstevens.event;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="Event")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="eventType", discriminatorType=DiscriminatorType.STRING)
public abstract class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "event", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
	
    @Column(name="name")
    private final String name;
    
    @Column(name="eventDate")
    private final Date eventDate;
    
    @Column(name="status")
    private EventStatus eventStatus;

    //Used only for hibernate
    @SuppressWarnings("unused")
	@Deprecated
    private Event() {
    	this(null, null, null, null);
    }
    
	public Event(Integer id, String name, Date eventDate, EventStatus eventStatus) {
		this.id = id;
		this.name = name;
		this.eventDate = eventDate;
		this.eventStatus = eventStatus;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public EventStatus getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(EventStatus eventStatus) {
		this.eventStatus = eventStatus;
	}
}
