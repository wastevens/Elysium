package com.dstevens.characters.changes;

import java.util.Set;

import com.dstevens.characters.traits.CharacterDefinedTrait;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CharactedDefinedTrait")
public abstract class SetCharacterDefinedTrait extends TraitChangeEvent {

    @Column(name="ordinal")
    private final int ordinal;
    
    @Column(name="rating")
    private final int rating;
    
    @Column(name="specialization")
    private final String specialization;
    
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="TraitChangeFocuses")
    @Column(name="focus")
    private final Set<String> focuses;
    
    public SetCharacterDefinedTrait(String id, TraitChangeStatus status, CharacterDefinedTrait trait) {
        this(id, trait.getCharacterId(), status, trait.ordinal(), trait.getRating(), trait.getSpecialization(), trait.getFocuses());
    }
    
    public SetCharacterDefinedTrait(String id, String characterId, TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
        super(id, characterId, status);
        this.ordinal = ordinal;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }

    protected final int ordinal() {
        return ordinal;
    }

    protected final int rating() {
        return rating;
    }

    protected final String specialization() {
        return specialization;
    }

    protected final Set<String> focuses() {
        return focuses;
    }
    
    

}
