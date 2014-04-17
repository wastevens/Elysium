package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.persistence.auditing.*;

@Service
public class TroupeRepository extends AbstractAuditableRepository<Troupe> {

    @Autowired
    public TroupeRepository(TroupeDao dao, AuditableRepositoryProvider repositoryProvider) {
        super(repositoryProvider.repositoryFor(dao));
    }
}
