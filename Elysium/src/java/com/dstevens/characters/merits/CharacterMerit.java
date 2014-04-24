package com.dstevens.characters.merits;

import java.util.Comparator;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.utilities.ObjectExtensions;

@Embeddable
public class CharacterMerit implements Comparable<CharacterMerit> {

    @Column(name="merit_id")
    private int meritId;
    
    @Column(name="merit_type")
    private String meritType;
    
    @Column(name="details")
    private String details;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private CharacterMerit() {
        this(0, null, null);
    }
    
    public CharacterMerit(Merit merit) {
        this(merit, null);
    }
    
    public CharacterMerit(Merit merit, String details) {
        this(merit.ordinal(), merit.getType(), details);
    }
    
    private CharacterMerit(int meritId, String meritType, String details) {
        this.meritId = meritId;
        this.meritType = meritType;
        this.details = details;
    }
    
    public Merit getMerit() {
        return MeritTranslator.ofTypeWithId(meritType, meritId);
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
    public int compareTo(CharacterMerit that) {
        Function<CharacterMerit, String> byMeritType = ((CharacterMerit cm) -> cm.meritType);
        Function<CharacterMerit, Integer> byMeritId = ((CharacterMerit cm) -> cm.meritId);
        Function<CharacterMerit, String> byDetails = ((CharacterMerit cm) -> cm.details);
        return Comparator.comparing(byMeritType).thenComparing(byMeritId).thenComparing(byDetails).compare(this, that);
    }
    
}
