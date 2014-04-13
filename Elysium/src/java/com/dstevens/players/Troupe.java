package com.dstevens.players;

import static com.dstevens.collections.Sets.*;
import static com.dstevens.comparators.ChainComparator.compare;

import java.util.*;

import javax.persistence.*;

import com.dstevens.collections.Sets;
import com.dstevens.comparators.*;
import com.dstevens.comparators.Comparators;
import com.dstevens.persistence.auditing.Auditable;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="Troupe")
public class Troupe implements Auditable<Troupe>, Comparable<Troupe> {

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

    @Column(name="deleted_at")
    private final Date deleteTimestamp;
    
    //Used only for hibernate
    @Deprecated
    public Troupe() {
        this(null, null, null, null, null);
    }
    
    Troupe(String id, String name, Setting setting, Set<Player> players) {
        this(id, name, setting, players, null);
    }
    
    private Troupe(String id, String name, Setting setting, Set<Player> players, Date deleteTimestamp) {
        this.id = id;
        this.name = name;
        this.setting = setting;
        this.players = players;
        this.deleteTimestamp = deleteTimestamp;
    }
    
    public String getId() {
        return id;
    }

    public Troupe withName(String name) {
        return new Troupe(id, name, setting, players, deleteTimestamp);
    }
    
    public String getName() {
        return name;
    }

    public Troupe withSetting(Setting setting) {
        return new Troupe(id, name, setting, players, deleteTimestamp);
    }
    
    public Setting getSetting() {
        return setting;
    }

    public Troupe withPlayer(Player player) {
        return new Troupe(id, name, setting, setWith(players, player), deleteTimestamp);
    }
    
    public Troupe withoutPlayer(Player player) {
        return new Troupe(id, name, setting, setWithout(players, player), deleteTimestamp);
    }
    
    public Troupe clearPlayers() {
        return new Troupe(id, name, setting, Sets.<Player>set(), deleteTimestamp);
    }
    
    public Set<Player> getPlayers() {
        return players;
    }

    public Troupe delete(Date deleteTimestamp) {
        return new Troupe(id, name, setting, players, deleteTimestamp);
    }
    
    public Troupe undelete() {
        return new Troupe(id, name, setting, players, null);
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

    @Override
    public int compareTo(Troupe that) {
        return compare(BY_DELETED_TIMESTMAP).then(BY_NAME).compare(this, that);
    }
    
    private static final Comparator<Troupe> BY_DELETED_TIMESTMAP = new Comparator<Troupe>() {
        @Override
        public int compare(Troupe o1, Troupe o2) {
            return Comparators.nullsFirst(DateComparator.INSTANCE).compare(o1.deleteTimestamp, o2.deleteTimestamp);
        } 
    };
    
    private static final Comparator<Troupe> BY_NAME = new Comparator<Troupe>() {
        @Override
        public int compare(Troupe o1, Troupe o2) {
            return StringComparator.INSTANCE.compare(o1.name, o2.name);
        } 
    };
}
