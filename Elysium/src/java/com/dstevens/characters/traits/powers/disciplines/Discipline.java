package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.powers.Power;


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
	public TraitChange set() {
		return new SetInClanDiscipline(this.ordinal());
	}
	
	@Override
	public TraitChange set(int rating) {
		return new SetDiscipline(this.ordinal(), rating);
	}
}
