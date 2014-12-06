package com.dstevens.persistence.auditing;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.suppliers.ClockSupplier;
import com.dstevens.suppliers.IdSupplier;

@Service
class AuditEventFactory {

    private IdSupplier idSupplier;
    private ClockSupplier clockSupplier;

    @Autowired
    public AuditEventFactory(IdSupplier idSupplier, ClockSupplier clockSupplier) {
        this.idSupplier = idSupplier;
        this.clockSupplier = clockSupplier;
    }
    
    public <E> AuditEvent<E> auditableFor(E foo, String auditMessage) {
        return new AuditEvent<E>(idSupplier.get(), foo, Date.from(clockSupplier.get().instant()), auditMessage);
    }
    
}
