package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerRepository {

    private TroupeDao troupeDao;
    private PlayerDao dao;
    private PlayerFactory factory;

    @Autowired
    public PlayerRepository(PlayerDao dao, TroupeDao troupeDao, PlayerFactory factory) {
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
        Player newPlayer = factory.createPlayer(playerName, playerEmail);
        Troupe withPlayer = troupe.withPlayer(newPlayer);
        troupeDao.save(withPlayer);
        return newPlayer;
    }

	public void delete(Player player) {
		dao.delete(player);
	}
}
