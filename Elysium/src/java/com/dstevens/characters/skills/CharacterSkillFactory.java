package com.dstevens.characters.skills;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class CharacterSkillFactory {

    private static final String NO_SPECIALIZATION = null;
    private static final Set<String> NO_FOCUSES = set();
    
    public CharacterSkill skillFor(Skill skill, int rating) {
        return skillFor(skill, rating, NO_SPECIALIZATION, NO_FOCUSES);
    }
    
    public CharacterSkill skillFor(Skill skill, int rating, String specialization) {
        return skillFor(skill, rating, specialization, NO_FOCUSES);
    }
    
    public CharacterSkill skillFor(Skill skill, int rating, Set<String> focuses) {
        return skillFor(skill, rating, NO_SPECIALIZATION, focuses);
    }
    
    public CharacterSkill skillFor(Skill skill, int rating, String specialization,  Set<String> focuses) {
        return new CharacterSkill(skill, rating, specialization, focuses);
    }
    
}
