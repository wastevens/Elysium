package com.dstevens.characters.traits.experience;

import static com.dstevens.time.DateTimeUtilities.asLocalDateInUTC;
import static com.dstevens.time.DateTimeUtilities.fromLocalDateInUTC;

import java.time.LocalDate;
import java.util.Date;

import com.dstevens.suppliers.IdSupplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public final class ExperienceAward {

	@Id
	private final String id;
	
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
		this.id = new IdSupplier().get();
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
