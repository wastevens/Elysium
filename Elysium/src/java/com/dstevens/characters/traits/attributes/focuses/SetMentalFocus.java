package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MentalFocus")
class SetMentalFocus extends ApplicableTraitChange<MentalAttributeFocus> {

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetMentalFocus() {
        super();
    }
    
    public SetMentalFocus(int ordinal) {
    	super(ordinal);
    }
    
    @Override
    protected MentalAttributeFocus trait() {
    	return MentalAttributeFocus.values()[ordinal];
    }
}
