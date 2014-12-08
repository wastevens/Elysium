package com.dstevens.characters.traits;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.SetAttributeFocusFactory;
import com.dstevens.characters.traits.attributes.SetAttributeValueFactory;
import com.dstevens.characters.traits.attributes.SocialAttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.distinctions.Flaw;
import com.dstevens.characters.traits.distinctions.Merit;
import com.dstevens.characters.traits.powers.ElderPower;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.Ritual;
import com.dstevens.characters.traits.powers.Technique;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.status.SetStatus;
import com.dstevens.characters.traits.status.Status;

@Service
class ProvidedTraitChangeFactory implements TraitChangeFactory {

	private final SetAttributeValueFactory attributeValueFactory;
	private final SetAttributeFocusFactory attributeFocusFactory;
	
	@Autowired
	public ProvidedTraitChangeFactory(SetAttributeValueFactory attributeValueFactory,
	                                  SetAttributeFocusFactory attributeFocusFactory) {
		this.attributeValueFactory = attributeValueFactory;
		this.attributeFocusFactory = attributeFocusFactory;
	}
	
	@Override
	public SetTrait physical(PlayerCharacter character) {
		return attributeValueFactory.attributeValue(Attribute.PHYSICAL, character.getPhysicalAttribute()+1);
	}

	@Override
	public SetTrait social(PlayerCharacter character) {
		return attributeValueFactory.attributeValue(Attribute.SOCIAL, character.getSocialAttribute()+1);
	}

	@Override
	public SetTrait mental(PlayerCharacter character) {
		return attributeValueFactory.attributeValue(Attribute.MENTAL, character.getMentalAttribute()+1);
	}
	
	@Override
	public SetTrait physicalFocus(PhysicalAttributeFocus focus) {
		return attributeFocusFactory.setFocus(focus);
	}

	@Override
	public SetTrait mentalFocus(MentalAttributeFocus focus) {
		return attributeFocusFactory.setFocus(focus);
	}

	@Override
	public SetTrait socialFocus(SocialAttributeFocus focus) {
		return attributeFocusFactory.setFocus(focus);
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
		return new SetStatus(TraitChangeStatus.PENDING, trait.ordinal(), specialization);
	}
}
