package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.ChangeExperience;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetAttributeValueBuilder implements TraitChangeBuilder {

    private AttributeValue trait;

	public SetAttributeValueBuilder(AttributeValue trait) {
		this.trait = trait;
    }

	@Override
	public SetTrait buy() {
		return ChangeExperience.spend(3).and(setAttributeValue());
	}

	@Override
	public SetTrait add() {
		return setAttributeValue();
	}
	

	private SetAttributeValue setAttributeValue() {
		return new SetAttributeValue(TraitChangeStatus.PENDING, trait);
	}

	@Override
	public SetTrait sell() {
		throw new IllegalStateException("NYI");
	}

	@Override
	public SetTrait remove() {
		throw new IllegalStateException("NYI");
	}
	
}
