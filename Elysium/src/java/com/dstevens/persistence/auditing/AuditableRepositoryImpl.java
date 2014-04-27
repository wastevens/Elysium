package com.dstevens.persistence.auditing;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.dstevens.suppliers.ClockSupplier;

public class AuditableRepositoryImpl<E extends Auditable<E>> implements AuditableRepository<E> {

    private CrudRepository<E, String> dao;
    private AuditEventRepository auditableRepository;
    private ClockSupplier clockSupplier;

    public AuditableRepositoryImpl(CrudRepository<E, String> dao, AuditEventRepository auditableRepository, ClockSupplier clockSupplier) {
        this.dao = dao;
        this.auditableRepository = auditableRepository;
        this.clockSupplier = clockSupplier;
    }
    
    @Override
    public E create(E e) {
        return save(e, "Created");
    }

    @Override
    public E update(E e) {
        return save(e);
    }

    @Override
    public void delete(E e) {
        save(e.delete(Date.from(clockSupplier.get().instant())), "Deleted");
    }

    @Override
    public E undelete(E e) {
        return save(e.undelete(), "Undeleted");
    }

    @Override
    public void hardDelete(E e) {
        dao.delete(e);
        auditableRepository.purgeAuditablesFor(e);
    }
    
    private E save(E e, String message) {
        E saved = dao.save(e);
        auditableRepository.recordAuditableFor(saved, message);
        return saved;
    }
    
    private E save(E e) {
        return dao.save(e);
    }

}
