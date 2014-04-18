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
    private final int rating;
    
    @Transient
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
    
}
