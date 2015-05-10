package com.dstevens.character.trait.distinction.flaw;

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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.CharacterSpecializedTrait;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="Flaws")
public class CharacterFlaw implements ApplicableTrait, CharacterSpecializedTrait, Comparable<CharacterFlaw> {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "characterFlaw", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
	
	@Basic(optional=false)
	private Flaw trait;
	
	@Column(name="specialization")
    private String specialization;

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterFlaw() {
        this(null, null);
    }
	
	public CharacterFlaw(Flaw trait) {
		this(trait, null);
	}
	
	public CharacterFlaw(Flaw trait, String specialization) {
		this.id = null;
		this.trait = trait;
		this.specialization = specialization;
	}
	
	@Override
	public String getSpecialization() {
		return specialization;
	}
	
	public Flaw trait() {
		return trait;
	}
	
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withFlaw(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutFlaw(this);
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
    public int compareTo(CharacterFlaw that) {
        return characterDistinctionComparator().compare(this, that);
    }
    
    private Comparator<? super CharacterFlaw> characterDistinctionComparator() {
        return Comparator.comparing((CharacterFlaw t) -> t.trait).
 thenComparing(Comparator.comparing((CharacterFlaw t) -> t.specialization));
    }

	public Predicate<CharacterFlaw> matches() {
		return ((Predicate<CharacterFlaw>)(CharacterFlaw t) -> t.trait.equals(this.trait)).
		    and((Predicate<CharacterFlaw>)(CharacterFlaw t) -> StringUtils.equalsIgnoreCase(t.specialization, this.specialization));
	}
}
