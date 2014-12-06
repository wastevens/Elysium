package com.dstevens.characters.traits.distinctions;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetFlawFactory {

	public SetTrait flaw(Flaw trait, String specialization, SetTrait associatedTrait) {
		return new SetFlaw(TraitChangeStatus.PENDING, new CharacterFlaw(trait, specialization)).and(associatedTrait);
	}
	
}
