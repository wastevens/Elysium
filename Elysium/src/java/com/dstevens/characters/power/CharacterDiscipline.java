package com.dstevens.characters.power;

import java.util.Comparator;
import java.util.function.Function;
import javax.persistence.Embeddable;

import com.dstevens.utilities.ObjectExtensions;

@Embeddable
public class CharacterDiscipline implements Comparable<CharacterDiscipline> {

    private final Discipline power;
    private int rating;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterDiscipline() {
        this(null, 0);
    }
    
    public CharacterDiscipline(Discipline power, int rating) {
        this.power = power;
        this.rating = rating;
    }
    
    public final Discipline getPower() {
        return power;
    }

    public final int getRating() {
        return rating;
    }
    
    public CharacterDiscipline withRating(int rating) {
        return new CharacterDiscipline(power, rating);
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
    public int compareTo(CharacterDiscipline that) {
        Function<CharacterDiscipline, Discipline> byPower = ((CharacterDiscipline c) -> c.power);
        return Comparator.comparing(byPower).compare(this, that);
    }
    
}
