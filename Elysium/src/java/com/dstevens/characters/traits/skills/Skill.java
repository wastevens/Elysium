package com.dstevens.characters.traits.skills;

import java.util.Set;

import com.dstevens.characters.traits.DetailLevel;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeSource;

public enum Skill implements TraitChangeSource<CharacterSkill> {
    
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
    
    public DetailLevel detailLevel() {
    	return detailLevel;
    }
    
    public TraitChange<CharacterSkill> set(int rating, String specialization, Set<String> focuses) {
    	return new SetSkill(this.ordinal(), rating, specialization, focuses);
    }
}
