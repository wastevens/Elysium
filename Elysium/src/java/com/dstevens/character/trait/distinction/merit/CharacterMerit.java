package com.dstevens.character.trait.distinction.merit;

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
@Table(name="Merits")
public class CharacterMerit implements ApplicableTrait, CharacterSpecializedTrait, Comparable<CharacterMerit> {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "characterMerit", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
	
	@Basic(optional=false)
	private Merit trait;
	
	@Column(name="specialization")
    private String specialization;

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterMerit() {
        this(null, null);
    }
	
	public CharacterMerit(Merit trait) {
		this(trait, null);
	}
	
	public CharacterMerit(Merit trait, String specialization) {
		this.id = null;
		this.trait = trait;
		this.specialization = specialization;
	}
	
	@Override
	public String getSpecialization() {
		return specialization;
	}
	
	public Merit trait() {
		return trait;
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
        return Comparator.comparing((CharacterMerit t) -> t.trait).
 thenComparing(Comparator.comparing((CharacterMerit t) -> t.specialization));
    }

	public Predicate<CharacterMerit> matches() {
		return ((Predicate<CharacterMerit>)(CharacterMerit t) -> t.trait.equals(this.trait)).
		    and((Predicate<CharacterMerit>)(CharacterMerit t) -> StringUtils.equalsIgnoreCase(t.specialization, this.specialization));
	}
}
