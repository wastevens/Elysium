package com.dstevens.characters.traits.powers;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetInClanDisciplineFactory {

	public SetTrait power(Discipline power) {
		return new SetInClanDiscipline(TraitChangeStatus.PENDING, power.ordinal());
	}
	
}
