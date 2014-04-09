package com.dstevens.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuditableRepository {

    private AuditableDao auditableDao;
    private AuditableFactory auditableFactory;

    @Autowired
    public AuditableRepository(AuditableDao auditableDao, AuditableFactory auditableFactory) {
        this.auditableDao = auditableDao;
        this.auditableFactory = auditableFactory;
    }
    
    public <E> void recordAuditableFor(E audited, String message) {
        auditableDao.save(auditableFactory.auditableFor(audited, message));
    }
    
    public <E> void purgeAuditablesFor(E audited) {
        auditableDao.deleteAuditEventsFor(audited);
    }
    
}
