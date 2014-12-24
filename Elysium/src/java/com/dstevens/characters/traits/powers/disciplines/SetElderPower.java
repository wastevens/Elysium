package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ElderPower")
class SetElderPower extends TraitChange<ElderPower> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetElderPower() {
    	super();
    }
    
    public SetElderPower(int ordinal) {
    	super(ordinal);
    }

	@Override
	protected ElderPower trait() {
		return ElderPower.values()[ordinal];
	}

}
