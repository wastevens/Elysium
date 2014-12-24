package com.dstevens.characters.traits.changes;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.AttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.distinctions.flaws.Flaw;
import com.dstevens.characters.traits.distinctions.merits.Merit;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.disciplines.ElderPower;
import com.dstevens.characters.traits.powers.disciplines.Technique;
import com.dstevens.characters.traits.powers.magic.Ritual;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.status.Status;

@Service
class ProvidedTraitChangeFactory implements TraitChangeFactory {
	
	@Override
	public TraitChange attribute(Attribute attribute, int rating) {
		return attribute.set(rating);
	}

	@Override
	public TraitChange focus(AttributeFocus focus) {
		return focus.set();
	}
	
	@Override
	public TraitChange skill(Skill skill, int rating, String specialization, Set<String> focuses) {
		return skill.set(rating, specialization, focuses);
	}

	@Override
	public TraitChange background(Background background, int rating, String specialization, Set<String> focuses) {
		return background.set(rating, specialization, focuses);
	}
	
	@Override
	public TraitChange power(Power<?> power, int rating) {
		return power.set(rating);
	}

	@Override
	public TraitChange ritual(Ritual<?> ritual) {
		return ritual.set();
	}
	
	 @Override
	public TraitChange merit(Merit merit, String specialization, TraitChange associatedTrait) {
		 return merit.set(specialization).and(associatedTrait);
    }
    
    @Override
	public TraitChange flaw(Flaw flaw, String specialization, TraitChange associatedTrait) {
    	return flaw.set(specialization).and(associatedTrait);
    }

	@Override
	public TraitChange technique(Technique technique) {
		return technique.set();
	}

	@Override
	public TraitChange elderPower(ElderPower power) {
		return power.set();
	}

	@Override
	public TraitChange inClanPower(Power<?> power) {
		return power.set();
	}
	
	@Override
	public TraitChange status(Status trait, String specialization) {
		return trait.set(specialization);
	}
}
