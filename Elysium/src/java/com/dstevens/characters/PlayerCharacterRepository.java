package com.dstevens.characters;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.players.Player;
import com.dstevens.players.PlayerDao;
import com.dstevens.players.Troupe;
import com.dstevens.players.TroupeDao;

@Service
public class PlayerCharacterRepository {

    private final PlayerCharacterFactory factory;
    private final PlayerDao playerDao;
    private final TroupeDao troupeDao;
	private final PlayerCharacterDao playerCharacterDao;

    @Autowired
    public PlayerCharacterRepository(PlayerCharacterDao playerCharacterDao, PlayerDao playerDao, TroupeDao troupeDao, PlayerCharacterFactory factory) {
        this.playerCharacterDao = playerCharacterDao;
		this.playerDao = playerDao;
        this.troupeDao = troupeDao;
        this.factory = factory;
    }
    
    public PlayerCharacter ensureExists(Troupe troupe, Player player, String name) {
        Predicate<PlayerCharacter> characterWithName = ((PlayerCharacter pc) -> (pc.getName().equals(name) && !pc.isDeleted()));
        if (player.getCharacters().stream().anyMatch(characterWithName)) {
            return player.getCharacters().stream().filter(characterWithName).findFirst().get();
        }
        return createNewCharacterFor(troupe, player, name);
    }

    private PlayerCharacter createNewCharacterFor(Troupe troupe, Player player, String name) {
        PlayerCharacter character = playerCharacterDao.save(factory.createPlayerCharacter(name));
        playerDao.save(player.withCharacter(character));
        troupeDao.save(troupe.withCharacter(character));
        return character;
    }

	public void delete(PlayerCharacter pc) {
		playerCharacterDao.delete(pc);
	}

	public PlayerCharacter update(PlayerCharacter pc) {
		return playerCharacterDao.save(pc);
	}
}
