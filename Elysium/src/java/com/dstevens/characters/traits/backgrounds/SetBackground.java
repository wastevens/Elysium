package com.dstevens.characters.traits.backgrounds;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Background")
class SetBackground extends ApplicableTraitChange<CharacterBackground> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetBackground() {
        this(null, 0, 0, null, set());
    }
    
    public SetBackground(TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
    	super(status, ordinal, rating, specialization, focuses);
    }
    
    @Override
    protected CharacterBackground trait() {
    	return new CharacterBackground(Background.values()[ordinal], rating, specialization, focuses);
    }
}
