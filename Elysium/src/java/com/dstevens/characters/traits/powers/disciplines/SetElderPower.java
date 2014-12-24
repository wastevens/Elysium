package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ElderPower")
class SetElderPower extends ApplicableTraitChange<ElderPower> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetElderPower() {
    	super();
    }
    
    public SetElderPower(TraitChangeStatus status, int ordinal) {
    	super(ordinal);
    }

	@Override
	protected ElderPower trait() {
		return ElderPower.values()[ordinal];
	}

}
