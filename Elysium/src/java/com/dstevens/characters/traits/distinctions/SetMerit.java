package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.traits.SetEnumeratedTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Merit")
public class SetMerit extends SetEnumeratedTrait<CharacterMerit> {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
	private CharacterMerit trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetMerit() {
        this(null, null);
    }
	
	protected SetMerit(TraitChangeStatus status, CharacterMerit trait) {
		super(status);
		this.trait = trait;
	}

	@Override
	protected CharacterMerit trait() {
		return trait;
	}

}
