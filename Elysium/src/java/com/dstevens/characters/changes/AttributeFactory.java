package com.dstevens.characters.changes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.attributes.*;

public enum AttributeFactory {

    PHYSICAL {
        @Override
        public PlayerCharacter applyTo(int rating, PlayerCharacter character) {
            return character.withPhysicalAttribute(rating);
        }

        @Override
        public String attributeName() {
            return "Physical";
        }

        @Override
        public PlayerCharacter applyFocusTo(int attributeFocusOrdinal, PlayerCharacter character) {
            return character.withPhysicalAttributeFocus(PhysicalAttributeFocus.values()[attributeFocusOrdinal]);
        }

        @Override
        public AttributeFocus focusFor(int attributeFocusOrdinal) {
            return PhysicalAttributeFocus.values()[attributeFocusOrdinal];
        }
    },
    SOCIAL {
        @Override
        public PlayerCharacter applyTo(int rating, PlayerCharacter character) {
            return character.withSocialAttribute(rating);
        }

        @Override
        public String attributeName() {
            return "Social";
        }

        @Override
        public PlayerCharacter applyFocusTo(int attributeFocusOrdinal, PlayerCharacter character) {
            return character.withSocialAttributeFocus(SocialAttributeFocus.values()[attributeFocusOrdinal]);
        }

        @Override
        public AttributeFocus focusFor(int attributeFocusOrdinal) {
            return SocialAttributeFocus.values()[attributeFocusOrdinal];
        }
    },
    MENTAL {
        @Override
        public PlayerCharacter applyTo(int rating, PlayerCharacter character) {
            return character.withMentalAttribute(rating);
        }
        
        @Override
        public String attributeName() {
            return "Mental";
        }

        @Override
        public PlayerCharacter applyFocusTo(int attributeFocusOrdinal, PlayerCharacter character) {
            return character.withMentalAttributeFocus(MentalAttributeFocus.values()[attributeFocusOrdinal]);
        }

        @Override
        public AttributeFocus focusFor(int attributeFocusOrdinal) {
            return MentalAttributeFocus.values()[attributeFocusOrdinal];
        }
    };

    public abstract PlayerCharacter applyTo(int rating, PlayerCharacter character);

    public abstract String attributeName();

    public abstract PlayerCharacter applyFocusTo(int attributeFocusOrdinal, PlayerCharacter character);

    public abstract AttributeFocus focusFor(int attributeFocusOrdinal);
    
}
