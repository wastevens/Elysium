package com.dstevens.characters.traits.changes;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.AttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.distinctions.flaws.Flaw;
import com.dstevens.characters.traits.distinctions.merits.Merit;
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
	public TraitChange<?> attribute(Attribute attribute, int rating) {
		return traitChangeFactory.attribute(attribute, rating).costing(3);
	}
    
    @Override
	public TraitChange<?> focus(AttributeFocus focus) {
    	throw new IllegalStateException("Cannot purchase attribute focuses");
    }
    
    @Override
	public TraitChange<?> skill(Skill skill, int rating, String specialization, Set<String> focuses) {
    	return traitChangeFactory.skill(skill, rating, specialization, focuses).costing(costForSkill(rating));
    }
	
	private int costForSkill(int rating) {
		if(character.getGeneration().orElse(1) == 1) {
			return rating;
		} else {
			return rating * 2;
		}
	}
    
	@Override
	public TraitChange<?> background(Background background, int rating, String specialization, Set<String> focuses) {
		return traitChangeFactory.background(background, rating, specialization, focuses).costing(costForBackground(rating));
	}
	
	private int costForBackground(int rating) {
		if(character.getGeneration().orElse(1) == 1) {
			return rating;
		} else {
			return rating * 2;
		}
	}
    
	@Override
	public TraitChange<?> power(Power<?> power, int rating) {
		return traitChangeFactory.power(power, rating).costing(costForPower(power, rating));
	}
	
    private int costForPower(Power<?> power, int rating) {
    	boolean inClan = character.getInClanDisciplines().contains(power);
    	if(inClan) {
    		return rating * 3;
    	} else {
    		if(character.getGeneration().orElse(1) == 5) {
        		return rating * 5;
        	} else {
        		return rating * 4;
        	}	
    	}
    }

	@Override
	public TraitChange<?> ritual(Ritual<?> ritual) {
		return traitChangeFactory.ritual(ritual).costing(ritual.rating() * 2);
	}
    
    @Override
	public TraitChange<?> merit(Merit merit, String specialization, TraitChange<?> associatedTrait) {
    	return traitChangeFactory.merit(merit, specialization, associatedTrait).costing(merit.getPoints());
    }
    
    @Override
	public TraitChange<?> flaw(Flaw flaw, String specialization, TraitChange<?> associatedTrait) {
    	return traitChangeFactory.flaw(flaw, specialization, associatedTrait).costing(flaw.getPoints());
    }

	@Override
	public TraitChange<?> technique(Technique technique) {
		return traitChangeFactory.technique(technique).costing(costForTechnique());
	}
	
    private int costForTechnique() {
    	if(character.getGeneration().orElse(1) >= 3) {
    		return 20;
    	} else {
    		return 12;
    	}
    }

	@Override
	public TraitChange<?> elderPower(ElderPower power) {
		return traitChangeFactory.elderPower(power).costing(costForElderPower(power));
	}

    private int costForElderPower(ElderPower power) {
    	if(character.getInClanDisciplines().contains(power.getPower())) {
    		return 18;
    	} else {
    		if(character.getGeneration().orElse(1) == 5) {
    			return 30;
    		} else {
    			return 24;
    		}
    	}
    }
	
	@Override
	public TraitChange<?> inClanPower(Power<?> power) {
		throw new IllegalStateException("Cannot buy additional in clan powers");
	}

	@Override
	public TraitChange<?> status(Status awesome, String string) {
		throw new IllegalStateException("Cannot buy status");
	}
}
