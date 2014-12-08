package com.dstevens.characters.traits.powers.magic.necromancy;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;
import com.dstevens.characters.traits.powers.Power;

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
	public TraitChange set(TraitChangeStatus status) {
		return new SetInClanNecromancy(status, this.ordinal());
	}
	
	@Override
	public TraitChange set(TraitChangeStatus status, int rating) {
		return new SetNecromancy(status, this.ordinal(), rating);
	}
}
