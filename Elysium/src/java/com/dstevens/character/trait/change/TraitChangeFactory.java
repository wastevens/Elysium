package com.dstevens.character.trait.change;

import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.TraitQualities;
import com.dstevens.character.trait.TraitType;

public interface TraitChangeFactory {
	
	TraitChange traitChange(TraitType traitType, Trait trait, TraitQualities qualities);

}