package com.dstevens.persistence;


public interface AuditableRepository<E extends SoftDeletable<E>> {

    E create(E e);
    
    E update(E e);
    
    void delete(E e);
    
    E undelete(E e);
    
    void hardDelete(E e);
    
}
