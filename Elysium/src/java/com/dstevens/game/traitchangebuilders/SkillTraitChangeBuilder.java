package com.dstevens.game.traitchangebuilders;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.backgrounds.Background;
import com.dstevens.characters.backgrounds.CharacterBackground;
import com.dstevens.characters.changes.SetCharacterDefinedTrait;
import com.dstevens.characters.changes.SetTrait;
import com.dstevens.characters.changes.SpendXp;
import com.dstevens.characters.changes.TraitChangeStatus;
import com.dstevens.characters.changes.TraitFactory;
import com.dstevens.characters.skills.CharacterSkill;
import com.dstevens.characters.skills.Skill;
import com.dstevens.game.TraitChangeBuilder;

import static com.dstevens.collections.Sets.set;

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
    	Integer generation = 
    			character.getBackgrounds().
		  		  		  stream().
		  		  		  filter((CharacterBackground t) -> t.trait().equals(Background.GENERATION)).
		  		  		  findFirst().
		  		  		  map((CharacterBackground t) -> t.rating()).orElse(1);
    	if(generation == 1) {
    		return new SpendXp(TraitChangeStatus.PENDING, rating, setSkill());
    	} else {
    		return new SpendXp(TraitChangeStatus.PENDING, rating * 2, setSkill());
    	}
    }

    @Override
    public SetTrait add() {
        return setSkill();
    }

    private SetCharacterDefinedTrait setSkill() {
        return new SetCharacterDefinedTrait(TraitChangeStatus.PENDING, CharacterSkill.skillFor(skill, rating, specialization, focuses), TraitFactory.SKILL);
    }
    
}
