package com.dstevens.characters.traits.status;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Status")
public class SetStatus extends ApplicableTraitChange<CharacterStatus> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetStatus() {
    	super();
    }
    
    public SetStatus(TraitChangeStatus status, int ordinal, String specialization) {
    	super(status, ordinal, specialization);
    }
	
	@Override
	protected CharacterStatus trait() {
		return new CharacterStatus(Status.values()[ordinal], specialization);
	}

}
