package com.dstevens.characters.traits;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.AttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.distinctions.Flaw;
import com.dstevens.characters.traits.distinctions.Merit;
import com.dstevens.characters.traits.powers.ElderPower;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.Ritual;
import com.dstevens.characters.traits.powers.Technique;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.status.Status;

@Service
class ProvidedTraitChangeFactory implements TraitChangeFactory {
	
	@Override
	public SetTrait attribute(Attribute attribute, int rating) {
		return attribute.set(TraitChangeStatus.PENDING, rating);
	}

	@Override
	public SetTrait focus(AttributeFocus focus) {
		return focus.set(TraitChangeStatus.PENDING);
	}
	
	@Override
	public SetTrait skill(Skill skill, int rating, String specialization, Set<String> focuses) {
		return skill.set(TraitChangeStatus.PENDING, rating, specialization, focuses);
	}

	@Override
	public SetTrait background(Background background, int rating, String specialization, Set<String> focuses) {
		return background.set(TraitChangeStatus.PENDING, rating, specialization, focuses);
	}
	
	@Override
	public SetTrait power(Power<?> power, int rating) {
		return power.set(TraitChangeStatus.PENDING, rating);
	}

	@Override
	public SetTrait ritual(Ritual<?> ritual) {
		return ritual.set(TraitChangeStatus.PENDING);
	}
	
	 @Override
	public SetTrait merit(Merit merit, String specialization, SetTrait associatedTrait) {
		 return merit.set(TraitChangeStatus.PENDING, specialization).and(associatedTrait);
    }
    
    @Override
	public SetTrait flaw(Flaw flaw, String specialization, SetTrait associatedTrait) {
    	return flaw.set(TraitChangeStatus.PENDING, specialization).and(associatedTrait);
    }

	@Override
	public SetTrait technique(Technique technique) {
		return technique.set(TraitChangeStatus.PENDING);
	}

	@Override
	public SetTrait elderPower(ElderPower power) {
		return power.set(TraitChangeStatus.PENDING);
	}

	@Override
	public SetTrait inClanPower(Power<?> power) {
		return power.set(TraitChangeStatus.PENDING);
	}
	
	@Override
	public SetTrait status(Status trait, String specialization) {
		return trait.set(TraitChangeStatus.PENDING, specialization);
	}
}
