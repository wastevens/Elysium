package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.changes.TraitChange;


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
	public TraitChange set() {
		return new SetMentalFocus(this.ordinal());
	}
    
}
