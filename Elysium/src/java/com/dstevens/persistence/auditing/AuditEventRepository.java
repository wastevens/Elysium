package com.dstevens.persistence.auditing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class AuditEventRepository {

    private AuditEventDao auditableDao;
    private AuditEventFactory auditableFactory;

    @Autowired
    public AuditEventRepository(AuditEventDao auditableDao, AuditEventFactory auditableFactory) {
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
