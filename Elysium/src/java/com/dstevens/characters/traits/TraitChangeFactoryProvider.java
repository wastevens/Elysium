package com.dstevens.characters.traits;

import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;

@Service
public class TraitChangeFactoryProvider {

	public TraitChangeFactory buyTraitsFor(PlayerCharacter character) {
		return new PurchasedTraitChangeFactory(character);
	}
	
	public TraitChangeFactory giveTraits() {
		return new ProvidedTraitChangeFactory();
	}
	
}
