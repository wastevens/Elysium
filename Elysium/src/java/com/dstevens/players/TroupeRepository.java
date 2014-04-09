package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dstevens.persistence.AuditableRepository;

@Repository
public class TroupeRepository {

    private TroupeDao troupeDao;
    private TroupeFactory troupeFactory;
    private AuditableRepository auditableRepository;

    @Autowired
    public TroupeRepository(TroupeDao troupeDao, TroupeFactory troupeFactory, AuditableRepository auditableRepository) {
        this.troupeDao = troupeDao;
        this.troupeFactory = troupeFactory;
        this.auditableRepository = auditableRepository;
    }
    
    public Troupe createTroupe(String name, Setting setting) {
        Troupe troupe = troupeDao.save(troupeFactory.createTroupe(name, setting));
        auditableRepository.recordAuditableFor(troupe, "Created");
        return troupe;
    }
    
    
}
