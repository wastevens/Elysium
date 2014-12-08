package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;



public interface Power<T> extends ApplicableTrait {
	
	TraitChange set(TraitChangeStatus pending);
	TraitChange set(TraitChangeStatus status, int rating);

	
}
