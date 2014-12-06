package com.dstevens.characters.traits.changes.builders;

import com.dstevens.characters.traits.changes.SetNecromanticRitual;
import com.dstevens.characters.traits.changes.SetThaumaturgicalRitual;
import com.dstevens.characters.traits.changes.SetTrait;
import com.dstevens.characters.traits.changes.SpendXp;
import com.dstevens.characters.traits.changes.TraitChangeStatus;
import com.dstevens.characters.traits.powers.NecromanticRitual;
import com.dstevens.characters.traits.powers.Ritual;
import com.dstevens.characters.traits.powers.ThaumaturgicalRitual;
import com.dstevens.game.TraitChangeBuilder;

public class RitualChangeBuilder implements TraitChangeBuilder {

    private final Ritual<?> ritual;

    public RitualChangeBuilder(Ritual<?> ritual) {
		this.ritual = ritual;
    }
    
    @Override
    public SetTrait buy() {
		return new SpendXp(TraitChangeStatus.PENDING, ritual.rating() * 2).and(setRitual());
    }

    @Override
    public SetTrait add() {
        return setRitual();
    }

    private SetTrait setRitual() {
    	if(ritual instanceof ThaumaturgicalRitual) {
    		return new SetThaumaturgicalRitual(TraitChangeStatus.PENDING, (ThaumaturgicalRitual) ritual);
    	} else if(ritual instanceof NecromanticRitual) {
    		return new SetNecromanticRitual(TraitChangeStatus.PENDING, (NecromanticRitual) ritual);
    	}
    	throw new IllegalArgumentException("Could not find a trait factory for " + ritual);
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
