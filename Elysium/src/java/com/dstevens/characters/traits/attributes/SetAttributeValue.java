package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Attribute")
class SetAttributeValue extends ApplicableTraitChange<AttributeValue> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetAttributeValue() {
        super();
    }
	
	public SetAttributeValue(TraitChangeStatus status, int ordinal, int rating) {
		super(status, ordinal, rating);
	}

	@Override
	protected AttributeValue trait() {
		return Attribute.values()[ordinal].setAttributeTo(rating);
	}

}
