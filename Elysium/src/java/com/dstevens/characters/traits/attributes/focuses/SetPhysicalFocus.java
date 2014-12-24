package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PhysicalFocus")
class SetPhysicalFocus extends TraitChange<PhysicalAttributeFocus> {

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
