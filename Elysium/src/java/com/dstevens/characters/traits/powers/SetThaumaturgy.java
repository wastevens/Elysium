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
@DiscriminatorValue("Thaumaturgy")
class SetThaumaturgy extends SetApplicableTrait<CharacterThaumaturgy> {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
    private CharacterThaumaturgy trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetThaumaturgy() {
        this(null, null);
    }
    
    public SetThaumaturgy(TraitChangeStatus status, CharacterThaumaturgy trait) {
    	super(status);
		this.trait = trait;
    }
    
    @Override
    protected CharacterThaumaturgy trait() {
    	return trait;
    }
}
