package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Technique")
class SetTechnique extends TraitChange<Technique> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetTechnique() {
    	super();
    }
    
    public SetTechnique(int ordinal) {
    	super(ordinal);
    }

	@Override
	protected Technique trait() {
		return Technique.values()[ordinal];
	}

}
