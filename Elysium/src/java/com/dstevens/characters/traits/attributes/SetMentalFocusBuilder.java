package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetMentalFocusBuilder implements TraitChangeBuilder {

    private MentalAttributeFocus trait;

	public SetMentalFocusBuilder(MentalAttributeFocus trait) {
		this.trait = trait;
    }
    
    @Override
    public SetTrait buy() {
		throw new IllegalStateException("You can't buy an attribute focus");
    }

    @Override
    public SetTrait add() {
        return setAttribute();
    }

    private SetTrait setAttribute() {
    	return new SetMentalFocus(TraitChangeStatus.PENDING, trait);
    }

	@Override
	public SetTrait sell() {
		throw new IllegalStateException("not yet implemented");
	}

	@Override
	public SetTrait remove() {
		throw new IllegalStateException("not yet implemented");
	}
    
}
