package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Social")
class SocialAttributeValue extends AttributeValue {

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SocialAttributeValue() {
        this(0);
    }
	
	public SocialAttributeValue(int value) {
		super(value);
	}
	
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withSocialAttribute(value());
	}
	
	public static AttributeValue set(int rating) {
		return new SocialAttributeValue(rating);
	}
	
	public static AttributeValue increase(PlayerCharacter character) {
		return new SocialAttributeValue(character.getSocialAttribute()+1);
	}
	
	public static AttributeValue decrease(PlayerCharacter character) {
		return new SocialAttributeValue(character.getSocialAttribute()-1);
	}
}
