package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PhysicalFocus")
class SetPhysicalFocus extends ApplicableTraitChange<PhysicalAttributeFocus> {

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetPhysicalFocus() {
        super();
    }
    
    public SetPhysicalFocus(int ordinal) {
    	super(ordinal);
    }
    
    @Override
    protected PhysicalAttributeFocus trait() {
    	return PhysicalAttributeFocus.values()[ordinal];
    }
}
