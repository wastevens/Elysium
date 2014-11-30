package com.dstevens.characters.powers;

import java.util.function.Predicate;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.RatedTrait;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class CharacterDiscipline implements Comparable<CharacterDiscipline>, RatedTrait<Discipline> {

    @Basic(optional=false)
    private final Discipline trait;
    private int rating;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterDiscipline() {
        this(null, 0);
    }

	public static Predicate<CharacterDiscipline> matching(CharacterDiscipline power) {
		return (CharacterDiscipline t) -> t.trait().equals(power.trait);
	}
    
    public CharacterDiscipline(Discipline power, int rating) {
        this.trait = power;
        this.rating = rating;
    }
    
    @Override
    public Discipline trait() {
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
    
    public final CharacterDiscipline withRating(int rating) {
        return new CharacterDiscipline(trait, rating);
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
    public int compareTo(CharacterDiscipline that) {
        return ratedTraitComparator().compare(this, that);
    }

    @Override
    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withDiscipline(this);
    }
}
