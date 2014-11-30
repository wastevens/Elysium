package com.dstevens.characters.powers.magics;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.RatedTrait;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class CharacterThaumaturgy implements Comparable<CharacterThaumaturgy>, RatedTrait<Thaumaturgy> {

    @Basic(optional=false)
    private final Thaumaturgy trait;
    private int rating;

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterThaumaturgy() {
        this(null, 0);
    }
    
    public CharacterThaumaturgy(Thaumaturgy trait, int rating) {
        this.trait = trait;
        this.rating = rating;
    }
    
    @Override
    public Thaumaturgy trait() {
        return trait;
    }
    
    @Override
    public final int ordinal() {
        return trait.ordinal();
    }
    
    @Override
    public final int rating() {
        return rating;
    }
    
    public final CharacterThaumaturgy withRating(int rating) {
        return new CharacterThaumaturgy(trait, rating);
    }
    
    @Override
    public boolean equals(Object that) {
        return ratedTraitEquals(that);
    }
    
    @Override
    public int hashCode() {
        return ratedTraitHashcode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(CharacterThaumaturgy that) {
        return ratedTraitComparator().compare(this, that);
    }

    @Override
    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withThaumaturgicalPath(this);
    }
}
