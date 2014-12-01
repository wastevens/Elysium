package com.dstevens.game.traitchangebuilders;

import com.dstevens.characters.changes.DistinctionFactory;
import com.dstevens.characters.changes.SetDistinction;
import com.dstevens.characters.changes.SetTrait;
import com.dstevens.characters.changes.SpendXp;
import com.dstevens.characters.changes.TraitChangeStatus;
import com.dstevens.characters.distinctions.Merit;
import com.dstevens.game.TraitChangeBuilder;

public class MeritTraitChangeBuilder implements TraitChangeBuilder {

    private Merit<?> merit;
    private String details;
    private SetTrait traitChange;

    public MeritTraitChangeBuilder(Merit<?> merit) {
        this.merit = merit;
    }

    public MeritTraitChangeBuilder withDetails(String details) {
        this.details = details;
        return this;
    }
    
    public MeritTraitChangeBuilder withTraitChange(SetTrait traitChange) {
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
