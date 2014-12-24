package com.dstevens.characters.traits.backgrounds;

import java.util.Set;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Background")
class SetBackground extends ApplicableTraitChange<CharacterBackground> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetBackground() {
        super();
    }
    
    public SetBackground(int ordinal, int rating, String specialization, Set<String> focuses) {
    	super(ordinal, rating, specialization, focuses);
    }
    
    @Override
    protected CharacterBackground trait() {
    	return new CharacterBackground(Background.values()[ordinal], rating, specialization, focuses);
    }
}
