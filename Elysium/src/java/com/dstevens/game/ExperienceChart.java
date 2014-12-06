package com.dstevens.game;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.attributes.AttributeFactory;
import com.dstevens.characters.traits.attributes.AttributeFocus;
import com.dstevens.characters.traits.attributes.SetAttributeBuilder;
import com.dstevens.characters.traits.attributes.SetAttributeFocusBuilder;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.SetCharacterBackgroundBuilder;
import com.dstevens.characters.traits.changes.builders.FlawTraitChangeBuilder;
import com.dstevens.characters.traits.changes.builders.MeritTraitChangeBuilder;
import com.dstevens.characters.traits.changes.builders.SkillTraitChangeBuilder;
import com.dstevens.characters.traits.distinctions.Flaw;
import com.dstevens.characters.traits.distinctions.Merit;
import com.dstevens.characters.traits.powers.ElderPower;
import com.dstevens.characters.traits.powers.SetElderPowerBuilder;
import com.dstevens.characters.traits.powers.SetInClanPowerBuilder;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.PowerChangeBuilder;
import com.dstevens.characters.traits.powers.Ritual;
import com.dstevens.characters.traits.powers.SetRitualBuilder;
import com.dstevens.characters.traits.powers.Technique;
import com.dstevens.characters.traits.powers.TechniqueChangeBuilder;
import com.dstevens.characters.traits.skills.Skill;

public class ExperienceChart {

    private PlayerCharacter character;

    private ExperienceChart(PlayerCharacter character) {
        this.character = character;
    }

    public static ExperienceChart chartFor(PlayerCharacter character) {
        return new ExperienceChart(character);
    }

    public MeritTraitChangeBuilder merit(Merit<?> merit) {
        return new MeritTraitChangeBuilder(merit);
    }
    
    public FlawTraitChangeBuilder flaw(Flaw<?> flaw) {
    	return new FlawTraitChangeBuilder(flaw);
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

    public SkillTraitChangeBuilder skill(Skill skill) {
        return new SkillTraitChangeBuilder(character, skill);
    }

	public SetCharacterBackgroundBuilder background(Background background) {
		return new SetCharacterBackgroundBuilder(character, background);
	}

	public PowerChangeBuilder power(Power<?> power) {
		return new PowerChangeBuilder(character, power);
	}

	public SetRitualBuilder ritual(Ritual<?> ritual) {
		return new SetRitualBuilder(ritual);
	}

	public TechniqueChangeBuilder technique(Technique technique) {
		return new TechniqueChangeBuilder(character, technique);
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
