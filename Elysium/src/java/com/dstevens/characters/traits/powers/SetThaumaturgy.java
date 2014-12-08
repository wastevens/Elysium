package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Thaumaturgy")
class SetThaumaturgy extends SetApplicableTrait<CharacterThaumaturgy> {

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
