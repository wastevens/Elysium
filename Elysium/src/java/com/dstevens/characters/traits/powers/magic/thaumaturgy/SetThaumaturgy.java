package com.dstevens.characters.traits.powers.magic.thaumaturgy;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Thaumaturgy")
class SetThaumaturgy extends ApplicableTraitChange<CharacterThaumaturgy> {

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetThaumaturgy() {
        this(null, 0, 0);
    }
	
    public SetThaumaturgy(TraitChangeStatus status, int ordinal, int rating) {
    	super(status, ordinal, rating);
    }
    
    @Override
    protected CharacterThaumaturgy trait() {
    	return new CharacterThaumaturgy(Thaumaturgy.values()[ordinal], rating);
    }
}
