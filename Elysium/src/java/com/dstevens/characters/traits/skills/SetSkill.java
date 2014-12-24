package com.dstevens.characters.traits.skills;

import java.util.Set;

import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Skill")
class SetSkill extends TraitChange<CharacterSkill> {

	//Hibernate only
    @SuppressWarnings("unused")
	@Deprecated
    private SetSkill() {
        super();
    }
    
    public SetSkill(int ordinal, int rating, String specialization, Set<String> focuses) {
    	super(ordinal, rating, specialization, focuses);
    }
    
    @Override
    protected CharacterSkill trait() {
    	return new CharacterSkill(Skill.values()[ordinal], rating, specialization, focuses);
    }
}
