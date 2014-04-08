package com.dstevens.players;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dstevens.persistence.*;

@Repository
public class TroupeRepository {

    private TroupeDao troupeDao;
    private AuditableDao auditableDao;
    private AuditableFactory auditableFactory;
    private TroupeFactory factory;

    @Autowired
    public TroupeRepository(TroupeDao troupeDao, TroupeFactory factory, AuditableDao auditableDao, AuditableFactory auditableFactory) {
        this.troupeDao = troupeDao;
        this.factory = factory;
        this.auditableDao = auditableDao;
        this.auditableFactory = auditableFactory;
    }
    
    @Transactional
    public Troupe createTroupe(String name, Setting setting) {
        Troupe troupe = factory.createTroupe(name, setting);
        Troupe save = troupeDao.save(troupe);
        auditableDao.save(auditableFactory.auditableFor(troupe, "Created"));
        return save;
    }
    
    
}
