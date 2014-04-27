package com.dstevens.characters.powers;

import com.dstevens.characters.traits.EnumeratedTrait;

public enum ElderPower implements EnumeratedTrait<ElderPower> {

    CRIMSON_FURY(Discipline.ANIMALISM),
    INTIMIDATE_THE_BEAST(Discipline.ANIMALISM),
    
    CLAIRVOYANCE(Discipline.AUSPEX),
    PSYCHIC_ASSAULT(Discipline.AUSPEX),
    
    QUICKNESS(Discipline.CELERITY),
    PROJECTILE(Discipline.CELERITY),
    
    SHARED_NIGHTMARE(Discipline.CHIMERSTRY),
    ARMY_OF_APPARITIONS(Discipline.CHIMERSTRY),
    
    INFERNAL_COMPACT(Discipline.DAIMOINON),
    
    LINGERING_MALAISE(Discipline.DEMENTATION),
    DENY(Discipline.DEMENTATION),
    
    MASS_MANIPULATION(Discipline.DOMINATE),
    TYRANTS_GAZE(Discipline.DOMINATE),
    
    PERSONAL_ARMOR(Discipline.FORTITUDE),
    REPAIR_THE_UNDEAD_FLESH(Discipline.FORTITUDE),
    
    SHATTERING_CRESCENDO(Discipline.MELPOMINEE),
    PERSISTENT_ECHO(Discipline.MELPOMINEE),
    
    STEAL_THE_MIND(Discipline.MYTHERCERIA),
    
    UNBURDEN_THE_BEASTIAL_SOUL(Discipline.OBEAH),
    
    CACHE(Discipline.OBFUSCATE),
    PHANTOM_HUNTER(Discipline.OBFUSCATE),
    
    SHADOWSTEP(Discipline.OBTENEBRATION),
    SHADOW_TWIN(Discipline.OBTENEBRATION),
    
    FORCE(Discipline.POTENCE),
    FLICK(Discipline.POTENCE),
    
    PARALYZING_GLANCE(Discipline.PRESENCE),
    LOVE(Discipline.PRESENCE),
    
    EARTH_CONTROL(Discipline.PROTEAN),
    SHAPE_MASTERY(Discipline.PROTEAN),
    
    BLOOD_SWEAT(Discipline.QUIETUS),
    BAALS_BLOODY_TALONS(Discipline.QUIETUS),
    
    SEED_OF_CORRUPTION(Discipline.SERPENTIS),
    DIVINE_IMAGE(Discipline.SERPENTIS),
    
    KISS_OF_LACHESIS(Discipline.TEMPORSIS),
    
    CORRUPT_THE_FLESH(Discipline.THANATOSIS),
    
    FIERY_AGONY(Discipline.VALEREN),
    
    BREATH_OF_THE_DRAGON(Discipline.VICISSITUDE),
    ACID_BLOOD(Discipline.VICISSITUDE),
    
    BULWARK(Discipline.VISCERATIKA),
    FURNACE_OF_STEEL(Discipline.VISCERATIKA);
    
    private Discipline power;

    public Discipline getPower() {
        return power;
    }
    
    private ElderPower(Discipline power) {
        this.power = power;
    }

    @Override
    public ElderPower trait() {
        return this;
    }
    
}
