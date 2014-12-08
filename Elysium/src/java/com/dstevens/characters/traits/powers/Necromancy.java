package com.dstevens.characters.traits.powers;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

public enum Necromancy implements Power<Necromancy> {

    SEPULCHRE_PATH,
    BONE_PATH,
    ASH_PATH,
    MORTIS_PATH;
    
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withInClanDiscipline(this);
	}
	
	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutInClanDiscipline(this);
	}
	
	@Override
	public SetTrait set(TraitChangeStatus status) {
		return new SetInClanNecromancy(status, this.ordinal());
	}
	
	@Override
	public SetTrait set(TraitChangeStatus status, int rating) {
		return new SetNecromancy(status, this.ordinal(), rating);
	}
}
