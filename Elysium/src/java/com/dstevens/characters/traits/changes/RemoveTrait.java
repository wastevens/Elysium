package com.dstevens.characters.traits.changes;

import org.hibernate.annotations.ForeignKey;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@SuppressWarnings("deprecation")
@Entity
@DiscriminatorValue("RemoveTrait")
class RemoveTrait extends TraitChange {

	@OneToOne(cascade={CascadeType.ALL})
	@ForeignKey(name="TraitChange_TraitToRemove_FK", inverseName="TraitToRemove_TraitChange_FK")
	private final TraitChange traitToRemove;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private RemoveTrait() {
        this(null, null);
    }
	
	protected RemoveTrait(TraitChange traitToRemove) {
		this(traitToRemove.status(), traitToRemove);
	}
	private RemoveTrait(TraitChangeStatus status, TraitChange traitToRemove) {
		super(status);
		this.traitToRemove = traitToRemove;
	}

	@Override
	public PlayerCharacter apply(PlayerCharacter character) {
		TraitChange currentTraitToRemove = traitToRemove;
		while(currentTraitToRemove != null) {
			currentTraitToRemove.setStatus(TraitChangeStatus.APPLIED);
			currentTraitToRemove.remove(character);
			currentTraitToRemove = currentTraitToRemove.associatedTrait();
		}
		return character;
	}
	
	@Override
	public PlayerCharacter remove(PlayerCharacter character) {
		TraitChange currentTraitToRemove = traitToRemove;
		currentTraitToRemove.apply(character);
		while(currentTraitToRemove.hasAssociatedTrait()) {
			currentTraitToRemove = traitToRemove.associatedTrait();
			currentTraitToRemove.apply(character);
		}
		return character;
	}

	@Override
	public String describe() {
		String removeTrait = String.format("Removing %1$s", traitToRemove.describe());
		String nextTrait = (hasAssociatedTrait() ? String.format(", restoring %1$s", associatedTrait().describe()) : "");
		return String.format("(%1$s) %2$s%3$s", status(), removeTrait, nextTrait);
	}

}
