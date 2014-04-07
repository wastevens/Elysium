package com.dstevens.players;

import static com.dstevens.collections.Sets.*;

import java.util.Set;
import javax.persistence.*;

import com.dstevens.collections.Sets;
import com.dstevens.configuration.IdGenerator;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="Troupe")
public class Troupe {

    @Id
    private final String id;
    
    @Column(name="name") 
    private final String name;
    
    @Column(name="setting") 
    private final Setting setting;
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="TroupePlayers",
               joinColumns = @JoinColumn(name="troupe_id"),
               inverseJoinColumns = @JoinColumn(name="player_id"))
    private final Set<Player> players;
    
    public static Troupe newTroupe(String name, Setting setting) {
        return new Troupe(IdGenerator.createId(), name, setting, Sets.<Player>set());
    }
    
    private Troupe() {
        this(IdGenerator.createId(), null, null, Sets.<Player>set());
    }
    
    private Troupe(String id, String name, Setting setting, Set<Player> players) {
        this.id = id;
        this.name = name;
        this.setting = setting;
        this.players = players;
    }
    
    public final String getId() {
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
        return new Troupe(id, name, setting, setWith(players, player));
    }
    
    public final Troupe withoutPlayer(Player player) {
        return new Troupe(id, name, setting, setWithout(players, player));
    }
    
    public final Troupe clearPlayers() {
        return new Troupe(id, name, setting, Sets.<Player>set());
    }
    
    public final Set<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Troupe) {
            Troupe that = Troupe.class.cast(obj);
            return this.id.equals(that.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }
}
