package com.dstevens.characters.changes;

import com.dstevens.characters.PlayerCharacter;

public enum TraitChangeStatus {

    PENDING() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, SetTrait event) {
            event.setStatus(TraitChangeStatus.APPLIED);
            PlayerCharacter characterWithChange = event.apply(character);
            if (event.hasAssociatedTrait()) {
                return event.associatedTrait().approve(characterWithChange);
            } else {
                return characterWithChange;
            }
        }
        
        @Override
        public PlayerCharacter deny(PlayerCharacter character, SetTrait event) {
            event.setStatus(TraitChangeStatus.DENIED);
            return character;
        }
    },
    DENIED() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, SetTrait event) {
            return character;
        }

        @Override
        public PlayerCharacter deny(PlayerCharacter character, SetTrait traitChangeEvent) {
            return character;
        }
    },
    APPLIED() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, SetTrait event) {
            return character;
        }

        @Override
        public PlayerCharacter deny(PlayerCharacter character, SetTrait traitChangeEvent) {
            return character;
        }
    };
    
    public abstract PlayerCharacter apply(PlayerCharacter character, SetTrait event);

    public abstract PlayerCharacter deny(PlayerCharacter character, SetTrait traitChangeEvent);
    
}
