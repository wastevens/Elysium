package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;



public interface Power<T> extends ApplicableTrait {
	
	SetTrait set(TraitChangeStatus pending);
	SetTrait set(TraitChangeStatus status, int rating);

	
}
