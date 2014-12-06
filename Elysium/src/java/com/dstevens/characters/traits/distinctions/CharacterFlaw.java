package com.dstevens.characters.traits.distinctions;

import java.util.Comparator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.dstevens.characters.PlayerCharacter;
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
public class CharacterFlaw implements EnumeratedTrait<Flaw>, Comparable<CharacterFlaw> {

	@Id
    private final String id;
	
	@Basic(optional=false)
	private Flaw flaw;
	
	@Column(name="specialization")
    private String specialization;

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterFlaw() {
        this(null, null);
    }
	
	public CharacterFlaw(Flaw flaw) {
		this(flaw, null);
	}
	
	public CharacterFlaw(Flaw flaw, String specialization) {
		this.id = new IdSupplier().get();
		this.flaw = flaw;
		this.specialization = specialization;
	}
	
	@Override
	public int ordinal() {
		return flaw.ordinal();
	}

	private String getSpecialization() {
		return specialization;
	}
	
	@Override
	public Flaw trait() {
		return flaw;
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
}
