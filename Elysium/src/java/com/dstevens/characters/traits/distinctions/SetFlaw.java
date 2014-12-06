package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Flaw")
public class SetFlaw extends SetTrait {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
	private CharacterFlaw trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetFlaw() {
        this(null, null);
    }
	
	protected SetFlaw(TraitChangeStatus status, CharacterFlaw trait) {
		super(status);
		this.trait = trait;
	}

	@Override
	public PlayerCharacter apply(PlayerCharacter character) {
		return character.withFlaw(trait);
	}

	@Override
	public PlayerCharacter remove(PlayerCharacter character) {
		return character.withoutFlaw(trait);
	}

	@Override
	public String describe() {
		return trait.toString();
	}

}
