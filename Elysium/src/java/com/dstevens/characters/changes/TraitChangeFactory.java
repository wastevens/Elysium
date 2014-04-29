package com.dstevens.characters.changes;

import org.springframework.stereotype.Service;

import com.dstevens.characters.skills.CharacterSkillFactory;

@Service
public class TraitChangeFactory {
    
    public CharacterSkillFactory characterSkillFactory() {
        return new CharacterSkillFactory();
    }
}
