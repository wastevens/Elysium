package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanDiscipline")
class SetInClanDiscipline extends ApplicableTraitChange<Discipline> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanDiscipline() {
    	super();
    }
    
    public SetInClanDiscipline(TraitChangeStatus status, int ordinal) {
    	super(ordinal);
    }

	@Override
	protected Discipline trait() {
		return Discipline.values()[ordinal];
	}
}
