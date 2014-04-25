package com.dstevens.players;

import java.util.Iterator;

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
        Iterator<Troupe> troupes = dao.findNamed(troupeName).iterator();
        if (troupes.hasNext()) {
            return troupes.next();
        }
        return create(factory.createTroupe(troupeName, setting));
    }
    
    
}
