package com.dstevens.players;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.persistence.auditing.*;

@Service
public class PlayerRepository extends AbstractAuditableRepository<Player> {

    private PlayerDao dao;
    private PlayerFactory factory;

    @Autowired
    public PlayerRepository(PlayerDao dao, AuditableRepositoryProvider repositoryProvider, PlayerFactory factory) {
        super(repositoryProvider.repositoryFor(dao));
        this.dao = dao;
        this.factory = factory;
    }

    public Player ensureExists(String playerName, String playerEmail) {
        Iterator<Player> troupes = dao.findNamed(playerName).iterator();
        if (troupes.hasNext()) {
            return troupes.next();
        }
        return create(factory.createPlayer(playerName, playerEmail));
    }
}
