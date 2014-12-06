package com.dstevens.characters.traits;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.attributes.AttributeFactory;
import com.dstevens.characters.traits.attributes.AttributeFocus;
import com.dstevens.characters.traits.attributes.SetAttributeBuilder;
import com.dstevens.characters.traits.attributes.SetAttributeFocusBuilder;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.SetCharacterBackgroundBuilder;
import com.dstevens.characters.traits.distinctions.Flaw;
import com.dstevens.characters.traits.distinctions.SetFlawBuilder;
import com.dstevens.characters.traits.distinctions.Merit;
import com.dstevens.characters.traits.distinctions.SetMeritBuilder;
import com.dstevens.characters.traits.powers.ElderPower;
import com.dstevens.characters.traits.powers.SetElderPowerBuilder;
import com.dstevens.characters.traits.powers.SetInClanPowerBuilder;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.SetPowerBuilder;
import com.dstevens.characters.traits.powers.Ritual;
import com.dstevens.characters.traits.powers.SetRitualBuilder;
import com.dstevens.characters.traits.powers.Technique;
import com.dstevens.characters.traits.powers.SetTechniqueBuilder;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.skills.SetSkillChangeBuilder;

public class ExperienceChart {

    private PlayerCharacter character;

    private ExperienceChart(PlayerCharacter character) {
        this.character = character;
    }

    public static ExperienceChart chartFor(PlayerCharacter character) {
        return new ExperienceChart(character);
    }

    public SetMeritBuilder merit(Merit<?> merit) {
        return new SetMeritBuilder(merit);
    }
    
    public SetFlawBuilder flaw(Flaw<?> flaw) {
    	return new SetFlawBuilder(flaw);
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
      
    public TraitChangeBuilder attributeFocus(AttributeFocus focus) {
    	return new SetAttributeFocusBuilder(focus);
    }

    public SetSkillChangeBuilder skill(Skill skill) {
        return new SetSkillChangeBuilder(character, skill);
    }

	public SetCharacterBackgroundBuilder background(Background background) {
		return new SetCharacterBackgroundBuilder(character, background);
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

	public SetAttributeBuilder attribute(AttributeFactory attributeFactory) {
		return new SetAttributeBuilder(attributeFactory);
	}
    
}
