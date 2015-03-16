package com.dstevens.characters.traits.status;

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

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.CharacterSpecializedTrait;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="Status")
public class CharacterStatus implements ApplicableTrait, CharacterSpecializedTrait, Comparable<CharacterStatus> {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "characterStatus", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
	
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
		this.id = null;
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
}
