package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Necromancy")
class SetNecromancy extends SetApplicableTrait<CharacterNecromancy> {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="applicable_trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
    private CharacterNecromancy trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetNecromancy() {
        this(null, null);
    }
    
    public SetNecromancy(TraitChangeStatus status, CharacterNecromancy trait) {
    	super(status);
		this.trait = trait;
    }

	@Override
	protected CharacterNecromancy trait() {
		return trait;
	}

}
