package com.dstevens.character.trait.experience;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import static com.dstevens.time.DateTimeUtilities.asLocalDateInUTC;
import static com.dstevens.time.DateTimeUtilities.fromLocalDateInUTC;

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
	
	//Hibernate only
    @SuppressWarnings("unused")
	@Deprecated
    private ExperienceAward() {
    	this(0, null, null);
    }
	
	public ExperienceAward(int experience, LocalDate awardedOn, String awardedFor) {
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
	
}
