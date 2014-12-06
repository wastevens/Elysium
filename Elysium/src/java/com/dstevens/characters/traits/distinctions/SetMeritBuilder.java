package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.SpendXp;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetMeritBuilder implements TraitChangeBuilder {

    private Merit<?> merit;
    private String details;
    private SetTrait traitChange;

    public SetMeritBuilder(Merit<?> merit) {
        this.merit = merit;
    }

    public SetMeritBuilder withDetails(String details) {
        this.details = details;
        return this;
    }
    
    public SetMeritBuilder withTraitChange(SetTrait traitChange) {
        this.traitChange = traitChange;
        return this;
    }
    
    @Override
    public SetTrait buy() {
        return new SpendXp(TraitChangeStatus.PENDING, merit.getPoints()).and(setDistinction());
    }

    @Override
    public SetTrait add() {
        return setDistinction();
    }

    private SetTrait setDistinction() {
        return new SetDistinction(TraitChangeStatus.PENDING, merit, details, DistinctionFactory.MERIT).and(traitChange);
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
