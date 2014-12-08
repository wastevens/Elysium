package com.dstevens.characters.traits.powers.magic.necromancy;

import com.dstevens.characters.traits.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanNecromancy")
class SetInClanNecromancy extends ApplicableTraitChange<Necromancy> {
	
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
