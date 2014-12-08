package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Physical")
class PhysicalAttributeValue extends AttributeValue {

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private PhysicalAttributeValue() {
        this(0);
    }
	
	public PhysicalAttributeValue(int value) {
		super(value);
	}
	
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withPhysicalAttribute(value());
	}
	
	public static AttributeValue set(int rating) {
		return new PhysicalAttributeValue(rating);
	}
	
	public static AttributeValue increase(PlayerCharacter character) {
		return new PhysicalAttributeValue(character.getPhysicalAttribute()+1);
	}
	
	public static AttributeValue decrease(PlayerCharacter character) {
		return new PhysicalAttributeValue(character.getPhysicalAttribute()-1);
	}
}
