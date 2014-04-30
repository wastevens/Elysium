package com.dstevens.characters.distinctions;

import java.util.Comparator;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Inheritance
@DiscriminatorColumn(name="distinction_type")
@Table(name="Distinctions")
public abstract class CharacterDistinction implements Comparable<CharacterDistinction> {

    @Id
    private final String id;
    
    @Column(name="ordinal")
    private int ordinal;
    
    @Column(name="type")
    private String type;
    
    @Column(name="details")
    private String details;
    
    protected CharacterDistinction(Distinction<?> distinction) {
        this(distinction, null);
    }
    
    protected CharacterDistinction(Distinction<?> distinction, String details) {
        this(distinction.ordinal(), distinction.getType(), details);
    }
    
    protected CharacterDistinction(int ordinal, String type, String details) {
        this.id = new IdSupplier().get();
        this.ordinal = ordinal;
        this.type = type;
        this.details = details;
    }
    
    public abstract Distinction<?> getDistinction();
    
    public final String getDetails() {
        return details;
    }
    
    public final int ordinal() {
        return ordinal;
    }
    
    public final String type() {
        return type;
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
    public int compareTo(CharacterDistinction that) {
        Function<CharacterDistinction, String> byType = ((CharacterDistinction cm) -> cm.type);
        Function<CharacterDistinction, Integer> byOrdinal = ((CharacterDistinction cm) -> cm.ordinal);
        Function<CharacterDistinction, String> byDetails = ((CharacterDistinction cm) -> cm.details);
        return Comparator.comparing(byType).thenComparing(byOrdinal).thenComparing(byDetails).compare(this, that);
    }
    
}
