package com.dstevens.characters.traits.powers;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetElderPowerFactory {

	public SetTrait elderPower(ElderPower trait) {
		return new SetElderPower(TraitChangeStatus.PENDING, trait);
	}
	
}
