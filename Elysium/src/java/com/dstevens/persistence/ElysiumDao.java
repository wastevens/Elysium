package com.dstevens.persistence;

import org.springframework.data.repository.CrudRepository;

public interface ElysiumDao<E> extends CrudRepository<E, String> {

}
