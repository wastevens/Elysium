package com.dstevens.characters.traits.changes;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.characters.traits.TraitType;

@Service
class ProvidedTraitChangeFactory implements TraitChangeFactory {
	
	public TraitChange traitChange(TraitType traitType, Trait trait, TraitQualities qualities) {
		return new TraitChange(traitType, trait.id(), qualities);
	}
}
