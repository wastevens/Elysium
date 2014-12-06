package com.dstevens.characters.traits.powers;

import java.util.Comparator;
import java.util.function.Predicate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.dstevens.characters.PlayerCharacter;
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
@Table(name="NecromanticPaths")
public class CharacterNecromancy implements EnumeratedTrait<Necromancy>, RatedTrait, Comparable<CharacterNecromancy> {

	@Id
    private final String id;
	
    @Basic(optional=false)
    private final Necromancy trait;
    
    @Column(name="rating")
    private int rating;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterNecromancy() {
        this(null, 0);
    }
    
    public CharacterNecromancy(Necromancy trait, int rating) {
    	this.id = new IdSupplier().get();
        this.trait = trait;
        this.rating = rating;
    }
    
    @Override
    public Necromancy trait() {
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
    
    public final CharacterNecromancy withRating(int rating) {
        return new CharacterNecromancy(trait, rating);
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
    public int compareTo(CharacterNecromancy that) {
    	return Comparator.comparing((CharacterNecromancy t) -> t.rating).
    			      thenComparing((CharacterNecromancy t) -> t.trait).
    			      compare(this, that);
    }

    @Override
    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withNecromanticPath(this);
    }
    
    @Override
    public PlayerCharacter removeFrom(PlayerCharacter character) {
    	return character.withoutNecromanticPath(this);
    }

    public Predicate<CharacterNecromancy> matches() {
		return ((Predicate<CharacterNecromancy>)(CharacterNecromancy t) -> t.trait.equals(this.trait));
	}
}
