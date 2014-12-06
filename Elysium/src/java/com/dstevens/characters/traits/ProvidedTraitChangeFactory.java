package com.dstevens.characters.traits;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.dstevens.characters.traits.powers.SetElderPowerFactory;
import com.dstevens.characters.traits.powers.SetInClanPowerFactory;
import com.dstevens.characters.traits.powers.SetPowerFactory;
import com.dstevens.characters.traits.powers.SetRitualFactory;
import com.dstevens.characters.traits.powers.SetTechniqueFactory;
import com.dstevens.characters.traits.powers.Technique;
import com.dstevens.characters.traits.skills.SetSkillFactory;
import com.dstevens.characters.traits.skills.Skill;

@Service
class ProvidedTraitChangeFactory implements TraitChangeFactory {

	private final SetAttributeValueFactory attributeValueFactory;
	private final SetAttributeFocusFactory attributeFocusFactory;
	private final SetSkillFactory skillFactory;
	private final SetBackgroundFactory backgroundFactory;
	private final SetPowerFactory setPowerFactory;
	private final SetRitualFactory setRitualFactory;
	private final SetMeritFactory setMeritFactory;
	private final SetFlawFactory setFlawFactory;
	private final SetTechniqueFactory setTechniqueFactory;
	private final SetElderPowerFactory setElderPowerFactory;
	private final SetInClanPowerFactory setInClanPowerFactory;
	
	@Autowired
	public ProvidedTraitChangeFactory(SetAttributeValueFactory attributeValueFactory,
	                                  SetAttributeFocusFactory attributeFocusFactory,
	                                  SetSkillFactory skillFactory,                  
	                                  SetBackgroundFactory backgroundFactory,        
	                                  SetPowerFactory setPowerFactory,        
	                                  SetRitualFactory setRitualFactory,             
	                                  SetMeritFactory setMeritFactory,             
	                                  SetFlawFactory setFlawFactory,               
	                                  SetTechniqueFactory setTechniqueFactory,       
	                                  SetElderPowerFactory setElderPowerFactory,     
	                                  SetInClanPowerFactory setInClanPowerFactory) {
		this.attributeValueFactory = new SetAttributeValueFactory();
		this.attributeFocusFactory = new SetAttributeFocusFactory();
		this.skillFactory = new SetSkillFactory();
		this.backgroundFactory = new SetBackgroundFactory();
		this.setPowerFactory = new SetPowerFactory();
		this.setRitualFactory = new SetRitualFactory();
		this.setMeritFactory = new SetMeritFactory();
		this.setFlawFactory = new SetFlawFactory();
		this.setTechniqueFactory = new SetTechniqueFactory();
		this.setElderPowerFactory = new SetElderPowerFactory();
		this.setInClanPowerFactory = new SetInClanPowerFactory();
	}
	
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
	public SetTrait elderPower(ElderPower power) {
		return setElderPowerFactory.elderPower(power);
	}

	@Override
	public SetTrait inClanPower(Power<?> power) {
		return setInClanPowerFactory.add(power);
	}
}
