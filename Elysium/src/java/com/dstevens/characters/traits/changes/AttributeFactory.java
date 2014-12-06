package com.dstevens.characters.traits.changes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.attributes.AttributeFocus;
import com.dstevens.characters.attributes.MentalAttributeFocus;
import com.dstevens.characters.attributes.PhysicalAttributeFocus;
import com.dstevens.characters.attributes.SocialAttributeFocus;

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
        public PlayerCharacter removeFocusFrom(int attributeFocusOrdinal, PlayerCharacter character) {
        	return character.withoutPhysicalAttributeFocus(PhysicalAttributeFocus.values()[attributeFocusOrdinal]);
        }

        @Override
        public AttributeFocus focusFor(int attributeFocusOrdinal) {
            return PhysicalAttributeFocus.values()[attributeFocusOrdinal];
        }

        @Override
        public PlayerCharacter increase(PlayerCharacter character) {
            return character.withPhysicalAttribute(character.getPhysicalAttribute()+1);
        }
        
        @Override
        public PlayerCharacter decrease(PlayerCharacter character) {
        	return character.withPhysicalAttribute(character.getPhysicalAttribute()-1);
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
        public PlayerCharacter removeFocusFrom(int attributeFocusOrdinal, PlayerCharacter character) {
        	return character.withoutSocialAttributeFocus(SocialAttributeFocus.values()[attributeFocusOrdinal]);
        }

        @Override
        public AttributeFocus focusFor(int attributeFocusOrdinal) {
            return SocialAttributeFocus.values()[attributeFocusOrdinal];
        }

        @Override
        public PlayerCharacter increase(PlayerCharacter character) {
            return character.withSocialAttribute(character.getSocialAttribute()+1);
        }
        
        @Override
        public PlayerCharacter decrease(PlayerCharacter character) {
        	return character.withSocialAttribute(character.getSocialAttribute()-1);
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
        public PlayerCharacter removeFocusFrom(int attributeFocusOrdinal, PlayerCharacter character) {
        	return character.withoutMentalAttributeFocus(MentalAttributeFocus.values()[attributeFocusOrdinal]);
        }

        @Override
        public AttributeFocus focusFor(int attributeFocusOrdinal) {
            return MentalAttributeFocus.values()[attributeFocusOrdinal];
        }

        @Override
        public PlayerCharacter increase(PlayerCharacter character) {
            return character.withMentalAttribute(character.getMentalAttribute()+1);
        }
        
        @Override
        public PlayerCharacter decrease(PlayerCharacter character) {
        	return character.withMentalAttribute(character.getMentalAttribute()-1);
        }
    };

    public abstract PlayerCharacter applyTo(int rating, PlayerCharacter character);

    public abstract String attributeName();

    public abstract PlayerCharacter applyFocusTo(int attributeFocusOrdinal, PlayerCharacter character);
    public abstract PlayerCharacter removeFocusFrom(int attributeFocusOrdinal, PlayerCharacter character);

    public abstract AttributeFocus focusFor(int attributeFocusOrdinal);

    public abstract PlayerCharacter increase(PlayerCharacter character);
    
    public abstract PlayerCharacter decrease(PlayerCharacter character);
    
}
