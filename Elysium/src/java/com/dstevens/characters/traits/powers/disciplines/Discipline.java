package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.PowerType;


public enum Discipline implements Power<Discipline> {

    ANIMALISM(PowerType.DISCIPLINE),
    AUSPEX(PowerType.DISCIPLINE),
    CELERITY(PowerType.DISCIPLINE),
    CHIMERSTRY(PowerType.DISCIPLINE),
    DAIMOINON(PowerType.DISCIPLINE),
    DEMENTATION(PowerType.DISCIPLINE),
    DOMINATE(PowerType.DISCIPLINE),
    FORTITUDE(PowerType.DISCIPLINE),
    MELPOMINEE(PowerType.DISCIPLINE),
    MYTHERCERIA(PowerType.DISCIPLINE),
    OBEAH(PowerType.DISCIPLINE),
    OBFUSCATE(PowerType.DISCIPLINE),
    OBTENEBRATION(PowerType.DISCIPLINE),
    POTENCE(PowerType.DISCIPLINE),
    PRESENCE(PowerType.DISCIPLINE),
    PROTEAN(PowerType.DISCIPLINE),
    QUIETUS(PowerType.DISCIPLINE),
    SERPENTIS(PowerType.DISCIPLINE),
    TEMPORSIS(PowerType.DISCIPLINE),
    THANATOSIS(PowerType.DISCIPLINE),
    VALEREN(PowerType.DISCIPLINE),
    VICISSITUDE(PowerType.DISCIPLINE),
    VISCERATIKA(PowerType.DISCIPLINE),
    
    SEPULCHRE_PATH(PowerType.NECROMANCY),
    BONE_PATH(PowerType.NECROMANCY),
    ASH_PATH(PowerType.NECROMANCY),
    MORTIS_PATH(PowerType.NECROMANCY),
    
    PATH_OF_BLOOD(PowerType.THAUMATURGY),
    PATH_OF_CONJURING(PowerType.THAUMATURGY),
    PATH_OF_CORRUPTION(PowerType.THAUMATURGY),
    PATH_OF_ELEMENTAL_MASTERY(PowerType.THAUMATURGY),
    LURE_OF_FLAMES(PowerType.THAUMATURGY),
    MOVEMENT_OF_THE_MIND(PowerType.THAUMATURGY),
    PATH_OF_TECHNOMANCY(PowerType.THAUMATURGY),
    PATH_OF_WEATHER_MASTERY(PowerType.THAUMATURGY);

    private final PowerType powerType;

	private Discipline(PowerType powerType) {
		this.powerType = powerType;
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
	public TraitChange<Discipline> set() {
		return new SetInClanDiscipline(this.ordinal());
	}
	
	@Override
	public TraitChange<CharacterDiscipline> set(int rating) {
		return new SetDiscipline(this.ordinal(), rating);
	}

	public PowerType getPowerType() {
		return powerType;
	}
}
