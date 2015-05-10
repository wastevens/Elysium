package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;


public enum MentalAttributeFocus implements Trait, ApplicableTrait {

    INTELLIGENCE(0),
    WITS(1),
    PERCEPTION(2);

    private final int id;

	private MentalAttributeFocus(int id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return this;
	}
    
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withMentalAttributeFocus(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutMentalAttributeFocus(this);
	}
}
