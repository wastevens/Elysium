package com.dstevens.characters.traits;

import com.dstevens.characters.PlayerCharacter;

public enum TraitChangeStatus {

    PENDING() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, TraitChangeEvent event, TraitChangeFactory traitChangeFactory) {
            event.setStatus(TraitChangeStatus.APPLIED);
            return event.apply(character, traitChangeFactory);
        }
        
        @Override
        public PlayerCharacter deny(PlayerCharacter character, TraitChangeEvent event) {
            event.setStatus(TraitChangeStatus.DENIED);
            return character;
        }
    },
    DENIED() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, TraitChangeEvent event, TraitChangeFactory traitChangeFactory) {
            return character;
        }

        @Override
        public PlayerCharacter deny(PlayerCharacter character, TraitChangeEvent traitChangeEvent) {
            return character;
        }
    },
    APPLIED() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, TraitChangeEvent event, TraitChangeFactory traitChangeFactory) {
            return character;
        }

        @Override
        public PlayerCharacter deny(PlayerCharacter character, TraitChangeEvent traitChangeEvent) {
            return character;
        }
    };
    
    public abstract PlayerCharacter apply(PlayerCharacter character, TraitChangeEvent event, TraitChangeFactory traitChangeFactory);

    public abstract PlayerCharacter deny(PlayerCharacter character, TraitChangeEvent traitChangeEvent);
    
}
