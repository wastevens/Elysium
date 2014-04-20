package com.dstevens.characters.magic;

import java.util.Comparator;
import java.util.function.Function;
import javax.persistence.Embeddable;

import com.dstevens.utilities.ObjectExtensions;

@Embeddable
public class CharacterThaumaturgy implements Comparable<CharacterThaumaturgy> {

    private final Thaumaturgy path;
    private int rating;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterThaumaturgy() {
        this(null, 0);
    }
    
    public CharacterThaumaturgy(Thaumaturgy path, int rating) {
        this.path = path;
        this.rating = rating;
    }
    
    public final Thaumaturgy getPath() {
        return path;
    }

    public final int getRating() {
        return rating;
    }
    
    public CharacterThaumaturgy withRating(int rating) {
        return new CharacterThaumaturgy(path, rating);
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
    public int compareTo(CharacterThaumaturgy that) {
        Function<CharacterThaumaturgy, Thaumaturgy> byPower = ((CharacterThaumaturgy c) -> c.path);
        return Comparator.comparing(byPower).compare(this, that);
    }
    
}
