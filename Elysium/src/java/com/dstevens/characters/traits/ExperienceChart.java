package com.dstevens.characters.traits;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.attributes.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.MentalAttributeValue;
import com.dstevens.characters.traits.attributes.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.PhysicalAttributeValue;
import com.dstevens.characters.traits.attributes.SetAttributeValueBuilder;
import com.dstevens.characters.traits.attributes.SetMentalFocusBuilder;
import com.dstevens.characters.traits.attributes.SetPhysicalFocusBuilder;
import com.dstevens.characters.traits.attributes.SetSocialFocusBuilder;
import com.dstevens.characters.traits.attributes.SocialAttributeFocus;
import com.dstevens.characters.traits.attributes.SocialAttributeValue;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.SetBackgroundBuilder;
import com.dstevens.characters.traits.distinctions.Flaw;
import com.dstevens.characters.traits.distinctions.Merit;
import com.dstevens.characters.traits.distinctions.SetFlawBuilder;
import com.dstevens.characters.traits.distinctions.SetMeritBuilder;
import com.dstevens.characters.traits.powers.ElderPower;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.Ritual;
import com.dstevens.characters.traits.powers.SetElderPowerBuilder;
import com.dstevens.characters.traits.powers.SetInClanPowerBuilder;
import com.dstevens.characters.traits.powers.SetPowerBuilder;
import com.dstevens.characters.traits.powers.SetRitualBuilder;
import com.dstevens.characters.traits.powers.SetTechniqueBuilder;
import com.dstevens.characters.traits.powers.Technique;
import com.dstevens.characters.traits.skills.SetSkillBuilder;
import com.dstevens.characters.traits.skills.Skill;

public class ExperienceChart {

    private PlayerCharacter character;

    private ExperienceChart(PlayerCharacter character) {
        this.character = character;
    }

    public static ExperienceChart chartFor(PlayerCharacter character) {
        return new ExperienceChart(character);
    }

    public SetMeritBuilder merit(Merit merit) {
        return new SetMeritBuilder(merit);
    }
    
    public SetMeritBuilder merit(Merit merit, String specialization) {
    	return new SetMeritBuilder(merit, specialization);
    }
    
    public SetMeritBuilder merit(Merit merit, String specialization, SetTrait associatedTrait) {
    	return new SetMeritBuilder(merit, specialization, associatedTrait);
    }
    
    public SetFlawBuilder flaw(Flaw flaw) {
    	return new SetFlawBuilder(flaw);
    }
    
    public SetFlawBuilder flaw(Flaw flaw, String specialization) {
    	return new SetFlawBuilder(flaw, specialization);
    }
    
    public SetFlawBuilder flaw(Flaw flaw, String specialization, SetTrait associatedTrait) {
    	return new SetFlawBuilder(flaw, specialization, associatedTrait);
    }

    public TraitChangeBuilder physicalAttribute() {
    	throw new IllegalStateException("NYI");
//    	return new AttributeChangeBuilder(AttributeFactory.PHYSICAL);
    }
    
    public TraitChangeBuilder socialAttribute() {
    	throw new IllegalStateException("NYI");
//    	return new AttributeChangeBuilder(AttributeFactory.SOCIAL);
    }
    
    public TraitChangeBuilder mentalAttribute() {
    	throw new IllegalStateException("NYI");
//    	return new AttributeChangeBuilder(AttributeFactory.MENTAL);
    }
      
    public TraitChangeBuilder physicalFocus(PhysicalAttributeFocus focus) {
    	return new SetPhysicalFocusBuilder(focus);
    }
    
    public TraitChangeBuilder mentalFocus(MentalAttributeFocus focus) {
    	return new SetMentalFocusBuilder(focus);
    }
    
    public TraitChangeBuilder socialFocus(SocialAttributeFocus focus) {
    	return new SetSocialFocusBuilder(focus);
    }

    public SetSkillBuilder skill(Skill skill) {
        return new SetSkillBuilder(character, skill);
    }

	public SetBackgroundBuilder background(Background background) {
		return new SetBackgroundBuilder(character, background);
	}

	public SetPowerBuilder power(Power<?> power) {
		return new SetPowerBuilder(character, power);
	}

	public SetRitualBuilder ritual(Ritual<?> ritual) {
		return new SetRitualBuilder(ritual);
	}

	public SetTechniqueBuilder technique(Technique technique) {
		return new SetTechniqueBuilder(character, technique);
	}

	public SetElderPowerBuilder elderPower(ElderPower power) {
		return new SetElderPowerBuilder(character, power);
	}

	public SetInClanPowerBuilder inClanPower(Power<?> power) {
		return new SetInClanPowerBuilder(power);
	}
	
	public SetAttributeValueBuilder physical(PlayerCharacter character) {
		return new SetAttributeValueBuilder(PhysicalAttributeValue.increase(character));
	}
	
	public SetAttributeValueBuilder social(PlayerCharacter character) {
		return new SetAttributeValueBuilder(SocialAttributeValue.increase(character));
	}
	
	public SetAttributeValueBuilder mental(PlayerCharacter character) {
		return new SetAttributeValueBuilder(MentalAttributeValue.increase(character));
	}
    
}
