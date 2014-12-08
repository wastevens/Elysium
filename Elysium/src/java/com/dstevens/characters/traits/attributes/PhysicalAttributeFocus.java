package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;

public enum PhysicalAttributeFocus implements ApplicableTrait {

    STRENGTH,
    DEXTERITY,
    STAMINA;

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withPhysicalAttributeFocus(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutPhysicalAttributeFocus(this);
	}
}
