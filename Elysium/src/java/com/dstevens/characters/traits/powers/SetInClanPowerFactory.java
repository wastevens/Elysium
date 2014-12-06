package com.dstevens.characters.traits.powers;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetInClanPowerFactory {

	public SetTrait add(Power<?> power) {
		if(power instanceof Discipline) {
			return new SetInClanDiscipline(TraitChangeStatus.PENDING, (Discipline) power);
		}
		if(power instanceof Thaumaturgy) {
			return new SetInClanThaumaturgy(TraitChangeStatus.PENDING, (Thaumaturgy) power);
		}
		if(power instanceof Necromancy) {
			return new SetInClanNecromancy(TraitChangeStatus.PENDING, (Necromancy) power);
		}
		throw new IllegalArgumentException("Could not create SetInClanPower for " + power);
	}
	
}
