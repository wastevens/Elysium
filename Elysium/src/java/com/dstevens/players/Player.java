package com.dstevens.players;

import static com.dstevens.collections.Sets.*;
import static com.dstevens.comparators.Comparators.nullSafe;
import static com.dstevens.comparators.ChainComparator.compare;

import java.util.*;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.collections.Sets;
import com.dstevens.comparators.*;
import com.dstevens.persistence.auditing.Auditable;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="Player")
public class Player implements Auditable<Player>, Comparable<Player> {

    @Id
    private final String id;
    
    @Column(name="name")
    private final String name;
    
    @Column(name="email")
    private final String email;
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="TroupePlayers",
               joinColumns = @JoinColumn(name="player_id"),
               inverseJoinColumns = @JoinColumn(name="troupe_id"))
    private final Set<Troupe> troupes;
    
    @Transient
    private final Set<PlayerCharacter> characters;

    @Column(name="deleted_at")
    private final Date deleteTimestamp;
    
    //Used only for hibernate
    @Deprecated
    public Player() {
        this(null, null, null, null, null, null);
    }
    
    Player(String id, String name, String email, Set<Troupe> troupes, Set<PlayerCharacter> characters) {
        this(id, name, email, troupes, characters, null);
    }
    
    private Player(String id, String name, String email, Set<Troupe> troupes, Set<PlayerCharacter> characters, Date deleteTimestamp) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.troupes = troupes;
        this.characters = characters;
        this.deleteTimestamp = deleteTimestamp;
    }
    
    public final String getId() {
        return id;
    }

    public final Player withName(String name) {
        return new Player(id, name, email, troupes, characters, deleteTimestamp);
    }
    
    public final String getName() {
        return name;
    }

    public final Player withEmail(String email) {
        return new Player(id, name, email, troupes, characters, deleteTimestamp);
    }
    
    public final String getEmail() {
        return email;
    }

    public final Player addCharacter(PlayerCharacter character) {
        return new Player(id, name, email, troupes, setWith(characters, character), deleteTimestamp);
    }
    
    public final Player removeCharacter(PlayerCharacter character) {
        return new Player(id, name, email, troupes, setWithout(characters, character), deleteTimestamp);
    }
    
    public final Player clearCharacters() {
        return new Player(id, name, email, troupes, Sets.<PlayerCharacter>set(), deleteTimestamp);
    }
    
    public final Set<PlayerCharacter> getCharacters() {
        return characters;
    }

    public final Player joinTroupe(Troupe troupe) {
        return new Player(id, name, email, setWith(troupes, troupe), characters, deleteTimestamp);
    }
    
    public final Player leaveTroupe(Troupe troupe) {
        return new Player(id, name, email, setWithout(troupes, troupe), characters, deleteTimestamp);
    }
    
    public final Player leaveAllTroupes() {
        return new Player(id, name, email, Sets.<Troupe>set(), characters, deleteTimestamp);
    }
    
    public final Set<Troupe> getTroupes() {
        return troupes;
    }
    
    public Player delete(Date deleteTimestamp) {
        return new Player(id, name, email, troupes, characters, deleteTimestamp);
    }
    
    public Player undelete() {
        return new Player(id, name, email, troupes, characters, null);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            Player that = Player.class.cast(obj);
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
    public int compareTo(Player that) {
        return compare(BY_DELETED_TIMESTMAP).then(BY_NAME).compare(this, that);
    }
    
    private static final Comparator<Player> BY_DELETED_TIMESTMAP = new Comparator<Player>() {
        @Override
        public int compare(Player o1, Player o2) {
            return nullSafe(DateComparator.INSTANCE).compare(o1.deleteTimestamp, o2.deleteTimestamp);
        } 
    };
    
    
    private static final Comparator<Player> BY_NAME = new Comparator<Player>() {
        @Override
        public int compare(Player o1, Player o2) {
            return nullSafe(StringComparator.INSTANCE).compare(o1.name, o2.name);
        } 
    };
}
