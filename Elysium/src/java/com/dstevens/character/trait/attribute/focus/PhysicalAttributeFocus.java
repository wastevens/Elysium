package com.dstevens.character.trait.attribute.focus;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.TraitQualities;

public enum PhysicalAttributeFocus implements Trait, ApplicableTrait {

    STRENGTH(0),
    DEXTERITY(1),
    STAMINA(2);

    private final int id;

	private PhysicalAttributeFocus(int id) {
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
		return character.withPhysicalAttributeFocus(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutPhysicalAttributeFocus(this);
	}
	
}
