package com.dstevens.characters.attributes;

import javax.persistence.Embeddable;

import com.dstevens.utilities.ObjectExtensions;

@Embeddable
public class PhysicalAttribute {

    private final int rating;
    private final boolean strengthFocus;
    private final boolean dexterityFocus;
    private final boolean staminaFocus;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private PhysicalAttribute() {
        this(0, false, false, false);
    }

    public PhysicalAttribute(int rating) {
        this(rating, false, false, false);
    }
    
    private PhysicalAttribute(int rating, boolean strengthFocus, boolean dexterityFocus, boolean staminaFocus) {
        this.rating = rating;
        this.strengthFocus = strengthFocus;
        this.dexterityFocus = dexterityFocus;
        this.staminaFocus = staminaFocus;
    }
    
    public PhysicalAttribute withRating(int rating) {
        return new PhysicalAttribute(rating, strengthFocus, dexterityFocus, staminaFocus);
    }
    
    public PhysicalAttribute withStrengthFocus(boolean strengthFocus) {
        return new PhysicalAttribute(rating, strengthFocus, dexterityFocus, staminaFocus);
    }
    
    public PhysicalAttribute withDexterityFocus(boolean dexterityFocus) {
        return new PhysicalAttribute(rating, strengthFocus, dexterityFocus, staminaFocus);
    }
    
    public PhysicalAttribute withStaminaFocus(boolean staminaFocus) {
        return new PhysicalAttribute(rating, strengthFocus, dexterityFocus, staminaFocus);
    }
    
    public int getRating() {
        return rating;
    }
    
    public final boolean hasStrengthFocus() {
        return strengthFocus;
    }

    public final boolean hasDexterityFocus() {
        return dexterityFocus;
    }

    public final boolean hasStaminaFocus() {
        return staminaFocus;
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
