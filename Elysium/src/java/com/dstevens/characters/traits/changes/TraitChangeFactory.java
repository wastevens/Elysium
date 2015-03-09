package com.dstevens.characters.traits.changes;

import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.characters.traits.TraitType;

public interface TraitChangeFactory {
	
	TraitChange traitChange(TraitType traitType, Trait trait, TraitQualities qualities);

}