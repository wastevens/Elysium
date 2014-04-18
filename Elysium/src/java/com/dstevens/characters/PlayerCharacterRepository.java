package com.dstevens.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.persistence.auditing.*;

@Service
public class PlayerCharacterRepository extends AbstractAuditableRepository<PlayerCharacter> {

    @Autowired
    public PlayerCharacterRepository(PlayerCharacterDao dao, AuditableRepositoryProvider repositoryProvider) {
        super(repositoryProvider.repositoryFor(dao));
    }
}
