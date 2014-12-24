package com.dstevens.characters.traits.powers.magic.thaumaturgy;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Thaumaturgy")
class SetThaumaturgy extends ApplicableTraitChange<CharacterThaumaturgy> {

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetThaumaturgy() {
    	super();
    }
	
    public SetThaumaturgy(int ordinal, int rating) {
    	super(ordinal, rating);
    }
    
    @Override
    protected CharacterThaumaturgy trait() {
    	return new CharacterThaumaturgy(Thaumaturgy.values()[ordinal], rating);
    }
}
