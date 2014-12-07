package com.dstevens.characters.traits.powers;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetInClanPowerFactory {

	public SetTrait power(Power<?> power) {
		if(power instanceof Discipline) {
			return new SetInClanDiscipline(TraitChangeStatus.PENDING, power.ordinal());
		}
		if(power instanceof Thaumaturgy) {
			return new SetInClanThaumaturgy(TraitChangeStatus.PENDING, power.ordinal());
		}
		if(power instanceof Necromancy) {
			return new SetInClanNecromancy(TraitChangeStatus.PENDING, power.ordinal());
		}
		throw new IllegalArgumentException("Could not create SetInClanPower for " + power);
	}
	
}
