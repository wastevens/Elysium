package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Flaw")
class SetFlaw extends SetApplicableTrait<CharacterFlaw> {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="applicable_trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
	private final CharacterFlaw trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetFlaw() {
        this(null, null);
    }
	
	protected SetFlaw(TraitChangeStatus status, CharacterFlaw trait) {
		super(status);
		this.trait = trait;
	}

	@Override
	protected CharacterFlaw trait() {
		return trait;
	}
}
