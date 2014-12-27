package com.dstevens.characters.traits.experience;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.suppliers.IdSupplier;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("TypeOfChange")
public abstract class ExperienceChange {

	private static final ZoneId UTC = ZoneId.of("Z");
	
	@Id
	private final String id;
	
	@Column(name="value")
	private final int value;
	
	@Column(name="changedOn")
	private final Date changedOn;

	//Hibernate only
    @Deprecated
    protected ExperienceChange() {
    	this(0, null);
    }
	
	public ExperienceChange(int value, Date changedOn) {
		this.id = new IdSupplier().get();
		this.value = value;
		this.changedOn = changedOn;
	}

	protected final int getValue() {
		return value;
	}
	
	protected final LocalDate changedOn() {
		return changedOn.toInstant().atZone(UTC).toLocalDate();
	}
	
	public abstract PlayerCharacter applyTo(PlayerCharacter playerCharacter);
	
}
