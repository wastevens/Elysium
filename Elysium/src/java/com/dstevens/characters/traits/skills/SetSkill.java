package com.dstevens.characters.traits.skills;

import java.util.Set;

import com.dstevens.characters.traits.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Skill")
class SetSkill extends ApplicableTraitChange<CharacterSkill> {

	//Hibernate only
    @SuppressWarnings("unused")
	@Deprecated
    private SetSkill() {
        super();
    }
    
    public SetSkill(TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
    	super(status, ordinal, rating, specialization, focuses);
    }
    
    @Override
    protected CharacterSkill trait() {
    	return new CharacterSkill(Skill.values()[ordinal], rating, specialization, focuses);
    }
}
