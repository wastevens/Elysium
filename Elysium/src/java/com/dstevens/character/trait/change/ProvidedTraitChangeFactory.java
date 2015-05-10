package com.dstevens.character.trait.change;

import org.springframework.stereotype.Service;

import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.TraitQualities;
import com.dstevens.character.trait.TraitType;

@Service
class ProvidedTraitChangeFactory implements TraitChangeFactory {
	
	public TraitChange traitChange(TraitType traitType, Trait trait, TraitQualities qualities) {
		return new TraitChange(traitType, trait.getId(), qualities);
	}
}
