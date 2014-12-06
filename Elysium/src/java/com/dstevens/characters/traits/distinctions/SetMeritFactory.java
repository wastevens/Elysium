package com.dstevens.characters.traits.distinctions;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetMeritFactory {

	public SetTrait merit(Merit trait, String specialization, SetTrait associatedTrait) {
		return new SetMerit(TraitChangeStatus.PENDING, new CharacterMerit(trait, specialization)).and(associatedTrait);
	}
	
}
