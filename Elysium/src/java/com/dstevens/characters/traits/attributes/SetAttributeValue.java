package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Attribute")
class SetAttributeValue extends SetApplicableTrait<AttributeValue> {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
	private AttributeValue trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetAttributeValue() {
        this(null, null);
    }
	
	public SetAttributeValue(TraitChangeStatus status, AttributeValue trait) {
		super(status);
		this.trait = trait;
	}

	@Override
	protected AttributeValue trait() {
		return trait;
	}

}
