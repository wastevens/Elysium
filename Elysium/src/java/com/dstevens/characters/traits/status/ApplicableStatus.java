package com.dstevens.characters.traits.status;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.CharacterSpecializedTrait;
import com.dstevens.characters.traits.EnumeratedTrait;

public class ApplicableStatus implements EnumeratedTrait<Status>, ApplicableTrait, CharacterSpecializedTrait, Comparable<ApplicableStatus> {

    private Status trait;
	private String specialization;

    public ApplicableStatus(Status trait, String specialization) {
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
