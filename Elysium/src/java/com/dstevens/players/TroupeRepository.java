package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dstevens.persistence.auditing.*;

@Repository
public class TroupeRepository extends AbstractAuditableRepository<Troupe> {

    @Autowired
    public TroupeRepository(TroupeDao dao, AuditableRepositoryProvider repositoryProvider) {
        super(repositoryProvider.repositoryFor(dao));
    }
}
