package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.characters.traits.powers.PowerType;


public enum Discipline implements Trait, ApplicableTrait {

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

    public final PowerType powerType;

	private Discipline(PowerType powerType) {
		this.powerType = powerType;
    }

	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		if(qualities.rating == -1) {
			return this;
		} 
		return new CharacterDiscipline(this, qualities.rating);
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
