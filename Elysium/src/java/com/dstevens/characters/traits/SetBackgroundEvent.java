package com.dstevens.characters.traits;

import java.util.Set;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.backgrounds.*;
import com.dstevens.suppliers.IdSupplier;

@Entity
@DiscriminatorValue("Background")
public class SetBackgroundEvent extends TraitChangeEvent {

    @Column(name="ordinal")
    private final int ordinal;
    
    @Column(name="rating")
    private final int rating;
    
    @Column(name="specialization")
    private final String specialization;
    
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="BackgroundChangeFocuses")
    @Column(name="focus")
    private final Set<String> focuses;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetBackgroundEvent() {
        this(null, null, null, 0, 0, null, null);
    }
    
    public SetBackgroundEvent(String id, TraitChangeStatus status, CharacterBackground background) {
        this(id, background.getCharacterId(), status, background.getBackground().ordinal(), background.getRating(), background.getSpecialization(), background.getFocuses());
    }
    
    public SetBackgroundEvent(String id, String characterId, TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
        super(id, characterId, status);
        this.ordinal = ordinal;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        CharacterBackgroundFactory factory = new CharacterBackgroundFactory(new IdSupplier());
        return character.withBackground(factory.backgroundFor(character, Background.values()[ordinal], rating, specialization, focuses));
    }
    
}
