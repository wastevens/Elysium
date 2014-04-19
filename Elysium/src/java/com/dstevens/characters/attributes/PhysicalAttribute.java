package com.dstevens.characters.attributes;

import java.util.*;
import javax.persistence.*;

import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="PhysicalAttribute")
public class PhysicalAttribute {

    @Id
    @Column(name="character_id")
    private final String id;
    
    @Column(name="rating")
    private int rating;
    
    @ElementCollection
    @CollectionTable(name="PhysicalAttributeFocus", joinColumns=@JoinColumn(name="character_id"))
    @Column(name="focuses")
    private final Set<PhysicalAttributeFocus> focuses;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private PhysicalAttribute() {
        this(null, 0, EnumSet.noneOf(PhysicalAttributeFocus.class));
    }
    
    public PhysicalAttribute(String id) {
        this(id, 0, EnumSet.noneOf(PhysicalAttributeFocus.class));
    }
    
    private PhysicalAttribute(String id, int rating, Set<PhysicalAttributeFocus> focuses) {
        this.id = id;
        this.rating = rating;
        this.focuses = focuses;
    }

    public PhysicalAttribute withRating(int rating) {
        this.rating = rating;
        return this;
    }
    
    public final int getRating() {
        return rating;
    }

    public final Set<PhysicalAttributeFocus> getFocuses() {
        return focuses;
    }

    public PhysicalAttribute withFocus(PhysicalAttributeFocus focus) {
        this.focuses.add(focus);
        return this;
    }
    
    public PhysicalAttribute withoutFocus(PhysicalAttributeFocus focus) {
        this.focuses.remove(focus);
        return this;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PhysicalAttribute) {
            PhysicalAttribute that = PhysicalAttribute.class.cast(obj);
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
    
}
