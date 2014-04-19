package com.dstevens.characters.attributes;

import java.util.*;
import javax.persistence.*;

import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="SocialAttribute")
public class SocialAttribute {

    @Id
    @Column(name="character_id")
    private final String id;
    
    @Column(name="rating")
    private int rating;
    
    @ElementCollection
    @CollectionTable(name="SocialAttributeFocus", joinColumns=@JoinColumn(name="character_id"))
    @Column(name="focuses")
    private final Set<Focus> focuses;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SocialAttribute() {
        this(null, 0, EnumSet.noneOf(Focus.class));
    }
    
    public SocialAttribute(String id) {
        this(id, 0, EnumSet.noneOf(Focus.class));
    }
    
    private SocialAttribute(String id, int rating, Set<Focus> focuses) {
        this.id = id;
        this.rating = rating;
        this.focuses = focuses;
    }

    public SocialAttribute withRating(int rating) {
        this.rating = rating;
        return this;
    }
    
    public final int getRating() {
        return rating;
    }

    public final Set<Focus> getFocuses() {
        return focuses;
    }

    public SocialAttribute withFocus(Focus focus) {
        this.focuses.add(focus);
        return this;
    }
    
    public SocialAttribute withoutFocus(Focus focus) {
        this.focuses.remove(focus);
        return this;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SocialAttribute) {
            SocialAttribute that = SocialAttribute.class.cast(obj);
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

        CHARISMA,
        MANIPULATION,
        APPEARANCE;
        
    }
    
}
