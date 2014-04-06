package com.dstevens.players;

import static com.dstevens.collections.Lists.list;

import java.util.List;

import javax.persistence.*;

import com.dstevens.utilities.ObjectExtensions;

public class Troupe {

    private final long id;
    private final String name;
    private final Setting setting;
    private final List<Player> players;
    
    private Troupe(long id, String name, Setting setting, List<Player> players) {
        this.id = id;
        this.name = name;
        this.setting = setting;
        this.players = players;
    }
    
    @Entity
    @Table(name="troupe")
    public static class PersistableTroupe {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        public Long id;
        @Column(name="name")
        public String name;
        @Column(name="setting")
        public Setting setting;
        
        //Used only by Hibernate
        @SuppressWarnings("unused")
        private PersistableTroupe() {
        }
        
        public PersistableTroupe(Long id, String name, Setting setting) {
            this.id = id;
            this.name = name;
            this.setting = setting;
        }

        public Troupe toTroupe() {
            List<Player> noPlayers = list();
            return new Troupe(id, name, setting, noPlayers);
        }
        
        public static PersistableTroupe fromTroup(Troupe troupe) {
            return new PersistableTroupe(troupe.id, troupe.name, troupe.setting);
        }
        
        @Override
        public boolean equals(Object that) {
            return ObjectExtensions.equals(this, that);
        }
        
        @Override
        public int hashCode() {
            return ObjectExtensions.hashCodeFor(this);
        }
        
        @Override
        public String toString() {
            return ObjectExtensions.toStringFor(this);
        }
    }
    
    
    
}
