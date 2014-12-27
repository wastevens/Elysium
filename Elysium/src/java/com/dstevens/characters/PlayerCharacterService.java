package com.dstevens.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeRepository;
import com.dstevens.characters.traits.changes.TraitChangeStatusFactory;

@Service
public class PlayerCharacterService {

	private final PlayerCharacterRepository repository;
	private final TraitChangeStatusFactory traitChangeStatusFactory;
	private final TraitChangeRepository traitChangeRepository;
	
	@Autowired
	public PlayerCharacterService(PlayerCharacterRepository repository, TraitChangeStatusFactory traitChangeStatusFactory, TraitChangeRepository traitChangeRepository) {
		this.repository = repository;
		this.traitChangeStatusFactory = traitChangeStatusFactory;
		this.traitChangeRepository = traitChangeRepository;
	}
	
	public PlayerCharacter request(PlayerCharacter character, TraitChange<?> traitChange) {
		character.addTraitChange(traitChange.withStatus(traitChangeStatusFactory.requeted()));
		return repository.update(character);
	}
	
	public PlayerCharacter approve(PlayerCharacter character, TraitChange<?> traitChange) {
		traitChange.apply(character);
		traitChangeRepository.update(traitChange.withStatus(traitChangeStatusFactory.applied()));
		return repository.update(character);
	}
}
