package com.dstevens.characters.traits;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.backgrounds.*;
import com.dstevens.suppliers.IdSupplier;

@Entity
@DiscriminatorValue("Background")
public class SetBackgroundEvent extends TraitChangeEvent {

    //Hibernate only
    @Deprecated
    private SetBackgroundEvent() {
        super(null, null, null, 0, 0, null, null);
    }
    
    public SetBackgroundEvent(String id, TraitChangeStatus status, CharacterBackground background) {
        super(id, background.getCharacterId(), status, background.getBackground().ordinal(), background.getRating(), background.getSpecialization(), background.getFocuses());
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        CharacterBackgroundFactory factory = new CharacterBackgroundFactory(new IdSupplier());
        return character.withBackground(factory.backgroundFor(character, Background.values()[getOrdinal()], getRating(), getSpecialization(), getFocuses()));
    }
    
}
