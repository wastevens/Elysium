package com.dstevens.characters.traits.backgrounds;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.DetailLevel;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;

public enum Background implements Trait {

    ALLIES(0, DetailLevel.REQUIRES_FOCUS),
    ALTERNATE_IDENTITY(1, DetailLevel.REQUIRES_SPECIALIZATION),
    CONTACTS(2, DetailLevel.REQUIRES_FOCUS),
    FAME(3, DetailLevel.REQUIRES_SPECIALIZATION),
    GENERATION(4),
    HAVEN(5, DetailLevel.REQUIRES_SPECIALIZATION_AND_FOCUS),
    HERD(6),
    ELITE_INFLUENCE(7, DetailLevel.REQUIRES_FOCUS),
    UNDERWORLD_INFLUENCE(8, DetailLevel.REQUIRES_FOCUS),
    RESOURCES(9),
    RETAINER(10, DetailLevel.REQUIRES_SPECIALIZATION_AND_FOCUS),
    RITUALS(11, DetailLevel.REQUIRES_FOCUS);
    
    private final DetailLevel detailLevel;
	private final int id;

    private Background(int id) {
        this(id, DetailLevel.NONE);
    }

    private Background(int id, DetailLevel detailLevel) {
        this.id = id;
		this.detailLevel = detailLevel;
    }
    
	@Override
	public Integer getId() {
		return id;
	}
    
    public DetailLevel detailLevel() {
    	return detailLevel;
    }
    
    public ApplicableTrait applyWith(TraitQualities qualities) {
    	return CharacterBackground.backgroundFor(this, qualities.rating, qualities.specialization, qualities.focuses);
    }
}
