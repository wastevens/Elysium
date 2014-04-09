package com.dstevens.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuditableDao extends CrudRepository<Auditable<?>, String> {

    @Query("SELECT a FROM Auditable a WHERE a.audited = ?1")
    <E> List<Auditable<E>> findByAudited(E e);
    
    
}
