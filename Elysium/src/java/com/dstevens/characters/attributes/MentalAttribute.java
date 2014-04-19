package com.dstevens.characters.attributes;

import java.util.*;
import javax.persistence.*;

import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="MentalAttribute")
public class MentalAttribute {

    @Id
    @Column(name="character_id")
    private final String id;
    
    @Column(name="rating")
    private int rating;
    
    @ElementCollection
    @CollectionTable(name="MentalAttributeFocus", joinColumns=@JoinColumn(name="character_id"))
    @Column(name="focuses")
    private final Set<Focus> focuses;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private MentalAttribute() {
        this(null, 0, EnumSet.noneOf(Focus.class));
    }
    
    public MentalAttribute(String id) {
        this(id, 0, EnumSet.noneOf(Focus.class));
    }
    
    private MentalAttribute(String id, int rating, Set<Focus> focuses) {
        this.id = id;
        this.rating = rating;
        this.focuses = focuses;
    }

    public MentalAttribute withRating(int rating) {
        this.rating = rating;
        return this;
    }
    
    public final int getRating() {
        return rating;
    }

    public final Set<Focus> getFocuses() {
        return focuses;
    }

    public MentalAttribute withFocus(Focus focus) {
        this.focuses.add(focus);
        return this;
    }
    
    public MentalAttribute withoutFocus(Focus focus) {
        this.focuses.remove(focus);
        return this;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MentalAttribute) {
            MentalAttribute that = MentalAttribute.class.cast(obj);
            return this.id.equals(that.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }
    
    public static enum Focus {

        INTELLIGENCE,
        WITS,
        PERCEPTION;
        
    }
    
}
