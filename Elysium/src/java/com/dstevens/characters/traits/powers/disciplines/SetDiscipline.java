package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Discipline")
class SetDiscipline extends ApplicableTraitChange<CharacterDiscipline> {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetDiscipline() {
    	super();
    }
	
    public SetDiscipline(TraitChangeStatus status, int ordinal, int rating) {
    	super(ordinal, rating);
    }
    
    @Override
    protected CharacterDiscipline trait() {
    	return new CharacterDiscipline(Discipline.values()[ordinal], rating);
    }

}
