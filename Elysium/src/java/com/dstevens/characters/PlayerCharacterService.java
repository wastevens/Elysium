package com.dstevens.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatusFactory;

@Service
public class PlayerCharacterService {

	private final PlayerCharacterRepository repository;
	private final TraitChangeStatusFactory traitChangeStatusFactory;
	
	@Autowired
	public PlayerCharacterService(PlayerCharacterRepository repository, TraitChangeStatusFactory traitChangeStatusFactory) {
		this.repository = repository;
		this.traitChangeStatusFactory = traitChangeStatusFactory;
	}
	
	public PlayerCharacter request(PlayerCharacter character, TraitChange<?> traitChange) {
		return repository.update(character.request(traitChange.withStatus(traitChangeStatusFactory.requeted())));
	}
	
	public PlayerCharacter approve(PlayerCharacter character, TraitChange<?> traitChange) {
		return repository.update(character.apply(traitChange.withStatus(traitChangeStatusFactory.requeted())));
	}
	
	public PlayerCharacter approveAllOutstandingChanges(PlayerCharacter character) {
		character.getTraitChanges().forEach((TraitChange<?> t) -> character.apply(t.withStatus(traitChangeStatusFactory.requeted())));
		return repository.update(character);
	}
}
