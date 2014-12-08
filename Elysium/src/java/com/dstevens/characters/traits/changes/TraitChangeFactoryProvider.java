package com.dstevens.characters.traits.changes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;

@Service
public class TraitChangeFactoryProvider {

	private final ProvidedTraitChangeFactory traitChangeFactory;

	@Autowired
	public TraitChangeFactoryProvider(ProvidedTraitChangeFactory traitChangeFactory) {
		this.traitChangeFactory = traitChangeFactory;
	}
	
	public TraitChangeFactory buyTraitsFor(PlayerCharacter character) {
		return new PurchasedTraitChangeFactory(character, traitChangeFactory);
	}
	
	public TraitChangeFactory giveTraits() {
		return traitChangeFactory;
	}
	
}
