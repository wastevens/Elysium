package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.persistence.auditing.*;

@Service
public class TroupeRepository extends AbstractAuditableRepository<Troupe> {

    private final TroupeFactory factory;
    private TroupeDao dao;

    @Autowired
    public TroupeRepository(TroupeDao dao, AuditableRepositoryProvider repositoryProvider, TroupeFactory factory) {
        super(repositoryProvider.repositoryFor(dao));
        this.dao = dao;
        this.factory = factory;
    }

    public Troupe ensureExists(String troupeName, Setting setting) {
        Troupe troupe = dao.findUndeletedNamed(troupeName);
        if (troupe != null) {
            return troupe;
        }
        return create(factory.createTroupe(troupeName, setting));
    }

    public Troupe findNamed(String troupeName) {
        return dao.findUndeletedNamed(troupeName);
    }
}
