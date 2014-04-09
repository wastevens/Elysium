package com.dstevens.persistence.auditing;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;

public interface AuditEventDao extends CrudRepository<AuditEvent<?>, String> {

    @Query("SELECT a FROM Auditable a WHERE a.audited = ?1 ORDER BY a.timestamp DESC")
    <E> List<AuditEvent<E>> findAllAuditEventsFor(E e);
    
    @Query("SELECT a FROM Auditable a WHERE a.timestamp = (select MAX(b.timestamp) FROM Auditable b WHERE b.audited = ?1)")
    <E> AuditEvent<E> findMostRecentAuditEventFor(E e);
    
    @Query("SELECT a FROM Auditable a WHERE a.timestamp = (select MIN(b.timestamp) FROM Auditable b WHERE b.audited = ?1)")
    <E> AuditEvent<E> findFirstAuditEventFor(E e);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Auditable a WHERE a.audited = ?1")
    <E> void deleteAuditEventsFor(E e);
}
