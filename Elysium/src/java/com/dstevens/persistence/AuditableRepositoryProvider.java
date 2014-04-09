package com.dstevens.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditableRepositoryProvider {

    private final AuditEventRepository auditableRepository;
    private final ClockProvider clockProvider;

    @Autowired
    public AuditableRepositoryProvider(AuditEventRepository auditableRepository, ClockProvider clockProvider) {
        this.auditableRepository = auditableRepository;
        this.clockProvider = clockProvider;
    }
    
    public <E extends SoftDeletable<E>> AuditableRepository<E> repositoryFor(ElysiumDao<E> dao) {
        return new AuditableRepositoryImpl<>(dao, auditableRepository, clockProvider);
    }
    
}
