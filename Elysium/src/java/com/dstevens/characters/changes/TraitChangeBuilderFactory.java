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
public class TraitChangeBuilderFactory {

    private final IdSupplier idSupplier;
    private final CharacterSkillFactory skillFactory;
    private final CharacterBackgroundFactory backgroundFactory;

    @Autowired
    public TraitChangeBuilderFactory(IdSupplier idSupplier, CharacterSkillFactory skillFactory,
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
        
        public abstract class SetCharacterDefinedTraitBuilder<Trait extends Enum<?>> {
            
            protected Trait trait = null;
            protected int rating = 0;
            protected String specialization = null;
            protected Set<String> focuses = set();

            public SetCharacterDefinedTraitBuilder(Trait trait, int rating) {
                this.trait = trait;
                this.rating = rating;
            }
            
            public SetCharacterDefinedTraitBuilder<Trait> withSpecialization(String specialization) {
                this.specialization = specialization;
                return this;
            }
            
            public SetCharacterDefinedTraitBuilder<Trait> withFocuses(Set<String> focuses) {
                this.focuses.addAll(focuses);
                return this;
            }
            
            public abstract TraitChange getEvent();
        }
        
        public class SetSkillBuilder extends SetCharacterDefinedTraitBuilder<Skill> {
            
            private SetSkillBuilder(Skill skill, int rating) {
                super(skill, rating);
            }
            
            public TraitChange getEvent() {
                return new SetSkill(idSupplier.get(), TraitChangeStatus.PENDING, 
                                    skillFactory.skillFor(character, trait, rating, specialization, focuses));
            }
        }
        
        
        public class SetBackgroundBuilder extends SetCharacterDefinedTraitBuilder<Background> {
            
            private SetBackgroundBuilder(Background background, int rating) {
                super(background, rating);
            }
            
            public TraitChange getEvent() {
                return new SetBackground(idSupplier.get(), TraitChangeStatus.PENDING, 
                                         backgroundFactory.backgroundFor(character, trait, rating, specialization, focuses));
            }
        }
        
        public SetSkillBuilder setSkill(Skill skill, int rating) {
            return new SetSkillBuilder(skill, rating);
        }
        
        public SetBackgroundBuilder setBackground(Background background, int rating) {
            return new SetBackgroundBuilder(background, rating);
        }

        public TraitChange gainXp(int xp) {
            return changeXp(-1 * xp);
        }

        public TraitChange spendXp(int xp) {
            return changeXp(xp);
        }
        
        private TraitChange changeXp(int xp) {
            return new SetXp(idSupplier.get(), TraitChangeStatus.PENDING, xp);
        }

    }

}
