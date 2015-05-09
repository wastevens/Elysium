package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.TraitQualities;

public enum SocialAttributeFocus implements AttributeFocus, ApplicableTrait {

    CHARISMA(0),
    MANIPULATION(1),
    APPEARANCE(2);

    private final int id;

	private SocialAttributeFocus(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
    
	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return this;
	}
    
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withSocialAttributeFocus(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutSocialAttributeFocus(this);
	}
}
