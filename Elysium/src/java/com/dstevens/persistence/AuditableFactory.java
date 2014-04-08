package com.dstevens.persistence;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditableFactory {

    private IdGenerator idGenerator;
    private ClockProvider clockProvider;

    @Autowired
    public AuditableFactory(IdGenerator idGenerator, ClockProvider clockProvider) {
        this.idGenerator = idGenerator;
        this.clockProvider = clockProvider;
    }
    
    public <E> Auditable<E> auditableFor(E foo, AuditableStatus available) {
        return new Auditable<E>(idGenerator.createId(), foo, available, Date.from(clockProvider.getClock().instant()));
    }
    
}
