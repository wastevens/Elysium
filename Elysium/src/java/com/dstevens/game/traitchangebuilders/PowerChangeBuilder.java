package com.dstevens.game.traitchangebuilders;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.changes.SetDiscipline;
import com.dstevens.characters.traits.changes.SetNecromancy;
import com.dstevens.characters.traits.changes.SetThaumaturgy;
import com.dstevens.characters.traits.changes.SetTrait;
import com.dstevens.characters.traits.changes.SpendXp;
import com.dstevens.characters.traits.changes.TraitChangeStatus;
import com.dstevens.characters.traits.powers.CharacterDiscipline;
import com.dstevens.characters.traits.powers.CharacterNecromancy;
import com.dstevens.characters.traits.powers.CharacterThaumaturgy;
import com.dstevens.characters.traits.powers.Discipline;
import com.dstevens.characters.traits.powers.Necromancy;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.Thaumaturgy;
import com.dstevens.game.TraitChangeBuilder;

public class PowerChangeBuilder implements TraitChangeBuilder {

	private final PlayerCharacter character;
    private final Power<?> power;
    private int rating = 0;

    public PowerChangeBuilder(PlayerCharacter character, Power<?> power) {
        this.character = character;
		this.power = power;
    }

    public PowerChangeBuilder withRating(int rating) {
        this.rating = rating;
        return this;
    }
    
    @Override
    public SetTrait buy() {
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
    	return new SpendXp(TraitChangeStatus.PENDING, cost).and(setDiscipline());
    }

    @Override
    public SetTrait add() {
        return setDiscipline();
    }

    private SetTrait setDiscipline() {
    	if(power instanceof Discipline) {
    		return new SetDiscipline(TraitChangeStatus.PENDING, new CharacterDiscipline((Discipline) power, rating));
    	} else if(power instanceof Thaumaturgy) {
    		return new SetThaumaturgy(TraitChangeStatus.PENDING, new CharacterThaumaturgy((Thaumaturgy) power, rating));
    	} else if(power instanceof Necromancy) {
    		return new SetNecromancy(TraitChangeStatus.PENDING, new CharacterNecromancy((Necromancy) power, rating));
    	}
    	throw new IllegalArgumentException("Cannot find an implementing power type for " + power);
    }

	@Override
	public SetTrait sell() {
		throw new IllegalStateException("not yet implemented");
	}

	@Override
	public SetTrait remove() {
		throw new IllegalStateException("not yet implemented");
	}
    
}
