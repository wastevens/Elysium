package com.dstevens.characters.traits.changes.builders;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.changes.SetSkill;
import com.dstevens.characters.traits.changes.SetTrait;
import com.dstevens.characters.traits.changes.SpendXp;
import com.dstevens.characters.traits.changes.TraitChangeStatus;
import com.dstevens.characters.traits.skills.CharacterSkill;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.game.TraitChangeBuilder;

public class SkillTraitChangeBuilder implements TraitChangeBuilder {

	private final PlayerCharacter character;
    private final Skill skill;
    private String specialization;
    private Set<String> focuses = set();
    private int rating = 0;

    public SkillTraitChangeBuilder(PlayerCharacter character, Skill skill) {
        this.character = character;
		this.skill = skill;
    }

    public SkillTraitChangeBuilder withSpecialization(String specialization) {
        this.specialization = specialization;
        return this;
    }
    
    public SkillTraitChangeBuilder withFocus(String focus) {
        this.focuses.add(focus);
        return this;
    }
    
    public SkillTraitChangeBuilder withRating(int rating) {
        this.rating = rating;
        return this;
    }
    
    @Override
    public SetTrait buy() {
    	if(character.getGeneration().orElse(1) == 1) {
    		return new SpendXp(TraitChangeStatus.PENDING, rating).and(setSkill());
    	} else {
    		return new SpendXp(TraitChangeStatus.PENDING, rating * 2).and(setSkill());
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
