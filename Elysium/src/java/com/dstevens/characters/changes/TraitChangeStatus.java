package com.dstevens.characters.changes;

import com.dstevens.characters.PlayerCharacter;

public enum TraitChangeStatus {

    PENDING() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, TraitChange event, TraitChangeFactory traitChangeFactory) {
            event.setStatus(TraitChangeStatus.APPLIED);
            return event.apply(character, traitChangeFactory);
        }
        
        @Override
        public PlayerCharacter deny(PlayerCharacter character, TraitChange event) {
            event.setStatus(TraitChangeStatus.DENIED);
            return character;
        }
    },
    DENIED() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, TraitChange event, TraitChangeFactory traitChangeFactory) {
            return character;
        }

        @Override
        public PlayerCharacter deny(PlayerCharacter character, TraitChange traitChangeEvent) {
            return character;
        }
    },
    APPLIED() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, TraitChange event, TraitChangeFactory traitChangeFactory) {
            return character;
        }

        @Override
        public PlayerCharacter deny(PlayerCharacter character, TraitChange traitChangeEvent) {
            return character;
        }
    };
    
    public abstract PlayerCharacter apply(PlayerCharacter character, TraitChange event, TraitChangeFactory traitChangeFactory);

    public abstract PlayerCharacter deny(PlayerCharacter character, TraitChange traitChangeEvent);
    
}
