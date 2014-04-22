package com.dstevens.characters.merits;

import java.util.Comparator;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.utilities.ObjectExtensions;

@Embeddable
public class CharacterFlaw implements Comparable<CharacterFlaw> {

    @Column(name="flaw_id")
    int ordinal;
    
    @Column(name="details")
    private String details;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private CharacterFlaw() {
        this(0, null);
    }
    
    public CharacterFlaw(Enum<? extends Flaw> flaw) {
        this(flaw.ordinal(), null);
    }
    
    public CharacterFlaw(Enum<? extends Flaw> flaw, String details) {
        this(flaw.ordinal(), details);
    }
    
    private CharacterFlaw(int ordinal, String details) {
        this.ordinal = ordinal;
        this.details = details;
    }
    
    public Flaw getFlaw() {
        return GeneralFlaw.values()[ordinal];
    }
    
    public String getDetails() {
        return details;
    }
    
    @Override
    public boolean equals(Object that) {
        return ObjectExtensions.equals(this, that);
    }
    
    @Override
    public int hashCode() {
        return ObjectExtensions.hashCodeFor(this);
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(CharacterFlaw that) {
        Function<CharacterFlaw, Integer> byOrdinal = ((CharacterFlaw cf) -> cf.ordinal);
        Function<CharacterFlaw, String> byDetails = ((CharacterFlaw cf) -> cf.details);
        return Comparator.comparing(byOrdinal).thenComparing(byDetails).compare(this, that);
    }
    
}
