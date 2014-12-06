package com.dstevens.characters.traits.powers;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.SpendXp;
import com.dstevens.characters.traits.TraitChangeStatus;
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
