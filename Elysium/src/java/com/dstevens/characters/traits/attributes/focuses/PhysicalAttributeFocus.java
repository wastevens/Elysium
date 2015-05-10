package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;

public enum PhysicalAttributeFocus implements Trait, ApplicableTrait {

    STRENGTH(0),
    DEXTERITY(1),
    STAMINA(2);

    private final int id;

	private PhysicalAttributeFocus(int id) {
		this.id = id;
	}
	
	public int id() {
		return id;
	}
    
    @Override
    public ApplicableTrait applyWith(TraitQualities qualities) {
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
