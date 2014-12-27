package com.dstevens.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.changes.TraitChange;

@Service
public class PlayerCharacterService {

	private final PlayerCharacterRepository repository;
	
	@Autowired
	public PlayerCharacterService(PlayerCharacterRepository repository) {
		this.repository = repository;
	}
	
	public PlayerCharacter request(PlayerCharacter character, TraitChange<?> traitChange) {
		return repository.update(character.request(traitChange));
	}
	
	public PlayerCharacter approve(PlayerCharacter character, TraitChange<?> traitChange) {
		return repository.update(character.apply(traitChange));
	}
	
	public PlayerCharacter approveAllOutstandingChanges(PlayerCharacter character) {
		character.getRequestedTraitChanges().forEach((TraitChange<?> t) -> character.apply(t));
		return repository.update(character);
	}
}
