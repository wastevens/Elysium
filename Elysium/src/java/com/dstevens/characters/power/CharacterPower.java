package com.dstevens.characters.power;

import java.util.Comparator;
import java.util.function.Function;
import javax.persistence.Embeddable;

import com.dstevens.utilities.ObjectExtensions;

@Embeddable
public class CharacterPower implements Comparable<CharacterPower> {

    private final Power power;
    private int rating;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterPower() {
        this(null, 0);
    }
    
    public CharacterPower(Power power, int rating) {
        this.power = power;
        this.rating = rating;
    }
    
    public final Power getPower() {
        return power;
    }

    public final int getRating() {
        return rating;
    }
    
    public CharacterPower withRating(int rating) {
        return new CharacterPower(power, rating);
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
    public int compareTo(CharacterPower that) {
        Function<CharacterPower, Power> byPower = ((CharacterPower c) -> c.power);
        return Comparator.comparing(byPower).compare(this, that);
    }
    
}
