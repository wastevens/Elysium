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
    
    Troupe(long id, String name, Setting setting, List<Player> players) {
        this.id = id;
        this.name = name;
        this.setting = setting;
        this.players = players;
    }
    
    public final long getId() {
        return id;
    }

    public final Troupe withName(String name) {
        return new Troupe(id, name, setting, players);
    }
    
    public final String getName() {
        return name;
    }

    public final Troupe withSetting(Setting setting) {
        return new Troupe(id, name, setting, players);
    }
    
    public final Setting getSetting() {
        return setting;
    }

    public final Troupe withPlayer(Player player) {
        players.add(player);
        return new Troupe(id, name, setting, players);
    }
    
    public final List<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Troupe) {
            Troupe that = Troupe.class.cast(obj);
            return this.id == that.id;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return (int) id;
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }
    
    @Entity
    @Table(name="troupe")
    static class PersistableTroupe {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;
        @Column(name="name")
        private String name;
        @Column(name="setting")
        private Setting setting;
        
        //Used only by Hibernate
        @SuppressWarnings("unused")
        private PersistableTroupe() {
        }
        
        PersistableTroupe(Long id, String name, Setting setting) {
            this.id = id;
            this.name = name;
            this.setting = setting;
        }

        Troupe toTroupe() {
            List<Player> noPlayers = list();
            return new Troupe(id, name, setting, noPlayers);
        }
        
        static PersistableTroupe fromTroup(Troupe troupe) {
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
