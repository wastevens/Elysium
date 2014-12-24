package com.dstevens.characters.traits.changes;

import com.dstevens.characters.PlayerCharacter;

public enum TraitChangeStatus {

    PENDING() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, TraitChange event) {
            return character;
        }
        
        @Override
        public PlayerCharacter deny(PlayerCharacter character, TraitChange event) {
            return character;
        }
    },
    DENIED() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, TraitChange event) {
            return character;
        }

        @Override
        public PlayerCharacter deny(PlayerCharacter character, TraitChange traitChangeEvent) {
            return character;
        }
    },
    APPLIED() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, TraitChange event) {
            return character;
        }

        @Override
        public PlayerCharacter deny(PlayerCharacter character, TraitChange traitChangeEvent) {
            return character;
        }
    };
    
    public abstract PlayerCharacter apply(PlayerCharacter character, TraitChange event);

    public abstract PlayerCharacter deny(PlayerCharacter character, TraitChange traitChangeEvent);
    
}
