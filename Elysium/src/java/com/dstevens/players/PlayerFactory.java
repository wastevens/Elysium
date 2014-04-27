package com.dstevens.players;

import static com.dstevens.collections.Sets.set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.suppliers.IdSupplier;

@Service
public class PlayerFactory {

    private IdSupplier idSupplier;

    @Autowired
    public PlayerFactory(IdSupplier idSupplier) {
        this.idSupplier = idSupplier;
    }
    
    public Player createPlayer(String name, String email) {
        return new Player(idSupplier.get(), name, email, set());
    }
    
}
