package com.dstevens.players;

import static com.dstevens.collections.Lists.listWith;

import java.util.List;
import javax.persistence.*;

import com.dstevens.collections.Lists;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="Troupe")
public class Troupe {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final Long id;
    
    @Column(name="name") 
    private final String name;
    
    @Column(name="setting") 
    private final Setting setting;
    
    @OneToMany
    @JoinTable(name="TroupePlayers",
               joinColumns = @JoinColumn(name="troupe_id"),
               inverseJoinColumns = @JoinColumn(name="player_id"))
    private final List<Player> players;
    
    public static Troupe newTroupe(String name, Setting setting) {
        return new Troupe(null, name, setting, Lists.<Player>list());
    }
    
    private Troupe() {
        this(null, null, null, Lists.<Player>list());
    }
    
    private Troupe(Long id, String name, Setting setting, List<Player> players) {
        this.id = id;
        this.name = name;
        this.setting = setting;
        this.players = players;
    }
    
    public final Long getId() {
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
        return new Troupe(id, name, setting, listWith(players, player));
    }
    
    public final List<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Troupe) {
            Troupe that = Troupe.class.cast(obj);
            if (this.id == null || that.id == null)
                return false;
            return this.id.equals(that.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.intValue();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }
    
}
