package com.dstevens.characters.traits;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.skills.*;
import com.dstevens.suppliers.IdSupplier;

@Service
public class TraitChangeEventBuilderFactory {

    private final IdSupplier idSupplier;
    private final CharacterSkillFactory skillFactory;

    @Autowired
    public TraitChangeEventBuilderFactory(IdSupplier idSupplier, CharacterSkillFactory skillFactory) {
        this.idSupplier = idSupplier;
        this.skillFactory = skillFactory;
    }
    
    public TraitChangeEventBuilder change(PlayerCharacter character) {
        return new TraitChangeEventBuilder(idSupplier, character, skillFactory);
    }
    

    public static class TraitChangeEventBuilder {

        private IdSupplier idSupplier;
        private PlayerCharacter character;
        private CharacterSkillFactory skillFactory;

        public TraitChangeEventBuilder(IdSupplier idSupplier, PlayerCharacter character, CharacterSkillFactory skillFactory) {
            this.idSupplier = idSupplier;
            this.character = character;
            this.skillFactory = skillFactory;
        }
        
        public SetSkillEvent setSkill(Skill skill, int rating) {
            return new SetSkillEvent(idSupplier.get(), TraitChangeStatus.PENDING, skillFactory.skillFor(character, skill, rating));
        }
        
        public SetSkillEvent setSkill(Skill skill, int rating, String specialization) {
            return new SetSkillEvent(idSupplier.get(), TraitChangeStatus.PENDING, skillFactory.skillFor(character, skill, rating, specialization));
        }
        
        public SetSkillEvent setSkill(Skill skill, int rating, Set<String> focuses) {
            return new SetSkillEvent(idSupplier.get(), TraitChangeStatus.PENDING, skillFactory.skillFor(character, skill, rating, focuses));
        }

    }

}
