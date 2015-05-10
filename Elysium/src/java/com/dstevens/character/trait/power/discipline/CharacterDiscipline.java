package com.dstevens.character.trait.power.discipline;

import java.util.Comparator;
import java.util.function.Predicate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.RatedTrait;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="Disciplines")
public class CharacterDiscipline implements ApplicableTrait, RatedTrait, Comparable<CharacterDiscipline> {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "characterDiscipline", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
	
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
    	this.id = null;
        this.trait = power;
        this.rating = rating;
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
	public final Discipline trait() {
		return trait;
	}
}
