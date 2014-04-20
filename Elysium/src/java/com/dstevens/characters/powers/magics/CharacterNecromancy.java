package com.dstevens.characters.powers.magics;

import java.util.Comparator;
import java.util.function.Function;
import javax.persistence.Embeddable;

import com.dstevens.utilities.ObjectExtensions;

@Embeddable
public class CharacterNecromancy implements Comparable<CharacterNecromancy> {

    private final Necromancy path;
    private int rating;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterNecromancy() {
        this(null, 0);
    }
    
    public CharacterNecromancy(Necromancy path, int rating) {
        this.path = path;
        this.rating = rating;
    }
    
    public final Necromancy getPath() {
        return path;
    }

    public final int getRating() {
        return rating;
    }
    
    public CharacterNecromancy withRating(int rating) {
        return new CharacterNecromancy(path, rating);
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
    public int compareTo(CharacterNecromancy that) {
        Function<CharacterNecromancy, Necromancy> byPower = ((CharacterNecromancy c) -> c.path);
        return Comparator.comparing(byPower).compare(this, that);
    }
    
}
