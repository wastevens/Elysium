package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetEnumeratedTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanDiscipline")
public class SetInClanDiscipline extends SetEnumeratedTrait<Discipline> {

	@Column(name="trait_ordinal")
    private Discipline trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanDiscipline() {
        this(null, null);
    }
    
    public SetInClanDiscipline(TraitChangeStatus status, Discipline trait) {
    	super(status);
		this.trait = trait;
    }

	@Override
	protected Discipline trait() {
		return trait;
	}
}
