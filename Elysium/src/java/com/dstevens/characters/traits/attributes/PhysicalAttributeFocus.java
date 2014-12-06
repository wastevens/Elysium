package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.EnumeratedTrait;

public enum PhysicalAttributeFocus implements EnumeratedTrait<PhysicalAttributeFocus>, ApplicableTrait {

    STRENGTH,
    DEXTERITY,
    STAMINA;

	@Override
	public PhysicalAttributeFocus trait() {
		return this;
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withPhysicalAttributeFocus(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutPhysicalAttributeFocus(this);
	}
    
}
