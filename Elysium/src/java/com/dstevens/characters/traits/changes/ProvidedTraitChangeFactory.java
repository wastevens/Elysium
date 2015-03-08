package com.dstevens.characters.traits.changes;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.characters.traits.Traits;

@Service
class ProvidedTraitChangeFactory implements TraitChangeFactory {
	
	public TraitChange traitChange(Traits traitType, Enum<? extends Trait> trait, TraitQualities qualities) {
		return new TraitChange(traitType, trait.ordinal(), qualities);
	}
}
