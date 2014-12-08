package com.dstevens.characters.traits.powers.magic.necromancy;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanNecromancy")
class SetInClanNecromancy extends SetApplicableTrait<Necromancy> {
	
	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanNecromancy() {
        this(null, 0);
    }
    
    public SetInClanNecromancy(TraitChangeStatus status, int ordinal) {
    	super(status, ordinal);
    }

	@Override
	protected Necromancy trait() {
		return Necromancy.values()[ordinal];
	}

}
