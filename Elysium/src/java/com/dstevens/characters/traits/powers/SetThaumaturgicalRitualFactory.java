package com.dstevens.characters.traits.powers;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetThaumaturgicalRitualFactory {

	public SetTrait setRitual(ThaumaturgicalRitual ritual) {
		return new SetThaumaturgicalRitual(TraitChangeStatus.PENDING, ritual.ordinal());
    }
	
}
