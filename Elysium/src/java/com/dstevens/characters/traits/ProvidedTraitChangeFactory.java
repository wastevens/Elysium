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
import com.dstevens.characters.traits.distinctions.SetFlawFactory;
import com.dstevens.characters.traits.distinctions.SetMeritFactory;
import com.dstevens.characters.traits.powers.Discipline;
import com.dstevens.characters.traits.powers.ElderPower;
import com.dstevens.characters.traits.powers.Necromancy;
import com.dstevens.characters.traits.powers.NecromanticRitual;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.Ritual;
import com.dstevens.characters.traits.powers.SetElderPowerFactory;
import com.dstevens.characters.traits.powers.SetInClanDisciplineFactory;
import com.dstevens.characters.traits.powers.SetInClanNecromancyFactory;
import com.dstevens.characters.traits.powers.SetInClanThaumaturgyFactory;
import com.dstevens.characters.traits.powers.SetNecromanticRitualFactory;
import com.dstevens.characters.traits.powers.SetPowerFactory;
import com.dstevens.characters.traits.powers.SetTechniqueFactory;
import com.dstevens.characters.traits.powers.SetThaumaturgicalRitualFactory;
import com.dstevens.characters.traits.powers.Technique;
import com.dstevens.characters.traits.powers.ThaumaturgicalRitual;
import com.dstevens.characters.traits.powers.Thaumaturgy;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.status.SetStatus;
import com.dstevens.characters.traits.status.Status;

@Service
class ProvidedTraitChangeFactory implements TraitChangeFactory {

	private final SetAttributeValueFactory attributeValueFactory;
	private final SetAttributeFocusFactory attributeFocusFactory;
	private final SetPowerFactory setPowerFactory;
	private final SetThaumaturgicalRitualFactory setThaumaturgicalRitualFactory;
	private final SetNecromanticRitualFactory setNecromanticRitualFactory;
	private final SetMeritFactory setMeritFactory;
	private final SetFlawFactory setFlawFactory;
	private final SetTechniqueFactory setTechniqueFactory;
	private final SetElderPowerFactory setElderPowerFactory;
	private final SetInClanDisciplineFactory setInClanDisciplineFactory;
	private final SetInClanThaumaturgyFactory setInClanThaumaturgyFactory;
	private final SetInClanNecromancyFactory setInClanNecromancyFactory;
	
	@Autowired
	public ProvidedTraitChangeFactory(SetAttributeValueFactory attributeValueFactory,
	                                  SetAttributeFocusFactory attributeFocusFactory,
	                                  SetPowerFactory setPowerFactory,        
	                                  SetThaumaturgicalRitualFactory setThaumaturgicalRitualFactory,
	                                  SetNecromanticRitualFactory setNecromanticRitualFactory,
	                                  SetMeritFactory setMeritFactory,             
	                                  SetFlawFactory setFlawFactory,               
	                                  SetTechniqueFactory setTechniqueFactory,       
	                                  SetElderPowerFactory setElderPowerFactory,     
	                                  SetInClanDisciplineFactory setInClanDisciplineFactory,
	                                  SetInClanThaumaturgyFactory setInClanThaumaturgyFactory,
	                                  SetInClanNecromancyFactory setInClanNecromancyFactory) {
		this.attributeValueFactory = attributeValueFactory;
		this.attributeFocusFactory = attributeFocusFactory;
		this.setPowerFactory = setPowerFactory;
		this.setThaumaturgicalRitualFactory = setThaumaturgicalRitualFactory;
		this.setNecromanticRitualFactory = setNecromanticRitualFactory;
		this.setMeritFactory = setMeritFactory;
		this.setFlawFactory = setFlawFactory;
		this.setTechniqueFactory = setTechniqueFactory;
		this.setElderPowerFactory = setElderPowerFactory;
		this.setInClanDisciplineFactory = setInClanDisciplineFactory;
		this.setInClanThaumaturgyFactory =setInClanThaumaturgyFactory;
		this.setInClanNecromancyFactory = setInClanNecromancyFactory;
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
		return setPowerFactory.setPower(power, rating);
	}

	@Override
	public SetTrait ritual(Ritual<?> ritual) {
		if(ritual instanceof ThaumaturgicalRitual) {
			return setThaumaturgicalRitualFactory.setRitual(ThaumaturgicalRitual.class.cast(ritual));
		}
		if(ritual instanceof NecromanticRitual) {
			return setNecromanticRitualFactory.setRitual(NecromanticRitual.class.cast(ritual));
		}
		throw new IllegalStateException("Could not find a ritual for " + ritual);
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
		if(power instanceof Discipline) {
			return setInClanDisciplineFactory.power(Discipline.class.cast(power));
		}
		if(power instanceof Thaumaturgy) {
			return setInClanThaumaturgyFactory.power(Thaumaturgy.class.cast(power));
		}
		if(power instanceof Necromancy) {
			return setInClanNecromancyFactory.power(Necromancy.class.cast(power));
		}
		throw new IllegalStateException("Could not find a power for " + power);
	}
	
	@Override
	public SetTrait status(Status trait, String specialization) {
		return new SetStatus(TraitChangeStatus.PENDING, trait.ordinal(), specialization);
	}
}
