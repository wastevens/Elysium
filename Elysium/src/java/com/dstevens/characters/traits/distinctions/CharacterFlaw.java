package com.dstevens.characters.traits.distinctions;

import java.util.Comparator;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.CharacterSpecializedTrait;
import com.dstevens.characters.traits.EnumeratedTrait;
import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Flaws")
public class CharacterFlaw implements EnumeratedTrait<Flaw>, ApplicableTrait, CharacterSpecializedTrait, Comparable<CharacterFlaw> {

	@Id
    private final String id;
	
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
		this.id = new IdSupplier().get();
		this.trait = trait;
		this.specialization = specialization;
	}
	
	@Override
	public int ordinal() {
		return trait.ordinal();
	}

	@Override
	public String getSpecialization() {
		return specialization;
	}
	
	@Override
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
        return Comparator.comparing((CharacterFlaw t) -> t.ordinal()).
 thenComparing(Comparator.comparing((CharacterFlaw t) -> t.getSpecialization()));
    }

	public Predicate<CharacterFlaw> matches() {
		return ((Predicate<CharacterFlaw>)(CharacterFlaw t) -> t.trait.equals(this.trait)).
		    and((Predicate<CharacterFlaw>)(CharacterFlaw t) -> StringUtils.equalsIgnoreCase(t.specialization, this.specialization));
	}

	@Override
	public ApplicableTrait copy() {
		return this;
	}
}
