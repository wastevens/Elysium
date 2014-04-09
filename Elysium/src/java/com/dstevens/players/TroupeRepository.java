package com.dstevens.players;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dstevens.persistence.*;

@Repository
public class TroupeRepository {

    private TroupeDao troupeDao;
    private TroupeFactory troupeFactory;
    private AuditableRepository auditableRepository;
    private ClockProvider clockProvider;

    @Autowired
    public TroupeRepository(TroupeDao troupeDao, TroupeFactory troupeFactory, AuditableRepository auditableRepository, ClockProvider clockProvider) {
        this.troupeDao = troupeDao;
        this.troupeFactory = troupeFactory;
        this.auditableRepository = auditableRepository;
        this.clockProvider = clockProvider;
    }
    
    public Troupe createTroupe(String name, Setting setting) {
        return saveTroupe(troupeFactory.createTroupe(name, setting), "Created");
    }
    
    public Troupe updateTroupe(Troupe troupe) {
        return saveTroupe(troupe, "Updated");
    }
    
    public void deleteTroupe(Troupe troupe) {
        saveTroupe(troupe.deleteAt(Date.from(clockProvider.getClock().instant())), "Deleted");
    }
    
    public Troupe undeleteTroupe(Troupe troupe) {
        return saveTroupe(troupe.undelete(), "Undeleted");
    }
    
    public void hardDeleteTroupe(Troupe troupe) {
        troupeDao.delete(troupe);
        auditableRepository.purgeAuditablesFor(troupe);
    }
    
    private Troupe saveTroupe(Troupe troupe, String message) {
        Troupe savedTroupe = troupeDao.save(troupe);
        auditableRepository.recordAuditableFor(savedTroupe, message);
        return savedTroupe;
    }
    
    
}
