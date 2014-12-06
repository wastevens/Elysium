package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PhysicalFocus")
class SetPhysicalFocus extends SetApplicableTrait<PhysicalAttributeFocus> {

	@Column(name="applicable_trait_ordinal")
    private PhysicalAttributeFocus trait;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetPhysicalFocus() {
        this(null, null);
    }
    
    public SetPhysicalFocus(TraitChangeStatus status, PhysicalAttributeFocus trait) {
    	super(status);
    	this.trait = trait;
    }
    
    @Override
    protected PhysicalAttributeFocus trait() {
    	return trait;
    }
}
