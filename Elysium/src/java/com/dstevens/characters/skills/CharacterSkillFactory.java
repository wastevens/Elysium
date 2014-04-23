package com.dstevens.characters.skills;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.suppliers.IdSupplier;

@Service
public class CharacterSkillFactory {

    private static final String NO_SPECIALIZATION = null;
    private static final Set<String> NO_FOCUSES = set();
    
    private final IdSupplier idSupplier;

    @Autowired
    public CharacterSkillFactory(IdSupplier idSupplier) {
        this.idSupplier = idSupplier;
    }
    
    public CharacterSkill skillFor(PlayerCharacter character, Skill skill, int rating) {
        return new CharacterSkill(idSupplier.get(), character.getId(), skill, rating, NO_SPECIALIZATION, NO_FOCUSES);
    }
    
    public CharacterSkill skillFor(PlayerCharacter character, Skill skill, int rating, String specialization) {
        return new CharacterSkill(idSupplier.get(), character.getId(), skill, rating, specialization, NO_FOCUSES);
    }
    
    public CharacterSkill skillFor(PlayerCharacter character, Skill skill, int rating, Set<String> focuses) {
        return new CharacterSkill(idSupplier.get(), character.getId(), skill, rating, NO_SPECIALIZATION, focuses);
    }
    
    public CharacterSkill skillFor(PlayerCharacter character, Skill skill, int rating,  String specialization, Set<String> focuses) {
        return new CharacterSkill(idSupplier.get(), character.getId(), skill, rating, specialization, focuses);
    }
    
}
