package com.dstevens.characters.changes;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.backgrounds.*;
import com.dstevens.characters.skills.*;
import com.dstevens.suppliers.IdSupplier;

@Service
public class TraitChangeEventBuilderFactory {

    private final IdSupplier idSupplier;
    private final CharacterSkillFactory skillFactory;
    private final CharacterBackgroundFactory backgroundFactory;

    @Autowired
    public TraitChangeEventBuilderFactory(IdSupplier idSupplier, CharacterSkillFactory skillFactory,
                                          CharacterBackgroundFactory backgroundFactory) {
        this.idSupplier = idSupplier;
        this.skillFactory = skillFactory;
        this.backgroundFactory = backgroundFactory;
    }
    
    public TraitChangeEventBuilder change(PlayerCharacter character) {
        return new TraitChangeEventBuilder(idSupplier, character, skillFactory, backgroundFactory);
    }

    public static class TraitChangeEventBuilder {

        private IdSupplier idSupplier;
        private PlayerCharacter character;
        private CharacterSkillFactory skillFactory;
        private CharacterBackgroundFactory backgroundFactory;

        public TraitChangeEventBuilder(IdSupplier idSupplier, PlayerCharacter character, CharacterSkillFactory skillFactory,
                                       CharacterBackgroundFactory backgroundFactory) {
            this.idSupplier = idSupplier;
            this.character = character;
            this.skillFactory = skillFactory;
            this.backgroundFactory = backgroundFactory;
        }
        
        public SetSkillEvent setSkill(Skill skill, int rating) {
            return setSkill(skill, rating, null, set());
        }
        
        public SetSkillEvent setSkill(Skill skill, int rating, String specialization) {
            return setSkill(skill, rating, specialization, set());
        }
        
        public SetSkillEvent setSkill(Skill skill, int rating, Set<String> focuses) {
            return setSkill(skill, rating, null, focuses);
        }
        
        private SetSkillEvent setSkill(Skill skill, int rating, String specialization, Set<String> focuses) {
            return new SetSkillEvent(idSupplier.get(), TraitChangeStatus.PENDING, 
                                     skillFactory.skillFor(character, skill, rating, specialization, focuses));
        }
        
        public SetBackgroundEvent setBackground(Background background, int rating) {
            return setBackground(background, rating, null, set());
        }
        
        public SetBackgroundEvent setBackground(Background background, int rating, String specialization) {
            return setBackground(background, rating, specialization, set());
        }
        
        public SetBackgroundEvent setBackground(Background background, int rating, Set<String> focuses) {
            return setBackground(background, rating, null, focuses);
        }
        
        public SetBackgroundEvent setBackground(Background background, int rating, String specialization, Set<String> focuses) {
            return new SetBackgroundEvent(idSupplier.get(), TraitChangeStatus.PENDING, backgroundFactory.backgroundFor(character, background, rating, specialization, focuses));
        }

        public TraitChangeEvent gainXp(int xp) {
            return changeXp(-1 * xp);
        }

        public TraitChangeEvent spendXp(int xp) {
            return changeXp(xp);
        }
        
        private TraitChangeEvent changeXp(int xp) {
            return new XpChangeEvent(idSupplier.get(), character.getId(), TraitChangeStatus.PENDING, xp);
        }

    }

}
