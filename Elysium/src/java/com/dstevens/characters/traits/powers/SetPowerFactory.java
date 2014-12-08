package com.dstevens.characters.traits.powers;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetPowerFactory {

	public SetTrait setPower(Power<?> power, int rating) {
    	if(power instanceof Discipline) {
    		return new SetDiscipline(TraitChangeStatus.PENDING, power.ordinal(), rating);
    	} else if(power instanceof Thaumaturgy) {
    		return new SetThaumaturgy(TraitChangeStatus.PENDING, power.ordinal(), rating);
    	} else if(power instanceof Necromancy) {
    		return new SetNecromancy(TraitChangeStatus.PENDING, power.ordinal(), rating);
    	}
    	throw new IllegalArgumentException("Cannot find an implementing power type for " + power);
    }
	
}
