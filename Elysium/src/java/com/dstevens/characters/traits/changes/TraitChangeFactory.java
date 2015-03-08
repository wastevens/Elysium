package com.dstevens.characters.traits.changes;

import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.characters.traits.Traits;

public interface TraitChangeFactory {
	
	TraitChange traitChange(Traits traitType, Enum<? extends Trait> trait, TraitQualities qualities);

}