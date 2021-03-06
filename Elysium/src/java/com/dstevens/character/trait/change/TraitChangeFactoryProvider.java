package com.dstevens.character.trait.change;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.character.PlayerCharacter;

@Service
public class TraitChangeFactoryProvider {

	private final ProvidedTraitChangeFactory traitChangeFactory;

	@Autowired
	public TraitChangeFactoryProvider(ProvidedTraitChangeFactory traitChangeFactory) {
		this.traitChangeFactory = traitChangeFactory;
	}
	
	public TraitChangeFactory giveTraits() {
		return traitChangeFactory;
	}

	public TraitChangeFactory buyTraitsFor(PlayerCharacter character) {
		return new PurchasedTraitChangeFactory(character, traitChangeFactory);
	}
	
}
