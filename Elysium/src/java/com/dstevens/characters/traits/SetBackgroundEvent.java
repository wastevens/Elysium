package com.dstevens.characters.traits;

import java.util.Set;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.backgrounds.*;

@Entity
@DiscriminatorValue("Background")
public class SetBackgroundEvent extends SetCharacterDefinedTrait {
    
    //Hibernate only
    @Deprecated
    private SetBackgroundEvent() {
        super(null, null, null, 0, 0, null, null);
    }
    
    public SetBackgroundEvent(String id, TraitChangeStatus status, CharacterBackground background) {
        super(id, status, background);
    }
    
    public SetBackgroundEvent(String id, String characterId, TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
        super(id, characterId, status, ordinal, rating, specialization, focuses);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        CharacterBackgroundFactory factory = traitChangeFactory.characterBackgroundFactory();
        return character.withBackground(factory.backgroundFor(character, Background.values()[ordinal()], rating(), specialization(), focuses()));
    }
    
}
