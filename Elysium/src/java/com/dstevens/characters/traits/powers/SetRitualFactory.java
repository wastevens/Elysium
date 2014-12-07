package com.dstevens.characters.traits.powers;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetRitualFactory {

	public SetTrait setRitual(Ritual<?> ritual) {
    	if(ritual instanceof ThaumaturgicalRitual) {
    		return new SetThaumaturgicalRitual(TraitChangeStatus.PENDING, ritual.ordinal());
    	} else if(ritual instanceof NecromanticRitual) {
    		return new SetNecromanticRitual(TraitChangeStatus.PENDING, ritual.ordinal());
    	}
    	throw new IllegalArgumentException("Could not find a trait factory for " + ritual);
    }
	
}
