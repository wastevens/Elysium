package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.collections.Sets;
import com.dstevens.persistence.IdGenerator;

@Service
public class TroupeFactory {

    private IdGenerator idGenerator;

    @Autowired
    public TroupeFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    
    public Troupe createTroupe(String name, Setting setting) {
        return new Troupe(idGenerator.createId(), name, setting, Sets.<Player>set());
    }
    
    
}
