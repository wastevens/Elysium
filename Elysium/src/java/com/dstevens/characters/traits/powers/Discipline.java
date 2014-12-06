package com.dstevens.characters.traits.powers;

import com.dstevens.characters.PlayerCharacter;


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
	public Discipline trait() {
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
}