package com.dstevens.characters.traits.skills;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ChangeXp;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetSkillBuilder implements TraitChangeBuilder {

	private final PlayerCharacter character;
    private final Skill skill;
    private String specialization;
    private Set<String> focuses = set();
    private int rating = 0;

    public SetSkillBuilder(PlayerCharacter character, Skill skill) {
        this.character = character;
		this.skill = skill;
    }

    public SetSkillBuilder withSpecialization(String specialization) {
        this.specialization = specialization;
        return this;
    }
    
    public SetSkillBuilder withFocus(String focus) {
        this.focuses.add(focus);
        return this;
    }
    
    public SetSkillBuilder withRating(int rating) {
        this.rating = rating;
        return this;
    }
    
    @Override
    public SetTrait buy() {
    	if(character.getGeneration().orElse(1) == 1) {
    		return ChangeXp.spend(rating).and(setSkill());
    	} else {
    		return ChangeXp.spend(rating * 2).and(setSkill());
    	}
    }

    @Override
    public SetTrait add() {
        return setSkill();
    }

    private SetSkill setSkill() {
    	return new SetSkill(TraitChangeStatus.PENDING, CharacterSkill.skillFor(skill, rating, specialization, focuses));
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
