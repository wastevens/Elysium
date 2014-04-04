package com.dstevens.characters.skills;

import com.dstevens.characters.traits.DetailLevel;

public enum Skill {

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
    
}
