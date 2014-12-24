package com.dstevens.characters.traits.backgrounds;

import java.util.Set;

import com.dstevens.characters.traits.DetailLevel;
import com.dstevens.characters.traits.changes.TraitChange;

public enum Background {

    ALLIES(DetailLevel.REQUIRES_FOCUS),
    ALTERNATE_IDENTITY(DetailLevel.REQUIRES_SPECIALIZATION),
    CONTACTS(DetailLevel.REQUIRES_FOCUS),
    FAME(DetailLevel.REQUIRES_SPECIALIZATION),
    GENERATION,
    HAVEN(DetailLevel.REQUIRES_SPECIALIZATION_AND_FOCUS),
    HERD,
    ELITE_INFLUENCE(DetailLevel.REQUIRES_FOCUS),
    UNDERWORLD_INFLUENCE(DetailLevel.REQUIRES_FOCUS),
    RESOURCES,
    RETAINER(DetailLevel.REQUIRES_SPECIALIZATION_AND_FOCUS);
    
    private final DetailLevel detailLevel;

    private Background() {
        this(DetailLevel.NONE);
    }

    private Background(DetailLevel detailLevel) {
        this.detailLevel = detailLevel;
    }
    
    public DetailLevel detailLevel() {
    	return detailLevel;
    }
    
    public TraitChange set(int rating, String specialization, Set<String> focuses) {
    	return new SetBackground(this.ordinal(), rating, specialization, focuses);
    }
}
