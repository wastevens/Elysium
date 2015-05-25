package com.dstevens.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCharacterRepository {

    private final PlayerCharacterFactory factory;
	private final PlayerCharacterDao dao;

    @Autowired
    public PlayerCharacterRepository(PlayerCharacterDao playerCharacterDao, PlayerCharacterFactory factory) {
        this.dao = playerCharacterDao;
        this.factory = factory;
    }
    
    public PlayerCharacter ensureExists(String name, Setting setting) {
        return createNewCharacterFor(name, setting);
    }

    private PlayerCharacter createNewCharacterFor(String name, Setting setting) {
        return dao.save(factory.createPlayerCharacter(name, setting));
    }

	public void delete(PlayerCharacter pc) {
		dao.delete(pc);
	}

	public PlayerCharacter update(PlayerCharacter pc) {
		return dao.save(pc);
	}
	
	public PlayerCharacter findWithId(Integer id) {
		return dao.findOne(id);
	}
	
	public PlayerCharacter findNamed(String name) {
		return dao.findNamed(name).iterator().next();
	}
	
	public Iterable<PlayerCharacter> findAllNamed(String name) {
		return dao.findNamed(name);
	}

	public void hardDelete(PlayerCharacter pc) {
		dao.delete(pc);
	}
}
