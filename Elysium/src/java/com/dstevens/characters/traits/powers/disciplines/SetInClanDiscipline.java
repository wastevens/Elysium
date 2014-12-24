package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanDiscipline")
class SetInClanDiscipline extends TraitChange<Discipline> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanDiscipline() {
    	super();
    }
    
    public SetInClanDiscipline(int ordinal) {
    	super(ordinal);
    }

	@Override
	protected Discipline trait() {
		return Discipline.values()[ordinal];
	}
}
