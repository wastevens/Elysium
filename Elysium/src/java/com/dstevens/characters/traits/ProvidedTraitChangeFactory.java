package com.dstevens.characters.traits;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.attributes.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.MentalAttributeValue;
import com.dstevens.characters.traits.attributes.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.PhysicalAttributeValue;
import com.dstevens.characters.traits.attributes.SetAttributeFocusFactory;
import com.dstevens.characters.traits.attributes.SetAttributeValueFactory;
import com.dstevens.characters.traits.attributes.SocialAttributeFocus;
import com.dstevens.characters.traits.attributes.SocialAttributeValue;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.SetBackgroundFactory;
import com.dstevens.characters.traits.distinctions.Flaw;
import com.dstevens.characters.traits.distinctions.Merit;
import com.dstevens.characters.traits.distinctions.SetFlawFactory;
import com.dstevens.characters.traits.distinctions.SetMeritFactory;
import com.dstevens.characters.traits.powers.ElderPower;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.Ritual;
import com.dstevens.characters.traits.powers.SetElderPowerBuilder;
import com.dstevens.characters.traits.powers.SetInClanPowerBuilder;
import com.dstevens.characters.traits.powers.SetPowerFactory;
import com.dstevens.characters.traits.powers.SetRitualFactory;
import com.dstevens.characters.traits.powers.SetTechniqueFactory;
import com.dstevens.characters.traits.powers.Technique;
import com.dstevens.characters.traits.skills.SetSkillFactory;
import com.dstevens.characters.traits.skills.Skill;

@Service
public class ProvidedTraitChangeFactory implements TraitChangeFactory {

	private final SetAttributeValueFactory attributeValueFactory = new SetAttributeValueFactory();
	private final SetAttributeFocusFactory attributeFocusFactory = new SetAttributeFocusFactory();
	private final SetSkillFactory skillFactory = new SetSkillFactory();
	private final SetBackgroundFactory backgroundFactory = new SetBackgroundFactory();
	private final SetPowerFactory setPowerFactory = new SetPowerFactory();
	private final SetRitualFactory setRitualFactory = new SetRitualFactory();
	private final SetMeritFactory setMeritFactory = new SetMeritFactory();
	private final SetFlawFactory setFlawFactory = new SetFlawFactory();
	private final SetTechniqueFactory setTechniqueFactory = new SetTechniqueFactory();
	
	@Override
	public SetTrait physical(PlayerCharacter character) {
		return attributeValueFactory.attributeValue(PhysicalAttributeValue.increase(character));
	}

	@Override
	public SetTrait social(PlayerCharacter character) {
		return attributeValueFactory.attributeValue(SocialAttributeValue.increase(character));
	}

	@Override
	public SetTrait mental(PlayerCharacter character) {
		return attributeValueFactory.attributeValue(MentalAttributeValue.increase(character));
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
		return skillFactory.setSkillFor(skill, rating, specialization, focuses);
	}

	@Override
	public SetTrait background(Background background, int rating, String specialization, Set<String> focuses) {
		return backgroundFactory.setBackgroundFor(background, rating, specialization, focuses);
	}
	
	@Override
	public SetTrait power(Power<?> power, int rating) {
		return setPowerFactory.setPower(power, rating);
	}

	@Override
	public SetTrait ritual(Ritual<?> ritual) {
		return setRitualFactory.setRitual(ritual);
	}
	
	 @Override
	public SetTrait merit(Merit merit, String specialization, SetTrait associatedTrait) {
		 return setMeritFactory.merit(merit, specialization, associatedTrait);
    }
    
    @Override
	public SetTrait flaw(Flaw flaw, String specialization, SetTrait associatedTrait) {
    	return setFlawFactory.flaw(flaw, specialization, associatedTrait);
    }

	@Override
	public SetTrait technique(Technique technique) {
		return setTechniqueFactory.technique(technique);
	}

	@Override
	public SetElderPowerBuilder elderPower(ElderPower power) {
		return null;
//		return new SetElderPowerBuilder(character, power);
	}

	@Override
	public SetInClanPowerBuilder inClanPower(Power<?> power) {
		return new SetInClanPowerBuilder(power);
	}
}
