package com.dstevens.persistence;

import java.util.Date;

public class AuditableRepositoryImpl<E extends Auditable<E>> implements AuditableRepository<E> {

    private ElysiumDao<E> dao;
    private AuditEventRepository auditableRepository;
    private ClockProvider clockProvider;

    public AuditableRepositoryImpl(ElysiumDao<E> dao, AuditEventRepository auditableRepository, ClockProvider clockProvider) {
        this.dao = dao;
        this.auditableRepository = auditableRepository;
        this.clockProvider = clockProvider;
    }
    
    @Override
    public E create(E e) {
        return save(e, "Created");
    }

    @Override
    public E update(E e) {
        return save(e, "Updated");
    }

    @Override
    public void delete(E e) {
        save(e.delete(Date.from(clockProvider.getClock().instant())), "Deleted");
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

}
