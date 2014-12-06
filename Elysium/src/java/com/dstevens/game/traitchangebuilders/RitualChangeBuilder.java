package com.dstevens.game.traitchangebuilders;

import com.dstevens.characters.changes.SetEnumeratedTrait;
import com.dstevens.characters.changes.SetThaumaturgicalRitual;
import com.dstevens.characters.changes.SetTrait;
import com.dstevens.characters.changes.SpendXp;
import com.dstevens.characters.changes.TraitChangeStatus;
import com.dstevens.characters.changes.TraitFactory;
import com.dstevens.characters.powers.magics.NecromanticRitual;
import com.dstevens.characters.powers.magics.Ritual;
import com.dstevens.characters.powers.magics.ThaumaturgicalRitual;
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
    		return new SetEnumeratedTrait(TraitChangeStatus.PENDING, ritual, TraitFactory.NECROMANTIC_RITUAL);
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
