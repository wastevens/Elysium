package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.distinctions.Distinction;

@Entity
@DiscriminatorValue("Distinction")
public class SetDistinction extends SetTrait {

    @Column(name="ordinal")
    private final int ordinal;
    
    @Column(name="typeIdentifier")
    private final String type;
    
    @Column(name="specialization")
    private final String details;

    @Column(name="factory")
    private DistinctionFactory factory;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetDistinction() {
        this(null, 0, null, null, null, null);
    }
    
    public SetDistinction(TraitChangeStatus status, Distinction<?> merit, DistinctionFactory factory) {
        this(status, merit.ordinal(), merit.getType(), null, null, factory);
    }
    
    public SetDistinction(TraitChangeStatus status, Distinction<?> merit, String details, DistinctionFactory factory) {
        this(status, merit.ordinal(), merit.getType(), details, null, factory);
    }
    
    public SetDistinction(TraitChangeStatus status, Distinction<?> merit, String details, SetTrait associatedTrait, DistinctionFactory factory) {
        this(status, merit.ordinal(), merit.getType(), details, associatedTrait, factory);
    }
    
    private SetDistinction(TraitChangeStatus status, int meritId, String meritType, String details, SetTrait associatedTrait, DistinctionFactory factory) {
        super(status, associatedTrait);
        this.ordinal = meritId;
        this.type = meritType;
        this.details = details;
        this.factory = factory;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return factory.distinctionFor(type, ordinal, details).applyTo(character);
    }
    
    @Override
    public String describe() {
        String specialization = (isPresent(details) ? String.format(" (%1$s)", details) : "");
        String nextTrait = (hasAssociatedTrait() ? String.format(" with %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Set %2$s%3$s%4$s", status(), factory.distinction(type, ordinal), specialization, nextTrait);
    }

    private boolean isPresent(String specialization) {
        return specialization != null && !specialization.isEmpty();
    }
}
