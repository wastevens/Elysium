package com.dstevens.characters.traits.changes;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.AttributeValue;
import com.dstevens.characters.traits.attributes.focuses.AttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.CharacterBackground;
import com.dstevens.characters.traits.distinctions.flaws.Flaw;
import com.dstevens.characters.traits.distinctions.merits.Merit;
import com.dstevens.characters.traits.experience.ChangeExperience;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.disciplines.ElderPower;
import com.dstevens.characters.traits.powers.disciplines.Technique;
import com.dstevens.characters.traits.powers.magic.Ritual;
import com.dstevens.characters.traits.skills.CharacterSkill;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.status.CharacterStatus;
import com.dstevens.characters.traits.status.Status;

@Service
class ProvidedTraitChangeFactory implements TraitChangeFactory {
	
	private final TraitChangeStatusFactory traitChangeStatusFactory;

	@Autowired
	public ProvidedTraitChangeFactory(TraitChangeStatusFactory traitChangeStatusFactory) {
		this.traitChangeStatusFactory = traitChangeStatusFactory;
	}
	
	@Override
	public TraitChange<AttributeValue> attribute(Attribute attribute, int rating) {
		return attribute.set(rating).statusChanged(traitChangeStatusFactory.pending());
	}

	@Override
	public TraitChange<? extends AttributeFocus> focus(AttributeFocus focus) {
		return focus.set().statusChanged(traitChangeStatusFactory.pending());
	}
	
	@Override
	public TraitChange<CharacterSkill> skill(Skill skill, int rating, String specialization, Set<String> focuses) {
		return skill.set(rating, specialization, focuses).statusChanged(traitChangeStatusFactory.pending());
	}

	@Override
	public TraitChange<CharacterBackground> background(Background background, int rating, String specialization, Set<String> focuses) {
		return background.set(rating, specialization, focuses).statusChanged(traitChangeStatusFactory.pending());
	}
	
	@Override
	public TraitChange<? extends ApplicableTrait> power(Power<?> power, int rating) {
		return power.set(rating).statusChanged(traitChangeStatusFactory.pending());
	}

	@Override
	public TraitChange<? extends ApplicableTrait> ritual(Ritual<?> ritual) {
		return ritual.set().statusChanged(traitChangeStatusFactory.pending());
	}
	
	@Override
	public TraitChange<?> merit(Merit merit, String specialization, TraitChange<?> associatedTrait) {
		 return merit.set(specialization).and(associatedTrait).statusChanged(traitChangeStatusFactory.pending());
    }
    
    @Override
	public TraitChange<?> flaw(Flaw flaw, String specialization, TraitChange<?> associatedTrait) {
    	return flaw.set(specialization).and(associatedTrait).statusChanged(traitChangeStatusFactory.pending());
    }

	@Override
	public TraitChange<Technique> technique(Technique technique) {
		return technique.set().statusChanged(traitChangeStatusFactory.pending());
	}

	@Override
	public TraitChange<ElderPower> elderPower(ElderPower power) {
		return power.set().statusChanged(traitChangeStatusFactory.pending());
	}

	@Override
	public TraitChange<? extends ApplicableTrait> inClanPower(Power<?> power) {
		return power.set().statusChanged(traitChangeStatusFactory.pending());
	}
	
	@Override
	public TraitChange<CharacterStatus> status(Status trait, String specialization) {
		return trait.set(specialization).statusChanged(traitChangeStatusFactory.pending());
	}
}
