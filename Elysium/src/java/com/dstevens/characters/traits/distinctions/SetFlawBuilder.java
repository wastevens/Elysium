package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.traits.GainXp;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetFlawBuilder implements TraitChangeBuilder {

    private Flaw<?> flaw;
    private String details;
    private SetTrait traitChange;

    public SetFlawBuilder(Flaw<?> flaw) {
        this.flaw = flaw;
    }

    public SetFlawBuilder withDetails(String details) {
        this.details = details;
        return this;
    }
    
    public SetFlawBuilder withTraitChange(SetTrait traitChange) {
        this.traitChange = traitChange;
        return this;
    }
    
    @Override
    public SetTrait buy() {
        return new GainXp(TraitChangeStatus.PENDING, flaw.getPoints()).and(setDistinction());
    }

    @Override
    public SetTrait add() {
        return setDistinction();
    }

    private SetTrait setDistinction() {
        return new SetDistinction(TraitChangeStatus.PENDING, flaw, details, DistinctionFactory.FLAW).and(traitChange);
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
