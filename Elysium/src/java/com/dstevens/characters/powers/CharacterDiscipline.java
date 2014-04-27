package com.dstevens.characters.powers;

import java.util.Comparator;
import java.util.function.Function;

import javax.persistence.*;

import com.dstevens.characters.traits.RatedTrait;
import com.dstevens.utilities.ObjectExtensions;

@Embeddable
public class CharacterDiscipline implements Comparable<CharacterDiscipline>, RatedTrait<Discipline> {

    @Basic(optional=false)
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

    @Override
    public Discipline getTrait() {
        return power;
    }
    
    @Override
    public final int getRating() {
        return rating;
    }
    
    @Override
    public int ordinal() {
        return power.ordinal();
    }
    
    public CharacterDiscipline withRating(int rating) {
        return new CharacterDiscipline(power, rating);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof CharacterDiscipline) {
            CharacterDiscipline that = (CharacterDiscipline) o;
            return this.power == that.power;
        }
        return false;
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
