package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.ChangeXp;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetRitualBuilder implements TraitChangeBuilder {

    private final Ritual<?> ritual;

    public SetRitualBuilder(Ritual<?> ritual) {
		this.ritual = ritual;
    }
    
    @Override
    public SetTrait buy() {
		return ChangeXp.spend(ritual.rating() * 2).and(setRitual());
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
