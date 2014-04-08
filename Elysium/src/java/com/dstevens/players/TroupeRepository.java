package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dstevens.persistence.*;

@Repository
public class TroupeRepository {

    private TroupeDao troupeDao;
    private AuditableDao auditableDao;
    private AuditableFactory auditableFactory;

    @Autowired
    public TroupeRepository(TroupeDao troupeDao, AuditableDao auditableDao, AuditableFactory auditableFactory) {
        this.troupeDao = troupeDao;
        this.auditableDao = auditableDao;
        this.auditableFactory = auditableFactory;
    }
    
    public Troupe save(Troupe troupe) {
        Troupe save = troupeDao.save(troupe);
        Auditable<Troupe> auditableFor = auditableFactory.auditableFor(troupe, AuditableStatusEnum.AVAILABLE);
        auditableDao.save(auditableFor);
        return save;
    }
    
    
}
