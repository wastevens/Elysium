package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.ChangeXp;
import com.dstevens.characters.traits.GainExperience;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetMentalAttributeBuilder implements TraitChangeBuilder {

    private final int rating;

    public SetMentalAttributeBuilder(int rating) {
		this.rating = rating;
    }
    
    @Override
    public SetTrait buy() {
		return new ChangeXp(TraitChangeStatus.PENDING, new GainExperience(-3)).and(setAttribute());
    }

    @Override
    public SetTrait add() {
        return setAttribute();
    }

    private SetTrait setAttribute() {
    	return new SetMentalAttribute(TraitChangeStatus.PENDING, rating);
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
