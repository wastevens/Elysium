package com.dstevens.characters.changes;

import java.util.Set;
import javax.persistence.*;

import com.dstevens.characters.traits.CharacterDefinedTrait;

@Entity
@DiscriminatorValue("CharactedDefinedTrait")
public abstract class SetCharacterDefinedTrait extends SetRatedTrait {

    @Column(name="specialization")
    private final String specialization;
    
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="TraitChangeFocuses")
    @Column(name="focus")
    private final Set<String> focuses;
    
    public SetCharacterDefinedTrait(String id, TraitChangeStatus status, CharacterDefinedTrait trait) {
        this(id, status, trait.ordinal(), trait.getRating(), trait.getSpecialization(), trait.getFocuses());
    }
    
    public SetCharacterDefinedTrait(String id, TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
        super(id, status, ordinal, rating);
        this.specialization = specialization;
        this.focuses = focuses;
    }

    protected final String specialization() {
        return specialization;
    }

    protected final Set<String> focuses() {
        return focuses;
    }
    
    

}
