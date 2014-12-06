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
@Table(name="Merits")
public class CharacterMerit implements EnumeratedTrait<Merits>, Comparable<CharacterMerit> {

	@Id
    private final String id;
	
	@Basic(optional=false)
	private Merits merit;
	
	@Column(name="specialization")
    private String specialization;

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterMerit() {
        this(null, null);
    }
	
	public CharacterMerit(Merits merit) {
		this(merit, null);
	}
	
	public CharacterMerit(Merits merit, String specialization) {
		this.id = new IdSupplier().get();
		this.merit = merit;
		this.specialization = specialization;
	}
	
	@Override
	public int ordinal() {
		return merit.ordinal();
	}

	private String getSpecialization() {
		return specialization;
	}
	
	@Override
	public Merits trait() {
		return merit;
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withMerit(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutMerit(this);
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
    public int compareTo(CharacterMerit that) {
        return characterDistinctionComparator().compare(this, that);
    }
    
    private Comparator<? super CharacterMerit> characterDistinctionComparator() {
        return Comparator.comparing((CharacterMerit t) -> t.ordinal()).
 thenComparing(Comparator.comparing((CharacterMerit t) -> t.getSpecialization()));
    }
}
