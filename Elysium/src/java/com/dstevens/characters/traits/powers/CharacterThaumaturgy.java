package com.dstevens.characters.traits.powers;

import java.util.Comparator;
import java.util.function.Predicate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.EnumeratedTrait;
import com.dstevens.characters.traits.RatedTrait;
import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ThaumaturgicalPaths")
public class CharacterThaumaturgy implements RatedTrait, ApplicableTrait, EnumeratedTrait<Thaumaturgy>, Comparable<CharacterThaumaturgy> {

	@Id
    private final String id;
	
    @Basic(optional=false)
    private final Thaumaturgy trait;
    
    @Column(name="rating")
    private int rating;

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterThaumaturgy() {
        this(null, 0);
    }
    
    public CharacterThaumaturgy(Thaumaturgy trait, int rating) {
    	this.id = new IdSupplier().get();
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
    	return EqualsBuilder.reflectionEquals(this, that, "id");
    }

    @Override
    public int hashCode() {
    	return HashCodeBuilder.reflectionHashCode(this, "id");
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(CharacterThaumaturgy that) {
    	return Comparator.comparing((CharacterThaumaturgy t) -> t.rating).
			          thenComparing((CharacterThaumaturgy t) -> t.trait).
			          compare(this, that);
    }

    @Override
    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withThaumaturgicalPath(this);
    }
    
    @Override
    public PlayerCharacter removeFrom(PlayerCharacter character) {
    	return character.withoutThaumaturgicalPath(this);
    }

    public Predicate<CharacterThaumaturgy> matches() {
		return ((Predicate<CharacterThaumaturgy>)(CharacterThaumaturgy t) -> t.trait.equals(this.trait));
	}
}
