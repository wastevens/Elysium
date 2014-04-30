package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.distinctions.*;

@Entity
@DiscriminatorValue("Flaw")
class SetFlaw extends SetTrait {

    @Column(name="ordinal")
    private final int ordinal;
    
    @Column(name="typeIdentifier")
    private final String typeIdentifier;
    
    @Column(name="specialization")
    private final String details;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetFlaw() {
        this(null, null, 0, null, null);
    }
    
    protected SetFlaw(String id, TraitChangeStatus status, CharacterFlaw flaw) {
        this(id, status, flaw.getDistinction().ordinal(), flaw.getDistinction().getType(), flaw.getDetails());
    }
    
    protected SetFlaw(String id, TraitChangeStatus status, Flaw<?> flaw, String details) {
        this(id, status, flaw.ordinal(), flaw.getType(), details);
    }
    
    protected SetFlaw(String id, TraitChangeStatus status, int ordinal, String typeIdentifier, String details) {
        super(status);
        this.ordinal = ordinal;
        this.typeIdentifier = typeIdentifier;
        this.details = details;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return character.withFlaw(new CharacterFlaw(trait(), details));
    }
    
    @Override
    public String describe() {
        String specialization = (isPresent(details) ? String.format(" (%1$s)", details) : "");
        String nextTrait = (hasAssociatedTrait() ? String.format(" with %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Set %2$s%3$s%4$s", status(), trait(), specialization, nextTrait);
    }

    private boolean isPresent(String specialization) {
        return specialization != null && !specialization.isEmpty();
    }

    private Flaw<?> trait() {
        return FlawTranslator.ofTypeWithId(typeIdentifier, ordinal);
    }

}
