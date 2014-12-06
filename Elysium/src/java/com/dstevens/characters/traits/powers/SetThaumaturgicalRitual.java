package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetEnumeratedTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ThaumaturgicalRitual")
public class SetThaumaturgicalRitual extends SetEnumeratedTrait<ThaumaturgicalRitual> {

	@Column(name="trait_ordinal")
    private ThaumaturgicalRitual trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetThaumaturgicalRitual() {
        this(null, null);
    }
    
    public SetThaumaturgicalRitual(TraitChangeStatus status, ThaumaturgicalRitual trait) {
    	super(status);
		this.trait = trait;
    }

    @Override
    protected ThaumaturgicalRitual trait() {
    	return trait;
    }
	
}
