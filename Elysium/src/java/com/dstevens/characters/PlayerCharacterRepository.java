package com.dstevens.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.persistence.auditing.*;
import com.dstevens.players.*;

@Service
public class PlayerCharacterRepository extends AbstractAuditableRepository<PlayerCharacter> {

    private final PlayerCharacterFactory factory;

    @Autowired
    public PlayerCharacterRepository(PlayerCharacterDao dao, AuditableRepositoryProvider repositoryProvider, PlayerCharacterFactory factory) {
        super(repositoryProvider.repositoryFor(dao));
        this.factory = factory;
    }

    public PlayerCharacter createNewCharacterFor(Troupe troupe, Player player, String name) {
        return create(factory.createPlayerCharacter(name));
    }
}
