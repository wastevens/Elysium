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
@Table(name="Disciplines")
public class CharacterDiscipline implements EnumeratedTrait<Discipline>, ApplicableTrait, RatedTrait, Comparable<CharacterDiscipline> {

	@Id
    private final String id;
	
    @Basic(optional=false)
    private final Discipline trait;
    
    @Column(name="rating")
    private int rating;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterDiscipline() {
        this(null, 0);
    }
    
    public CharacterDiscipline(Discipline power, int rating) {
    	this.id = new IdSupplier().get();
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
    public int compareTo(CharacterDiscipline that) {
        return Comparator.comparing((CharacterDiscipline t) -> t.rating).
        		      thenComparing((CharacterDiscipline t) -> t.trait).
        		      compare(this, that);
    }

    @Override
    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withDiscipline(this);
    }
    
    @Override
    public PlayerCharacter removeFrom(PlayerCharacter character) {
    	return character.withoutDiscipline(this);
    }

	public Predicate<CharacterDiscipline> matches() {
		return ((Predicate<CharacterDiscipline>)(CharacterDiscipline t) -> t.trait.equals(this.trait));
	}

	@Override
	public ApplicableTrait copy() {
		return new CharacterDiscipline(trait, rating);
	}
}
