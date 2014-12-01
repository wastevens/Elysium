package com.dstevens.game.traitchangebuilders;

import com.dstevens.characters.changes.DistinctionFactory;
import com.dstevens.characters.changes.GainXp;
import com.dstevens.characters.changes.SetDistinction;
import com.dstevens.characters.changes.SetTrait;
import com.dstevens.characters.changes.TraitChangeStatus;
import com.dstevens.characters.distinctions.Flaw;
import com.dstevens.game.TraitChangeBuilder;

public class FlawTraitChangeBuilder implements TraitChangeBuilder {

    private Flaw<?> flaw;
    private String details;
    private SetTrait traitChange;

    public FlawTraitChangeBuilder(Flaw<?> flaw) {
        this.flaw = flaw;
    }

    public FlawTraitChangeBuilder withDetails(String details) {
        this.details = details;
        return this;
    }
    
    public FlawTraitChangeBuilder withTraitChange(SetTrait traitChange) {
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
