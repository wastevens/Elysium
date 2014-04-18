package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.persistence.auditing.*;

@Service
public class PlayerRepository extends AbstractAuditableRepository<Player> {

    @Autowired
    public PlayerRepository(PlayerDao dao, AuditableRepositoryProvider repositoryProvider) {
        super(repositoryProvider.repositoryFor(dao));
    }
}
