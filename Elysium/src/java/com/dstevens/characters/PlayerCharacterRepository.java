package com.dstevens.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.persistence.auditing.*;
import com.dstevens.players.*;

@Service
public class PlayerCharacterRepository extends AbstractAuditableRepository<PlayerCharacter> {

    private final PlayerCharacterFactory factory;
    private PlayerDao playerDao;
    private TroupeDao troupeDao;

    @Autowired
    public PlayerCharacterRepository(PlayerCharacterDao dao, PlayerDao playerDao, TroupeDao troupeDao, AuditableRepositoryProvider repositoryProvider, PlayerCharacterFactory factory) {
        super(repositoryProvider.repositoryFor(dao));
        this.playerDao = playerDao;
        this.troupeDao = troupeDao;
        this.factory = factory;
    }

    public PlayerCharacter createNewCharacterFor(Troupe troupe, Player player, String name) {
        PlayerCharacter character = create(factory.createPlayerCharacter(name));
        playerDao.save(player.withCharacter(character));
        troupeDao.save(troupe.withCharacter(character));
        return character;
    }
}
