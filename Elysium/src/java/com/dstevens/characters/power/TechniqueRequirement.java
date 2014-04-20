package com.dstevens.characters.power;

import com.dstevens.utilities.ObjectExtensions;

public class TechniqueRequirement {

    private final Power power;
    private final int rating;

    private TechniqueRequirement(Power power, int rating) {
        this.power = power;
        this.rating = rating;
    }

    public static TechniqueRequirement required(Power power, int rating) {
        return new TechniqueRequirement(power, rating);
    }
    
    public final Power getPower() {
        return power;
    }

    public final int getRating() {
        return rating;
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
