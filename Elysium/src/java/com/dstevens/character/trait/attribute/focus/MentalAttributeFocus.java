package com.dstevens.character.trait.attribute.focus;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.TraitQualities;
import com.dstevens.utilities.IdentityUtilities;


public enum MentalAttributeFocus implements Trait, ApplicableTrait {

    INTELLIGENCE(0),
    WITS(1),
    PERCEPTION(2);

    private final int id;

	private MentalAttributeFocus(int id) {
		this.id = id;
	}
	
	public static MentalAttributeFocus from(int id) {
		return IdentityUtilities.withId(id, MentalAttributeFocus.values());
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
