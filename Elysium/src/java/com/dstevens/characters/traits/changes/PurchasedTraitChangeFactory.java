package com.dstevens.characters.traits.changes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.characters.traits.Traits;
import com.dstevens.characters.traits.distinctions.flaws.Flaw;
import com.dstevens.characters.traits.distinctions.merits.Merit;
import com.dstevens.characters.traits.powers.disciplines.Discipline;
import com.dstevens.characters.traits.powers.disciplines.ElderPower;
import com.dstevens.characters.traits.powers.magic.necromancy.NecromanticRitual;
import com.dstevens.characters.traits.powers.magic.thaumaturgy.ThaumaturgicalRitual;

class PurchasedTraitChangeFactory implements TraitChangeFactory {

    private final PlayerCharacter character;
    private final ProvidedTraitChangeFactory traitChangeFactory;
    
    PurchasedTraitChangeFactory(PlayerCharacter character, ProvidedTraitChangeFactory traitChangeFactory) {
        this.character = character;
		this.traitChangeFactory = traitChangeFactory;
    }


	@Override
	public TraitChange traitChange(Traits traitType, Enum<? extends Trait> trait, TraitQualities qualities) {
		return traitChangeFactory.traitChange(traitType, trait, qualities).costing(costFor(traitType, trait, qualities));
	}
    
	private int costFor(Traits traitType, Enum<? extends Trait> trait, TraitQualities qualities) {
		switch (traitType) {
		case ATTRIBUTE:             return 3;
		case PHYSICAL_FOCUS:        throw new IllegalStateException("Cannot purchase attribute focuses");
		case SOCIAL_FOCUS:          throw new IllegalStateException("Cannot purchase attribute focuses");
		case SKILLS:                return costForSkill(qualities.rating);
		case BACKGROUNDS:           return costForBackground(qualities.rating);
		case DISCIPLINE:            return costForPower((Discipline)trait, qualities.rating);
		case NECROMANTIC_RITUAL:    return ((NecromanticRitual)trait).rating() * 2;
		case THAUMATURGICAL_RITUAL: return ((ThaumaturgicalRitual)trait).rating() * 2;
		case MERIT:                 return costForMerit((Merit)trait);
		case FLAW:                  return costForFlaw((Flaw)trait);
		case TECHNIQUE:             return costForTechnique();
		case ELDER_POWER:           return costForElderPower((ElderPower)trait);
		default:
			throw new IllegalStateException("Cannot purchase " + traitType.name());
		}
	}

	private int costForSkill(int rating) {
		if(character.getGeneration().orElse(1) == 1) {
			return rating;
		} else {
			return rating * 2;
		}
	}
    
	private int costForBackground(int rating) {
		if(character.getGeneration().orElse(1) == 1) {
			return rating;
		} else {
			return rating * 2;
		}
	}
    
    private int costForPower(Discipline power, int rating) {
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
    
	private int costForMerit(Merit merit) {
		return merit.getPoints();
	}
	
	private int costForFlaw(Flaw flaw) {
		return -1 * flaw.getPoints();
	}

    private int costForTechnique() {
    	if(character.getGeneration().orElse(1) >= 3) {
    		return 20;
    	} else {
    		return 12;
    	}
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
}
