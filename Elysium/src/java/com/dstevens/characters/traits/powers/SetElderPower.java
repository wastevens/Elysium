package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ElderPower")
class SetElderPower extends SetApplicableTrait<ElderPower> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetElderPower() {
        this(null, 0);
    }
    
    public SetElderPower(TraitChangeStatus status, int ordinal) {
    	super(status, ordinal);
    }

	@Override
	protected ElderPower trait() {
		return ElderPower.values()[ordinal];
	}

}
