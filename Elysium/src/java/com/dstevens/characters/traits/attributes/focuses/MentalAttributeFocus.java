package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;


public enum MentalAttributeFocus implements ApplicableTrait, AttributeFocus {

    INTELLIGENCE,
    WITS,
    PERCEPTION;

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withMentalAttributeFocus(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutMentalAttributeFocus(this);
	}
	
	@Override
	public SetTrait set(TraitChangeStatus status) {
		return new SetMentalFocus(status, this.ordinal());
	}
    
}
