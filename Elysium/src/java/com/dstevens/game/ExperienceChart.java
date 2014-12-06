package com.dstevens.game;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.attributes.AttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.changes.AttributeFactory;
import com.dstevens.characters.traits.changes.builders.AttributeChangeBuilder;
import com.dstevens.characters.traits.changes.builders.AttributeFocusBuilder;
import com.dstevens.characters.traits.changes.builders.BackgroundTraitChangeBuilder;
import com.dstevens.characters.traits.changes.builders.ElderPowerChangeBuilder;
import com.dstevens.characters.traits.changes.builders.FlawTraitChangeBuilder;
import com.dstevens.characters.traits.changes.builders.InClanDisciplineChangeBuilder;
import com.dstevens.characters.traits.changes.builders.MeritTraitChangeBuilder;
import com.dstevens.characters.traits.changes.builders.PowerChangeBuilder;
import com.dstevens.characters.traits.changes.builders.RitualChangeBuilder;
import com.dstevens.characters.traits.changes.builders.SkillTraitChangeBuilder;
import com.dstevens.characters.traits.changes.builders.TechniqueChangeBuilder;
import com.dstevens.characters.traits.distinctions.Flaw;
import com.dstevens.characters.traits.distinctions.Merit;
import com.dstevens.characters.traits.powers.ElderPower;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.Ritual;
import com.dstevens.characters.traits.powers.Technique;
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
    	return new AttributeFocusBuilder(focus);
    }

    public SkillTraitChangeBuilder skill(Skill skill) {
        return new SkillTraitChangeBuilder(character, skill);
    }

	public BackgroundTraitChangeBuilder background(Background background) {
		return new BackgroundTraitChangeBuilder(character, background);
	}

	public PowerChangeBuilder power(Power<?> power) {
		return new PowerChangeBuilder(character, power);
	}

	public RitualChangeBuilder ritual(Ritual<?> ritual) {
		return new RitualChangeBuilder(ritual);
	}

	public TechniqueChangeBuilder technique(Technique technique) {
		return new TechniqueChangeBuilder(character, technique);
	}

	public ElderPowerChangeBuilder elderPower(ElderPower power) {
		return new ElderPowerChangeBuilder(character, power);
	}

	public InClanDisciplineChangeBuilder inClanPower(Power<?> power) {
		return new InClanDisciplineChangeBuilder(power);
	}

	public AttributeChangeBuilder attribute(AttributeFactory attributeFactory) {
		return new AttributeChangeBuilder(attributeFactory);
	}
    
}
