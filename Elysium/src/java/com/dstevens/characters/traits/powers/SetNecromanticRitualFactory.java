package com.dstevens.characters.traits.powers;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetNecromanticRitualFactory {

	public SetTrait setRitual(NecromanticRitual ritual) {
		return new SetNecromanticRitual(TraitChangeStatus.PENDING, ritual.ordinal());
    }
	
}
