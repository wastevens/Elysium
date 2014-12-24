package com.dstevens.characters.traits.changes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;

@Service
public class TraitChangeFactoryProvider {

	private final ProvidedTraitChangeFactory traitChangeFactory;
	private final TraitChangeStatusFactory traitChangeStatusFactory;

	@Autowired
	public TraitChangeFactoryProvider(ProvidedTraitChangeFactory traitChangeFactory, TraitChangeStatusFactory traitChangeStatusFactory) {
		this.traitChangeFactory = traitChangeFactory;
		this.traitChangeStatusFactory = traitChangeStatusFactory;
	}
	
	public TraitChangeFactory giveTraits() {
		return traitChangeFactory;
	}

	public TraitChangeFactory buyTraitsFor(PlayerCharacter character) {
		return new PurchasedTraitChangeFactory(character, traitChangeFactory, traitChangeStatusFactory);
	}
	
}
