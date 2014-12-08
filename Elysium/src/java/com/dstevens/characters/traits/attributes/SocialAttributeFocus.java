package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.EnumeratedTrait;

public enum SocialAttributeFocus implements EnumeratedTrait<SocialAttributeFocus>, ApplicableTrait {

    CHARISMA,
    MANIPULATION,
    APPEARANCE;

	@Override
	public SocialAttributeFocus trait() {
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
