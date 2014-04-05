package com.dstevens.players;

import static com.dstevens.collections.Lists.list;

import java.util.List;

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
    
    public static class PersistableTroupe {
        public Long id;
        public String name;
        public Setting setting;
        
        private PersistableTroupe(long id, String name, Setting setting) {
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
    }
    
    
    
}
