package com.dstevens.characters.traits.experience;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.suppliers.IdSupplier;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("TypeOfChange")
public abstract class ExperienceChange {

	@Id
	private final String id;
	
	@Column(name="value")
	private final int value;
	
	//Hibernate only
    @Deprecated
    protected ExperienceChange() {
    	this(0);
    }
	
	public ExperienceChange(int value) {
		this.id = new IdSupplier().get();
		this.value = value;
	}

	protected final int getValue() {
		return value;
	}
	
	public abstract PlayerCharacter applyTo(PlayerCharacter playerCharacter);
	
}
