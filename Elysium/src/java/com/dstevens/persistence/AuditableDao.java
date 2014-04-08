package com.dstevens.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dstevens.players.Troupe;

public interface AuditableDao extends CrudRepository<Auditable<?>, String> {

    @Query("SELECT a FROM Auditable a WHERE a.audited = ?1")
    List<Auditable<Troupe>> findByAudited(Troupe t);
    
    
}
