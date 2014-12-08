package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Discipline")
class SetDiscipline extends SetApplicableTrait<CharacterDiscipline> {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetDiscipline() {
        this(null, 0, 0);
    }
	
    public SetDiscipline(TraitChangeStatus status, int ordinal, int rating) {
    	super(status, ordinal, rating);
    }
    
    @Override
    protected CharacterDiscipline trait() {
    	return new CharacterDiscipline(Discipline.values()[ordinal], rating);
    }

}
