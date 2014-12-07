package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanDiscipline")
class SetInClanDiscipline extends SetApplicableTrait<Discipline> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanDiscipline() {
        this(null, 0);
    }
    
    public SetInClanDiscipline(TraitChangeStatus status, int ordinal) {
    	super(status, ordinal);
    }

	@Override
	protected Discipline trait() {
		return Discipline.values()[ordinal];
	}
}
