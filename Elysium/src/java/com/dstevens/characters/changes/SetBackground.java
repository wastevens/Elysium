package com.dstevens.characters.changes;

import java.util.Set;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.backgrounds.*;

@Entity
@DiscriminatorValue("Background")
@TraitType(type=Background.class)
class SetBackground extends SetCharacterDefinedTrait {
    
    //Hibernate only
    @Deprecated
    private SetBackground() {
        super(null, null, 0, 0, null, null);
    }
    
    protected SetBackground(String id, TraitChangeStatus status, CharacterBackground background) {
        super(id, status, background);
    }
    
    protected SetBackground(String id, TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
        super(id, status, ordinal, rating, specialization, focuses);
    }

    @Override
    public final PlayerCharacter apply(PlayerCharacter character) {
        return CharacterBackground.backgroundFor(trait(), rating(), specialization(), focuses()).applyTo(character);
    }
    
}
