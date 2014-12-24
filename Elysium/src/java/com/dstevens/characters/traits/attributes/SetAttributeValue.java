package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;

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
	
	public SetAttributeValue(int ordinal, int rating) {
		super(ordinal, rating);
	}

	@Override
	protected AttributeValue trait() {
		return Attribute.values()[ordinal].setAttributeTo(rating);
	}

}
