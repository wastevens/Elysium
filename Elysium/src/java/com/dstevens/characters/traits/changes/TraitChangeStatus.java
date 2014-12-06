package com.dstevens.characters.traits.changes;

import com.dstevens.characters.PlayerCharacter;

public enum TraitChangeStatus {

    PENDING() {
        @Override
        public PlayerCharacter apply(PlayerCharacter character, SetTrait event) {
        	SetTrait currentSetTrait = event;
        	while(currentSetTrait != null) {
        		currentSetTrait.setStatus(APPLIED);
        		character = currentSetTrait.apply(character);
        		currentSetTrait = currentSetTrait.associatedTrait();
        	}
            return character;
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
