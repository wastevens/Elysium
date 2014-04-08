package com.dstevens.persistence;

import org.springframework.data.repository.CrudRepository;

public interface AuditableRepository extends CrudRepository<Auditable<Identified>, String> {

}
