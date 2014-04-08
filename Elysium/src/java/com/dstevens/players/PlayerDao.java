package com.dstevens.players;

import org.springframework.data.repository.CrudRepository;

public interface PlayerDao extends CrudRepository<Player, String> {
}
