package com.dstevens.characters.traits.powers;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetInClanThaumaturgyFactory {

	public SetTrait power(Thaumaturgy power) {
		return new SetInClanThaumaturgy(TraitChangeStatus.PENDING, power.ordinal());
	}
	
}
