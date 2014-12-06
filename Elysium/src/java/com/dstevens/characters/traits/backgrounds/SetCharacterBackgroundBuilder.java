package com.dstevens.characters.traits.backgrounds;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.SpendXp;
import com.dstevens.characters.traits.TraitChangeStatus;
import com.dstevens.game.TraitChangeBuilder;

public class SetCharacterBackgroundBuilder implements TraitChangeBuilder {

	private final PlayerCharacter character;
    private final Background background;
    private String specialization;
    private Set<String> focuses = set();
    private int rating = 0;

    public SetCharacterBackgroundBuilder(PlayerCharacter character, Background background) {
        this.character = character;
		this.background = background;
    }

    public SetCharacterBackgroundBuilder withSpecialization(String specialization) {
        this.specialization = specialization;
        return this;
    }
    
    public SetCharacterBackgroundBuilder withFocus(String focus) {
        this.focuses.add(focus);
        return this;
    }
    
    public SetCharacterBackgroundBuilder withRating(int rating) {
        this.rating = rating;
        return this;
    }
    
    @Override
    public SetTrait buy() {
    	if(character.getGeneration().orElse(1) == 1) {
    		return new SpendXp(TraitChangeStatus.PENDING, rating).and(setSkill());
    	} else {
    		return new SpendXp(TraitChangeStatus.PENDING, rating).and(setSkill());
    	}
    }

    @Override
    public SetTrait add() {
        return setSkill();
    }

    private SetBackground setSkill() {
        return new SetBackground(TraitChangeStatus.PENDING, new CharacterBackground(background, rating, specialization, focuses));
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
