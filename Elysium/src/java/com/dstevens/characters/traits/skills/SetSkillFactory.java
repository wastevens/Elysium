package com.dstevens.characters.traits.skills;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetSkillFactory {
   
    public SetSkill setSkillFor(Skill skill, int rating, String specialization, Set<String> focuses) {
    	return new SetSkill(TraitChangeStatus.PENDING, new CharacterSkill(skill, rating, specialization, focuses));
    }
}
