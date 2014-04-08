package com.dstevens.persistence;

import org.springframework.data.repository.CrudRepository;

public interface AuditableDao extends CrudRepository<Auditable<?>, String> {

}
