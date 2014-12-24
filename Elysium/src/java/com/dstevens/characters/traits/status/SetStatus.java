package com.dstevens.characters.traits.status;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Status")
class SetStatus extends ApplicableTraitChange<CharacterStatus> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetStatus() {
    	super();
    }
    
    public SetStatus(int ordinal, String specialization) {
    	super(ordinal, specialization);
    }
	
	@Override
	protected CharacterStatus trait() {
		return new CharacterStatus(Status.values()[ordinal], specialization);
	}

}
