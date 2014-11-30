package com.dstevens.game.traitchangebuilders;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.backgrounds.Background;
import com.dstevens.characters.backgrounds.CharacterBackground;
import com.dstevens.characters.changes.SetRatedTrait;
import com.dstevens.characters.changes.SetTrait;
import com.dstevens.characters.changes.SpendXp;
import com.dstevens.characters.changes.TraitChangeStatus;
import com.dstevens.characters.changes.TraitFactory;
import com.dstevens.characters.powers.Discipline;
import com.dstevens.game.TraitChangeBuilder;

public class DisciplineChangeBuilder implements TraitChangeBuilder {

	private final PlayerCharacter character;
    private final Discipline discipline;
    private int rating = 0;

    public DisciplineChangeBuilder(PlayerCharacter character, Discipline discipline) {
        this.character = character;
		this.discipline = discipline;
    }

    public DisciplineChangeBuilder withRating(int rating) {
        this.rating = rating;
        return this;
    }
    
    @Override
    public SetTrait buy() {
    	int cost = 0;
    	boolean inClan = character.getInClanDisciplines().contains(discipline);
    	if(inClan) {
    		cost = rating * 3;
    	} else {
    		Integer generation = 
        			character.getBackgrounds().
    		  		  		  stream().
    		  		  		  filter((CharacterBackground t) -> t.trait().equals(Background.GENERATION)).
    		  		  		  map((CharacterBackground t) -> t.rating()).findFirst().orElse(1);
        	if(generation == 5) {
        		cost = rating * 5;
        	} else {
        		cost = rating * 4;
        	}	
    	}
    	return new SpendXp(TraitChangeStatus.PENDING, cost, setDiscipline());
    }

    @Override
    public SetTrait add() {
        return setDiscipline();
    }

    private SetRatedTrait setDiscipline() {
        return new SetRatedTrait(TraitChangeStatus.PENDING, discipline, rating, TraitFactory.DISCIPLINE);
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
