package com.dstevens.game;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.attributes.AttributeFocus;
import com.dstevens.characters.backgrounds.Background;
import com.dstevens.characters.changes.AttributeFactory;
import com.dstevens.characters.distinctions.Flaw;
import com.dstevens.characters.distinctions.Merit;
import com.dstevens.characters.powers.ElderPower;
import com.dstevens.characters.powers.Power;
import com.dstevens.characters.powers.Technique;
import com.dstevens.characters.powers.magics.Ritual;
import com.dstevens.characters.skills.Skill;
import com.dstevens.game.traitchangebuilders.AttributeChangeBuilder;
import com.dstevens.game.traitchangebuilders.AttributeFocusBuilder;
import com.dstevens.game.traitchangebuilders.BackgroundTraitChangeBuilder;
import com.dstevens.game.traitchangebuilders.ElderPowerChangeBuilder;
import com.dstevens.game.traitchangebuilders.FlawTraitChangeBuilder;
import com.dstevens.game.traitchangebuilders.InClanDisciplineChangeBuilder;
import com.dstevens.game.traitchangebuilders.MeritTraitChangeBuilder;
import com.dstevens.game.traitchangebuilders.PowerChangeBuilder;
import com.dstevens.game.traitchangebuilders.RitualChangeBuilder;
import com.dstevens.game.traitchangebuilders.SkillTraitChangeBuilder;
import com.dstevens.game.traitchangebuilders.TechniqueChangeBuilder;

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
    	return new AttributeChangeBuilder(AttributeFactory.PHYSICAL);
    }
    
    public TraitChangeBuilder socialAttribute() {
    	return new AttributeChangeBuilder(AttributeFactory.SOCIAL);
    }
    
    public TraitChangeBuilder mentalAttribute() {
    	return new AttributeChangeBuilder(AttributeFactory.MENTAL);
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
    
}
