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
@DiscriminatorValue("Merit")
public class SetMerit extends SetTrait {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
	private CharacterMerit trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetMerit() {
        this(null, null);
    }
	
	protected SetMerit(TraitChangeStatus status, CharacterMerit trait) {
		super(status);
		this.trait = trait;
	}

	@Override
	public PlayerCharacter apply(PlayerCharacter character) {
		return character.withMerit(trait);
	}

	@Override
	public PlayerCharacter remove(PlayerCharacter character) {
		return character.withoutMerit(trait);
	}

	@Override
	public String describe() {
		return trait.toString();
	}

}
