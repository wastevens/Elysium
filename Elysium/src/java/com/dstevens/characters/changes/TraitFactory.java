package com.dstevens.characters.changes;

import java.util.Set;

import com.dstevens.characters.backgrounds.Background;
import com.dstevens.characters.backgrounds.CharacterBackground;
import com.dstevens.characters.powers.CharacterDiscipline;
import com.dstevens.characters.powers.Discipline;
import com.dstevens.characters.powers.ElderPower;
import com.dstevens.characters.powers.Technique;
import com.dstevens.characters.powers.magics.CharacterNecromancy;
import com.dstevens.characters.powers.magics.CharacterThaumaturgy;
import com.dstevens.characters.powers.magics.Necromancy;
import com.dstevens.characters.powers.magics.NecromanticRitual;
import com.dstevens.characters.powers.magics.ThaumaturgicalRitual;
import com.dstevens.characters.powers.magics.Thaumaturgy;
import com.dstevens.characters.skills.CharacterSkill;
import com.dstevens.characters.skills.Skill;
import com.dstevens.characters.traits.CharacterDefinedTrait;
import com.dstevens.characters.traits.EnumeratedTrait;
import com.dstevens.characters.traits.RatedTrait;

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
    }, 
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
    ELDER_POWER {
        @Override
        public EnumeratedTrait<?> traitFor(int ordinal) {
            return ElderPower.values()[ordinal];
        }

        @SuppressWarnings("unchecked")
        @Override
        public <E extends Enum<?>> E trait(int ordinal) {
            return (E) ElderPower.values()[ordinal];
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
