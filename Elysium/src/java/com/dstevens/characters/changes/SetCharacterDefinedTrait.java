package com.dstevens.characters.changes;

import java.util.Set;

import javax.persistence.*;

import com.dstevens.characters.traits.CharacterDefinedTrait;

@Entity
@DiscriminatorValue("CharactedDefinedTrait")
abstract class SetCharacterDefinedTrait extends SetRatedTrait {

    @Column(name="specialization")
    private final String specialization;
    
    @ElementCollection
    private final Set<String> focuses;
    
    protected SetCharacterDefinedTrait(String id, TraitChangeStatus status, CharacterDefinedTrait<?> trait) {
        this(id, status, trait.ordinal(), trait.rating(), trait.getSpecialization(), trait.getFocuses());
    }
    
    protected SetCharacterDefinedTrait(String id, TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
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
    
    @Override
    public String describe() {
        String displaySpecialization = (isPresent(specialization) ? String.format(" (%1$s)", specialization) : "");
        String displayFocuses = (!focuses.isEmpty() ? String.format(" %1$s", focuses()) : "");
        String nextTrait = (hasAssociatedTrait() ? String.format (" with %1$s", associatedTrait().describe()) : "");
        
        return String.format("(%1$s) Set %2$s%3$s to %4$s%5$s%6$s", status(), trait(), displaySpecialization, rating(), displayFocuses, nextTrait);
    }

    private boolean isPresent(String specialization) {
        return specialization != null && !specialization.isEmpty();
    }
    

}
