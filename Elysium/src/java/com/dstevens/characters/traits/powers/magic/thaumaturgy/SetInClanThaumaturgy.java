package com.dstevens.characters.traits.powers.magic.thaumaturgy;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanThaumaturgy")
class SetInClanThaumaturgy extends ApplicableTraitChange<Thaumaturgy> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanThaumaturgy() {
    	super();
    }
    
    public SetInClanThaumaturgy(TraitChangeStatus status, int ordinal) {
    	super(status, ordinal);
    }

	@Override
	protected Thaumaturgy trait() {
		return Thaumaturgy.values()[ordinal];
	}

}
