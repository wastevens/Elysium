package com.dstevens.event;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.ForeignKey;

import com.dstevens.character.PlayerCharacter;

@SuppressWarnings("deprecation")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="eventType", discriminatorType=DiscriminatorType.STRING)
@Table(name="Event")
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
    
    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Event_PlayerCharacters", joinColumns = @JoinColumn(name="event_id"), 
	           inverseJoinColumns = @JoinColumn(name="playerCharacter_id"))
	@ForeignKey(name="Event_PlayerCharacters_FK", inverseName="PlayerCharacters_Event_FK")
    private final Set<PlayerCharacter> attendingCharacters;

    //Used only for hibernate
    @SuppressWarnings("unused")
	@Deprecated
    private Event() {
    	this(null, null, null, null, null);
    }
    
	public Event(Integer id, String name, Date eventDate, EventStatus eventStatus, Set<PlayerCharacter> attendingCharacters) {
		this.id = id;
		this.name = name;
		this.eventDate = eventDate;
		this.eventStatus = eventStatus;
		this.attendingCharacters = attendingCharacters;
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

	public Set<PlayerCharacter> getAttendingCharacters() {
		return attendingCharacters;
	}
}
