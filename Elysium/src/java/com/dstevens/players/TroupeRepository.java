package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dstevens.persistence.*;
import com.dstevens.persistence.auditing.*;

@Repository
public class TroupeRepository implements AuditableRepository<Troupe> {

    private AuditableRepository<Troupe> repository;

    @Autowired
    public TroupeRepository(TroupeDao dao, AuditableRepositoryProvider repositoryProvider) {
        this.repository = repositoryProvider.repositoryFor(dao);
    }
    
    public Troupe create(Troupe troupe) {
        return repository.create(troupe);
    }
    
    public Troupe update(Troupe troupe) {
        return repository.update(troupe);
    }
    
    public void delete(Troupe troupe) {
        repository.delete(troupe);
    }
    
    public Troupe undelete(Troupe troupe) {
        return repository.undelete(troupe);
    }
    
    public void hardDelete(Troupe troupe) {
        repository.hardDelete(troupe);
    }
}
