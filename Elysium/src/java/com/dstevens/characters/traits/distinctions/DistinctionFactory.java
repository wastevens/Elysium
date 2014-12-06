package com.dstevens.characters.traits.distinctions;


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
    };
    
    public abstract Distinction<?> distinction(String type, int ordinal);
    public abstract CharacterDistinction distinctionFor(String type, int ordinal, String details);
    
}
