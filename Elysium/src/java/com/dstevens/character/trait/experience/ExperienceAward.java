package com.dstevens.character.trait.experience;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.ForeignKey;

import com.dstevens.event.Event;

import static com.dstevens.time.DateTimeUtilities.asLocalDateInUTC;
import static com.dstevens.time.DateTimeUtilities.fromLocalDateInUTC;

@SuppressWarnings("deprecation")
@Entity
public final class ExperienceAward {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "characterExperienceAward", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
	
	@Column(name="experience")
	private final int experience;
	
	@Column(name="awardedOn")
	private final Date awardedOn;

	@Column(name="awardedFor")
	private final String awardedFor;
	
	@OneToOne(optional=true)
	@JoinColumn(name="event_id", referencedColumnName="id")
	@ForeignKey(name="ExperienceAward_Event_FK", inverseName="Event_ExperienceAward_FK")
	private final Event attended;
	
	//Hibernate only
    @SuppressWarnings("unused")
	@Deprecated
    private ExperienceAward() {
    	this(0, null, null, null);
    }
    
    public ExperienceAward(int experience, LocalDate awardedOn, Event attended) {
    	this(experience, awardedOn, null, attended);
    }
    
    public ExperienceAward(int experience, LocalDate awardedOn, String awardedFor) {
    	this(experience, awardedOn, awardedFor, null);
    }
	
	public ExperienceAward(int experience, LocalDate awardedOn, String awardedFor, Event attended) {
		this.attended = attended;
		this.id = null;
		this.experience = experience;
		this.awardedOn = (awardedOn != null ? fromLocalDateInUTC(awardedOn) : null);
		this.awardedFor = awardedFor;
	}

	public final LocalDate awardedOn() {
		return asLocalDateInUTC(awardedOn);
	}

	public final int experience() {
		return experience;
	}

	public final String awardedFor() {
		return awardedFor;
	}
	
	public final Event attended() {
		return attended;
	}
	
	public final Date effectiveAwardDate() {
		if(attended != null) {
			return attended.getEventDate();
		}
		return awardedOn;
	}
	
}
