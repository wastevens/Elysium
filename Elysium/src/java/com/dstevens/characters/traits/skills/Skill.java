package com.dstevens.characters.traits.skills;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.DetailLevel;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;

public enum Skill implements Trait {
    
    ACADEMICS(DetailLevel.REQUIRES_FOCUS),
    ANIMAL_KEN,
    ATHLETICS,
    AWARENESS,
    BRAWL,
    COMPUTER,
    CRAFTS(DetailLevel.REQUIRES_SPECIALIZATION),
    DODGE(DetailLevel.REQUIRES_FOCUS),
    DRIVE,
    EMPATHY,
    FIREARMS,
    INTIMIDATION,
    INVESTIGATION,
    LEADERSHIP,
    LINGUISTICS(DetailLevel.REQUIRES_FOCUS),
    LORE(DetailLevel.REQUIRES_FOCUS),
    MEDICINE,
    MELEE,
    OCCULT,
    PERFORMANCE(DetailLevel.REQUIRES_SPECIALIZATION),
    SCIENCE(DetailLevel.REQUIRES_SPECIALIZATION),
    SECURITY,
    STEALTH,
    STREETWISE,
    SUBTERFUGE,
    SURVIVAL;
    
    private final DetailLevel detailLevel;

    private Skill() {
        this(DetailLevel.NONE);
    }
    
    private Skill(DetailLevel detailLevel) {
        this.detailLevel = detailLevel;
    }
    
    @Override
    public int id() {
    	return 0;
    }
    
    public DetailLevel detailLevel() {
    	return detailLevel;
    }
    
    public ApplicableTrait applyWith(TraitQualities qualities) {
    	return new CharacterSkill(this, qualities.rating, qualities.specialization, qualities.focuses);
    }
}
