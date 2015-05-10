package com.dstevens.character.trait.attribute.focus;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.TraitQualities;

public enum SocialAttributeFocus implements Trait, ApplicableTrait {

    CHARISMA(0),
    MANIPULATION(1),
    APPEARANCE(2);

    private final int id;

	private SocialAttributeFocus(int id) {
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
		return character.withSocialAttributeFocus(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutSocialAttributeFocus(this);
	}
}
