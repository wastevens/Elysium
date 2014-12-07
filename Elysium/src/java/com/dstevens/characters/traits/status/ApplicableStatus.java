package com.dstevens.characters.traits.status;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.CharacterSpecializedTrait;
import com.dstevens.characters.traits.EnumeratedTrait;
import com.dstevens.suppliers.IdSupplier;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ApplicableStatus")
public class ApplicableStatus implements EnumeratedTrait<Status>, ApplicableTrait, CharacterSpecializedTrait, Comparable<ApplicableStatus> {

    @Id
    private final String id;
	
	@Basic(optional=true)
    private Status trait;
	
	@Column(name="specialization")
	private String specialization;

	//Hibernate only
    @SuppressWarnings("unused")
	@Deprecated
    private ApplicableStatus() {
        this(null, null);
    }
    
    public ApplicableStatus(Status trait, String specialization) {
    	this.id = new IdSupplier().get();
        this.trait = trait;
        this.specialization = specialization;
    }
	
	@Override
	public int compareTo(ApplicableStatus that) {
		return this.trait.compareTo(that.trait);
	}

	@Override
	public String getSpecialization() {
		return specialization;
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withStatus(new CharacterStatus(trait, specialization));
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutStatus(new CharacterStatus(trait, specialization));
	}

	@Override
	public ApplicableTrait copy() {
		return this;
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
