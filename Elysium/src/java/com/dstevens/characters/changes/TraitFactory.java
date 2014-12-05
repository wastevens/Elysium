package com.dstevens.characters.changes;

import java.util.Set;

import com.dstevens.characters.powers.Discipline;
import com.dstevens.characters.powers.Technique;
import com.dstevens.characters.powers.magics.Necromancy;
import com.dstevens.characters.powers.magics.NecromanticRitual;
import com.dstevens.characters.powers.magics.ThaumaturgicalRitual;
import com.dstevens.characters.powers.magics.Thaumaturgy;
import com.dstevens.characters.traits.CharacterDefinedTrait;
import com.dstevens.characters.traits.EnumeratedTrait;
import com.dstevens.characters.traits.RatedTrait;

public enum TraitFactory {
    THAUMATURGICAL_RITUAL {
        @Override
        public EnumeratedTrait<?> traitFor(int ordinal) {
            return ThaumaturgicalRitual.values()[ordinal];
        }

        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) ThaumaturgicalRitual.values()[ordinal];
        }
    },
    NECROMANTIC_RITUAL {
        @Override
        public EnumeratedTrait<?> traitFor(int ordinal) {
            return NecromanticRitual.values()[ordinal];
        }

        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) NecromanticRitual.values()[ordinal];
        }
    },
    TECHNIQUE {
        @Override
        public EnumeratedTrait<?> traitFor(int ordinal) {
            return Technique.values()[ordinal];
        }

        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) Technique.values()[ordinal];
        }
    },
    IN_CLAN_DISCIPLINE {
        @Override
        public EnumeratedTrait<?> traitFor(int ordinal) {
            return Discipline.values()[ordinal];
        }

        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) Discipline.values()[ordinal];
        }
    },
    IN_CLAN_THAUMATURGY {
        @Override
        public EnumeratedTrait<?> traitFor(int ordinal) {
            return Thaumaturgy.values()[ordinal];
        }

        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) Thaumaturgy.values()[ordinal];
        }
    },
    IN_CLAN_NECROMANCY {
        @Override
        public EnumeratedTrait<?> traitFor(int ordinal) {
            return Necromancy.values()[ordinal];
        }

        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) Necromancy.values()[ordinal];
        }
    };
    
    public EnumeratedTrait<?> traitFor(int ordinal) {
        throw new IllegalStateException("Enumerated trait factory is undefined for " + this.getClass().getName() + " ordinal " + ordinal);
    }
    
    public RatedTrait<?> traitFor(int ordinal, int rating) {
        throw new IllegalStateException("Rated trait factory is undefined");
    }
    
    public CharacterDefinedTrait<?> traitFor(int ordinal, int rating, String specialization, Set<String> focuses) {
        throw new IllegalStateException("Character defined factory is undefined");
    }
    
    public abstract <E extends Enum<?>> E trait(int ordinal);
    
}
