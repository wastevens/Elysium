package com.dstevens.persistence;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditEventFactory {

    private IdGenerator idGenerator;
    private ClockProvider clockProvider;

    @Autowired
    public AuditEventFactory(IdGenerator idGenerator, ClockProvider clockProvider) {
        this.idGenerator = idGenerator;
        this.clockProvider = clockProvider;
    }
    
    public <E> AuditEvent<E> auditableFor(E foo, String auditMessage) {
        return new AuditEvent<E>(idGenerator.createId(), foo, Date.from(clockProvider.getClock().instant()), auditMessage);
    }
    
}
