package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PhysicalFocus")
class SetPhysicalFocus extends SetApplicableTrait<PhysicalAttributeFocus> {

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetPhysicalFocus() {
        super();
    }
    
    public SetPhysicalFocus(TraitChangeStatus status, int ordinal) {
    	super(status, ordinal);
    }
    
    @Override
    protected PhysicalAttributeFocus trait() {
    	return PhysicalAttributeFocus.values()[ordinal];
    }
}
