package com.dstevens.characters.changes;

import java.util.Set;

import com.dstevens.characters.backgrounds.*;
import com.dstevens.characters.powers.*;
import com.dstevens.characters.powers.magics.*;
import com.dstevens.characters.skills.*;
import com.dstevens.characters.traits.*;

public enum TraitFactory {

    BACKGROUND {
        @Override
        public CharacterDefinedTrait<?> traitFor(int ordinal, int rating, String specialization, Set<String> focuses) {
            return CharacterBackground.backgroundFor(trait(ordinal), rating, specialization, focuses);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) Background.values()[ordinal];
        }
    },
    SKILL {
        @Override
        public CharacterDefinedTrait<?> traitFor(int ordinal, int rating, String specialization, Set<String> focuses) {
            return CharacterSkill.skillFor(trait(ordinal), rating, specialization, focuses);
        }


        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) Skill.values()[ordinal];
        }
    }, 
    DISCIPLINE {
        @Override
        public RatedTrait<?> traitFor(int ordinal, int rating) {
            return new CharacterDiscipline(trait(ordinal), rating);
        }


        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) Discipline.values()[ordinal];
        }
    }, 
    THAUMATURGY {
        @Override
        public RatedTrait<?> traitFor(int ordinal, int rating) {
            return new CharacterThaumaturgy(trait(ordinal), rating);
        }


        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) Thaumaturgy.values()[ordinal];
        }
    }, 
    NECROMANCY {
        @Override
        public RatedTrait<?> traitFor(int ordinal, int rating) {
            return new CharacterNecromancy(trait(ordinal), rating);
        }


        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) Necromancy.values()[ordinal];
        }
    };
    
    public EnumeratedTrait<?> traitFor(int ordinal) {
        throw new IllegalStateException("Enumerated trait factory is undefined");
    }
    
    public RatedTrait<?> traitFor(int ordinal, int rating) {
        throw new IllegalStateException("Rated trait factory is undefined");
    }
    
    public CharacterDefinedTrait<?> traitFor(int ordinal, int rating, String specialization, Set<String> focuses) {
        throw new IllegalStateException("Character defined factory is undefined");
    }
    
    public abstract <E extends Enum<?>> E trait(int ordinal);
    
}
