package com.dstevens.characters.traits.powers;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;

public enum Necromancy implements Power<Necromancy> {

    SEPULCHRE_PATH,
    BONE_PATH,
    ASH_PATH,
    MORTIS_PATH;
    
	@Override
	public Necromancy trait() {
		return this;
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withInClanDiscipline(this);
	}
	
	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutInClanDiscipline(this);
	}
    
	@Override
	public ApplicableTrait copy() {
		return this;
	}
}
