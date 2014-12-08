package com.dstevens.characters.traits;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.AttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.distinctions.flaws.Flaw;
import com.dstevens.characters.traits.distinctions.merits.Merit;
import com.dstevens.characters.traits.experience.ChangeExperience;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.disciplines.ElderPower;
import com.dstevens.characters.traits.powers.disciplines.Technique;
import com.dstevens.characters.traits.powers.magic.Ritual;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.status.Status;

class PurchasedTraitChangeFactory implements TraitChangeFactory {

    private final PlayerCharacter character;
    private final ProvidedTraitChangeFactory traitChangeFactory;
    
    PurchasedTraitChangeFactory(PlayerCharacter character, ProvidedTraitChangeFactory traitChangeFactory) {
        this.character = character;
		this.traitChangeFactory = traitChangeFactory;
    }

	@Override
	public SetTrait attribute(Attribute attribute, int rating) {
		return ChangeExperience.spend(3).and(traitChangeFactory.attribute(attribute, rating));
	}
    
    @Override
	public SetTrait focus(AttributeFocus focus) {
    	throw new IllegalStateException("Cannot purchase attribute focuses");
    }
    
    @Override
	public SetTrait skill(Skill skill, int rating, String specialization, Set<String> focuses) {
    	return costForSkill(rating).and(traitChangeFactory.skill(skill, rating, specialization, focuses));
    }
	
	private ChangeExperience costForSkill(int rating) {
		if(character.getGeneration().orElse(1) == 1) {
			return ChangeExperience.spend(rating);
		} else {
			return ChangeExperience.spend(rating * 2);
		}
	}
    
	@Override
	public SetTrait background(Background background, int rating, String specialization, Set<String> focuses) {
		return costForBackground(rating).and(traitChangeFactory.background(background, rating, specialization, focuses));
	}
	
	private ChangeExperience costForBackground(int rating) {
		if(character.getGeneration().orElse(1) == 1) {
    		return ChangeExperience.spend(rating);
    	} else {
    		return ChangeExperience.spend(rating * 2);
    	}
	}
    
	@Override
	public SetTrait power(Power<?> power, int rating) {
		return costForPower(power, rating).and(traitChangeFactory.power(power, rating));
	}
	
    private SetTrait costForPower(Power<?> power, int rating) {
    	int cost = 0;
    	boolean inClan = character.getInClanDisciplines().contains(power);
    	if(inClan) {
    		cost = rating * 3;
    	} else {
    		if(character.getGeneration().orElse(1) == 5) {
        		cost = rating * 5;
        	} else {
        		cost = rating * 4;
        	}	
    	}
    	return ChangeExperience.spend(cost);
    }

	@Override
	public SetTrait ritual(Ritual<?> ritual) {
		return ChangeExperience.spend(ritual.rating() * 2).and(traitChangeFactory.ritual(ritual));
	}
    
    @Override
	public SetTrait merit(Merit merit, String specialization, SetTrait associatedTrait) {
    	return ChangeExperience.spend(merit.getPoints()).and(traitChangeFactory.merit(merit, specialization, associatedTrait));
    }
    
    @Override
	public SetTrait flaw(Flaw flaw, String specialization, SetTrait associatedTrait) {
    	return ChangeExperience.gain(flaw.getPoints()).and(traitChangeFactory.flaw(flaw, specialization, associatedTrait));
    }

	@Override
	public SetTrait technique(Technique technique) {
		return costForTechnique().and(traitChangeFactory.technique(technique));
	}
	
    private SetTrait costForTechnique() {
    	if(character.getGeneration().orElse(1) >= 3) {
    		return ChangeExperience.spend(20);
    	} else {
    		return ChangeExperience.spend(12);
    	}
    }

	@Override
	public SetTrait elderPower(ElderPower power) {
		return costForElderPower(power).and(traitChangeFactory.elderPower(power));
	}

    private SetTrait costForElderPower(ElderPower power) {
    	if(character.getInClanDisciplines().contains(power.getPower())) {
    		return ChangeExperience.spend(18);
    	} else {
    		if(character.getGeneration().orElse(1) == 5) {
    			return ChangeExperience.spend(30);
    		} else {
    			return ChangeExperience.spend(24);
    		}
    	}
    }
	
	@Override
	public SetTrait inClanPower(Power<?> power) {
		throw new IllegalStateException("Cannot buy additional in clan powers");
	}

	@Override
	public SetTrait status(Status awesome, String string) {
		throw new IllegalStateException("Cannot buy status");
	}
}
