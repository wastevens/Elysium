package com.dstevens.characters.traits.powers;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;


public enum Discipline implements Power<Discipline> {

    ANIMALISM,
    AUSPEX,
    CELERITY,
    DOMINATE,
    FORTITUDE,
    OBFUSCATE,
    POTENCE,
    PRESENCE,
    DEMENTATION,
    PROTEAN,
    QUIETUS,
    SERPENTIS,
    CHIMERSTRY,
    DAIMOINON,
    MELPOMINEE,
    MYTHERCERIA,
    OBEAH,
    OBTENEBRATION,
    TEMPORSIS,
    THANATOSIS,
    VALEREN,
    VICISSITUDE,
    VISCERATIKA;

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
		return new SetInClanDiscipline(status, this.ordinal());
	}
	
	@Override
	public SetTrait set(TraitChangeStatus status, int rating) {
		return new SetDiscipline(status, this.ordinal(), rating);
	}
}
