package com.dstevens.persistence.auditing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.dstevens.persistence.ClockProvider;

@Service
public class AuditableRepositoryProvider {

    private final AuditEventRepository auditableRepository;
    private final ClockProvider clockProvider;

    @Autowired
    public AuditableRepositoryProvider(AuditEventRepository auditableRepository, ClockProvider clockProvider) {
        this.auditableRepository = auditableRepository;
        this.clockProvider = clockProvider;
    }
    
    public <E extends Auditable<E>> AuditableRepository<E> repositoryFor(CrudRepository<E, String> dao) {
        return new AuditableRepositoryImpl<>(dao, auditableRepository, clockProvider);
    }
    
}
