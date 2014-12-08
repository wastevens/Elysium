package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

public enum SocialAttributeFocus implements ApplicableTrait, AttributeFocus {

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
	
	@Override
	public SetTrait set(TraitChangeStatus status) {
		return new SetSocialFocus(status, this.ordinal());
	}
}
