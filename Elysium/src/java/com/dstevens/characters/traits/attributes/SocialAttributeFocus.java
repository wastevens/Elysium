package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;

public enum SocialAttributeFocus implements ApplicableTrait {

    CHARISMA,
    MANIPULATION,
    APPEARANCE;

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withSocialAttributeFocus(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutSocialAttributeFocus(this);
	}
}
