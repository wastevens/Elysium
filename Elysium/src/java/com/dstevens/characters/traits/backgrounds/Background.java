package com.dstevens.characters.traits.backgrounds;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.DetailLevel;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;

public enum Background implements Trait {

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
    RETAINER(DetailLevel.REQUIRES_SPECIALIZATION_AND_FOCUS),
    RITUALS(DetailLevel.REQUIRES_FOCUS);
    
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
    
    public ApplicableTrait applyWith(TraitQualities qualities) {
    	return CharacterBackground.backgroundFor(this, qualities.rating, qualities.specialization, qualities.focuses);
    }
}
