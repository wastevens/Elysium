package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

public enum PhysicalAttributeFocus implements ApplicableTrait, AttributeFocus {

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
	
	@Override
	public TraitChange set(TraitChangeStatus status) {
		return new SetPhysicalFocus(status, this.ordinal());
	}
}
