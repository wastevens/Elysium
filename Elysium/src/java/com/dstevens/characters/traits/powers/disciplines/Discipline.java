package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.characters.traits.powers.PowerType;


public enum Discipline implements Trait, ApplicableTrait {

    ANIMALISM(0, PowerType.DISCIPLINE),
    AUSPEX(1, PowerType.DISCIPLINE),
    CELERITY(2, PowerType.DISCIPLINE),
    CHIMERSTRY(3, PowerType.DISCIPLINE),
    DAIMOINON(4, PowerType.DISCIPLINE),
    DEMENTATION(5, PowerType.DISCIPLINE),
    DOMINATE(6, PowerType.DISCIPLINE),
    FORTITUDE(7, PowerType.DISCIPLINE),
    MELPOMINEE(8, PowerType.DISCIPLINE),
    MYTHERCERIA(9, PowerType.DISCIPLINE),
    OBEAH(10, PowerType.DISCIPLINE),
    OBFUSCATE(11, PowerType.DISCIPLINE),
    OBTENEBRATION(12, PowerType.DISCIPLINE),
    POTENCE(13, PowerType.DISCIPLINE),
    PRESENCE(14, PowerType.DISCIPLINE),
    PROTEAN(15, PowerType.DISCIPLINE),
    QUIETUS(16, PowerType.DISCIPLINE),
    SERPENTIS(17, PowerType.DISCIPLINE),
    TEMPORSIS(18, PowerType.DISCIPLINE),
    THANATOSIS(19, PowerType.DISCIPLINE),
    VALEREN(20, PowerType.DISCIPLINE),
    VICISSITUDE(21, PowerType.DISCIPLINE),
    VISCERATIKA(22, PowerType.DISCIPLINE),
    
    SEPULCHRE_PATH(23, PowerType.NECROMANCY),
    BONE_PATH(24, PowerType.NECROMANCY),
    ASH_PATH(25, PowerType.NECROMANCY),
    MORTIS_PATH(26, PowerType.NECROMANCY),
    
    PATH_OF_BLOOD(27, PowerType.THAUMATURGY),
    PATH_OF_CONJURING(28, PowerType.THAUMATURGY),
    PATH_OF_CORRUPTION(29, PowerType.THAUMATURGY),
    PATH_OF_ELEMENTAL_MASTERY(30, PowerType.THAUMATURGY),
    LURE_OF_FLAMES(31, PowerType.THAUMATURGY),
    MOVEMENT_OF_THE_MIND(32, PowerType.THAUMATURGY),
    PATH_OF_TECHNOMANCY(33, PowerType.THAUMATURGY),
    PATH_OF_WEATHER_MASTERY(34, PowerType.THAUMATURGY);

    private final PowerType powerType;
	private final int id;

	private Discipline(int id, PowerType powerType) {
		this.id = id;
		this.powerType = powerType;
    }

	@Override
	public Integer getId() {
		return id;
	}
	
	public PowerType powerType() {
		return powerType;
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
