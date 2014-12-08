package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Technique")
class SetTechnique extends SetApplicableTrait<Technique> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetTechnique() {
        this(null, 0);
    }
    
    public SetTechnique(TraitChangeStatus status, int ordinal) {
    	super(status, ordinal);
    }

	@Override
	protected Technique trait() {
		return Technique.values()[ordinal];
	}

}
