package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.persistence.auditing.*;

@Service
public class PlayerRepository extends AbstractAuditableRepository<Player> {

    private TroupeDao troupeDao;
    private PlayerDao dao;
    private PlayerFactory factory;

    @Autowired
    public PlayerRepository(PlayerDao dao, TroupeDao troupeDao, AuditableRepositoryProvider repositoryProvider, PlayerFactory factory) {
        super(repositoryProvider.repositoryFor(dao));
        this.dao = dao;
        this.troupeDao = troupeDao;
        this.factory = factory;
    }

    public Player ensureExists(String playerName, String playerEmail, Troupe troupe) {
        Player player = dao.findUndeletedWithEmail(playerEmail);
        if (player != null) {
            if (!troupe.getPlayers().contains(player)) {
                troupeDao.save(troupe.withPlayer(player));
            }
            return player;
        }
        Player createPlayer = factory.createPlayer(playerName, playerEmail);
        Player newPlayer = create(createPlayer);
        Troupe withPlayer = troupe.withPlayer(newPlayer);
        troupeDao.save(withPlayer);
        return newPlayer;
    }
}
