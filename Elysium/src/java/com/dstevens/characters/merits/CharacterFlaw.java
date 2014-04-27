package com.dstevens.characters.merits;

import java.util.Comparator;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.utilities.ObjectExtensions;

@Embeddable
public class CharacterFlaw implements Comparable<CharacterFlaw> {

    @Column(name="flaw_id")
    private int flawId;
    
    @Column(name="flaw_type")
    private String flawType;
    
    @Column(name="details")
    private String details;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private CharacterFlaw() {
        this(0, null, null);
    }
    
    public CharacterFlaw(Flaw<?> flaw) {
        this(flaw.ordinal(), flaw.getType(), null);
    }
    
    public CharacterFlaw(Flaw<?> flaw, String details) {
        this(flaw.ordinal(), flaw.getType(), details);
    }
    
    private CharacterFlaw(int flawId, String flawType, String details) {
        this.flawId = flawId;
        this.flawType = flawType;
        this.details = details;
    }
    
    public Flaw<?> getFlaw() {
        return FlawTranslator.ofTypeWithId(flawType, flawId);
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
        Function<CharacterFlaw, String> byMeritType = ((CharacterFlaw cm) -> cm.flawType);
        Function<CharacterFlaw, Integer> byMeritId = ((CharacterFlaw cm) -> cm.flawId);
        Function<CharacterFlaw, String> byDetails = ((CharacterFlaw cm) -> cm.details);
        return Comparator.comparing(byMeritType).thenComparing(byMeritId).thenComparing(byDetails).compare(this, that);
    }
    
}
