package com.dstevens.characters.traits.changes;

import com.dstevens.characters.distinctions.*;

public enum DistinctionFactory {

    FLAW {
        @Override
        public Distinction<?> distinction(String type, int ordinal) {
            return FlawTranslator.ofTypeWithId(type, ordinal);
        }

        @Override
        public CharacterDistinction distinctionFor(String type, int ordinal, String details) {
            return new CharacterFlaw(FlawTranslator.ofTypeWithId(type, ordinal), details);
        }
        
    },
    MERIT {
        @Override
        public Distinction<?> distinction(String type, int ordinal) {
            return MeritTranslator.ofTypeWithId(type, ordinal);
        }

        @Override
        public CharacterDistinction distinctionFor(String type, int ordinal, String details) {
            return new CharacterMerit(MeritTranslator.ofTypeWithId(type, ordinal), details);
        }        
        
    };
    
    public abstract Distinction<?> distinction(String type, int ordinal);
    public abstract CharacterDistinction distinctionFor(String type, int ordinal, String details);
    
}
