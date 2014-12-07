package com.dstevens.characters.traits.status;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Status")
public class CharacterStatus {

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

}
