package com.dstevens.character.trait.attribute;

import com.dstevens.character.PlayerCharacter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Mental")
class MentalAttributeValue extends AttributeValue {

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private MentalAttributeValue() {
        this(0);
    }
	
	public MentalAttributeValue(int value) {
		super(value);
	}
	
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withMentalAttribute(value());
	}
	
	public static AttributeValue set(int rating) {
		return new MentalAttributeValue(rating);
	}
	
	public static AttributeValue increase(PlayerCharacter character) {
		return new MentalAttributeValue(character.getMentalAttribute()+1);
	}
	
	public static AttributeValue decrease(PlayerCharacter character) {
		return new MentalAttributeValue(character.getMentalAttribute()-1);
	}
}
