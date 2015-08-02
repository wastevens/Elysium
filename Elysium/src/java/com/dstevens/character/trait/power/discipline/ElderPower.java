package com.dstevens.character.trait.power.discipline;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.TraitQualities;
import com.dstevens.utilities.IdentityUtilities;


public enum ElderPower implements Trait, ApplicableTrait {

    CRIMSON_FURY(0, Discipline.ANIMALISM),
    INTIMIDATE_THE_BEAST(1, Discipline.ANIMALISM),
    
    CLAIRVOYANCE(2, Discipline.AUSPEX),
    PSYCHIC_ASSAULT(3, Discipline.AUSPEX),
    
    QUICKNESS(4, Discipline.CELERITY),
    PROJECTILE(5, Discipline.CELERITY),
    
    SHARED_NIGHTMARE(6, Discipline.CHIMERSTRY),
    ARMY_OF_APPARITIONS(7, Discipline.CHIMERSTRY),
    
    INFERNAL_COMPACT(8, Discipline.DAIMOINON),
    
    LINGERING_MALAISE(9, Discipline.DEMENTATION),
    DENY(10, Discipline.DEMENTATION),
    
    MASS_MANIPULATION(11, Discipline.DOMINATE),
    TYRANTS_GAZE(12, Discipline.DOMINATE),
    
    PERSONAL_ARMOR(13, Discipline.FORTITUDE),
    REPAIR_THE_UNDEAD_FLESH(14, Discipline.FORTITUDE),
    
    SHATTERING_CRESCENDO(15, Discipline.MELPOMINEE),
    PERSISTENT_ECHO(16, Discipline.MELPOMINEE),
    
    STEAL_THE_MIND(17, Discipline.MYTHERCERIA),
    
    UNBURDEN_THE_BEASTIAL_SOUL(18, Discipline.OBEAH),
    
    CACHE(19, Discipline.OBFUSCATE),
    PHANTOM_HUNTER(20, Discipline.OBFUSCATE),
    
    SHADOWSTEP(21, Discipline.OBTENEBRATION),
    SHADOW_TWIN(22, Discipline.OBTENEBRATION),
    
    FORCE(23, Discipline.POTENCE),
    FLICK(24, Discipline.POTENCE),
    
    PARALYZING_GLANCE(25, Discipline.PRESENCE),
    LOVE(26, Discipline.PRESENCE),
    
    EARTH_CONTROL(27, Discipline.PROTEAN),
    SHAPE_MASTERY(28, Discipline.PROTEAN),
    
    BLOOD_SWEAT(29, Discipline.QUIETUS),
    BAALS_BLOODY_TALONS(30, Discipline.QUIETUS),
    
    SEED_OF_CORRUPTION(31, Discipline.SERPENTIS),
    DIVINE_IMAGE(32, Discipline.SERPENTIS),
    
    KISS_OF_LACHESIS(33, Discipline.TEMPORSIS),
    
    CORRUPT_THE_FLESH(34, Discipline.THANATOSIS),
    
    FIERY_AGONY(35, Discipline.VALEREN),
    
    BREATH_OF_THE_DRAGON(36, Discipline.VICISSITUDE),
    ACID_BLOOD(37, Discipline.VICISSITUDE),
    
    BULWARK(38, Discipline.VISCERATIKA),
    FURNACE_OF_STEEL(39, Discipline.VISCERATIKA);
    
    private final int id;
    private final Discipline power;
    
    private ElderPower(int id, Discipline power) {
    	this.id = id;
		this.power = power;
    }

	public static ElderPower from(int id) {
		return IdentityUtilities.withId(id, ElderPower.values());
	}
    
	@Override
	public Integer getId() {
		return id;
	}
    
    public Discipline getPower() {
        return power;
    }

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withElderPower(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutElderPower(this);
	}

	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return this;
	}
}
