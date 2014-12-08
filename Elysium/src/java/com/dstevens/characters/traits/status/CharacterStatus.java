package com.dstevens.characters.traits.status;

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
@Table(name="Status")
public class CharacterStatus implements EnumeratedTrait<Status>, ApplicableTrait, CharacterSpecializedTrait, Comparable<CharacterStatus> {

	@Id
    private final String id;
	
	@Basic(optional=false)
    private Status trait;
	
	@Column(name="specialization")
	private String specialization;
	
	//Hibernate only
    @SuppressWarnings("unused")
	@Deprecated
    private CharacterStatus() {
        this(null, null);
    }
	
	public CharacterStatus(Status trait, String specialization) {
		this.id = new IdSupplier().get();
		this.trait = trait;
		this.specialization = specialization;
	}

	public Predicate<CharacterStatus> matching() {
		return (CharacterStatus t) -> t.trait.equals(this.trait) && StringUtils.equals(t.specialization, this.specialization);
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
	public int compareTo(CharacterStatus arg0) {
		return this.trait.compareTo(arg0.trait);
	}

	@Override
	public String getSpecialization() {
		return specialization;
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withStatus(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutStatus(this);
	}

	@Override
	public int ordinal() {
		return trait.ordinal();
	}

	@Override
	public Status trait() {
		return trait;
	}

}
