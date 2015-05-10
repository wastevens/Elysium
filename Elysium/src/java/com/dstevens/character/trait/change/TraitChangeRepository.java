package com.dstevens.character.trait.change;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TraitChangeRepository {

	private TraitChangeDao dao;

	@Autowired
	public TraitChangeRepository(TraitChangeDao dao) {
		this.dao = dao;
	}
	
	public TraitChange update(TraitChange traitChange) {
		return dao.save(traitChange);
	}
	
}
