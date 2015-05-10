package com.dstevens.character.trait.power.discipline;

import com.dstevens.utilities.ObjectExtensions;

class TechniqueRequirement {

    private final Discipline power;
    private final int rating;

    private TechniqueRequirement(Discipline power, int rating) {
        this.power = power;
        this.rating = rating;
    }

    public static TechniqueRequirement required(Discipline power, int rating) {
        return new TechniqueRequirement(power, rating);
    }
    
    public final Discipline getPower() {
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
