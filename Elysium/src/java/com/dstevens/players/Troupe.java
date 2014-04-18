package com.dstevens.players;

import static com.dstevens.collections.Sets.*;

import java.util.*;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.collections.Sets;
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
    private Set<Player> players;

    @OneToMany(mappedBy= "troupe", cascade = {CascadeType.REMOVE})
    private final Set<PlayerCharacter> characters;

    @Column(name="deleted_at")
    private final Date deleteTimestamp;
    
    //Used only for hibernate
    @Deprecated
    public Troupe() {
        this(null, null, null);
    }
    
    Troupe(String id, String name, Setting setting) {
        this(id, name, setting, set(), set(), null);
    }
    
    private Troupe(String id, String name, Setting setting, Set<Player> players, Set<PlayerCharacter> characters, Date deleteTimestamp) {
        this.id = id;
        this.name = name;
        this.setting = setting;
        this.players = players;
        this.characters = characters;
        this.deleteTimestamp = deleteTimestamp;
    }
    
    public String getId() {
        return id;
    }

    public Troupe withName(String name) {
        return new Troupe(id, name, setting, players, characters, deleteTimestamp);
    }
    
    public String getName() {
        return name;
    }

    public Troupe withSetting(Setting setting) {
        return new Troupe(id, name, setting, players, characters, deleteTimestamp);
    }
    
    public Setting getSetting() {
        return setting;
    }

    public Troupe withPlayer(Player player) {
        return new Troupe(id, name, setting, setWith(players, player), characters, deleteTimestamp);
    }
    
    public Troupe withoutPlayer(Player player) {
        return new Troupe(id, name, setting, setWithout(players, player), characters, deleteTimestamp);
    }
    
    public Troupe clearPlayers() {
        return new Troupe(id, name, setting, Sets.<Player>set(), characters, deleteTimestamp);
    }
    
    public Set<Player> getPlayers() {
        return players;
    }
    
    public Troupe withCharacter(PlayerCharacter playerCharacter) {
        return new Troupe(id, name, setting, players, setWith(characters, playerCharacter), deleteTimestamp);
    }
    
    public Troupe withoutCharacter(PlayerCharacter playerCharacter) {
        return new Troupe(id, name, setting, players, setWithout(characters, playerCharacter), deleteTimestamp);
    }
    
    public Set<PlayerCharacter> getCharacters() {
        return characters;
    }

    public Troupe delete(Date deleteTimestamp) {
        return new Troupe(id, name, setting, players, characters, deleteTimestamp);
    }
    
    public Troupe undelete() {
        return new Troupe(id, name, setting, players, characters, null);
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
        Function<Troupe, Date>  byDeletedTimestamp = ((Troupe t) -> t.deleteTimestamp);
        Function<Troupe, String> byName = ((Troupe t) -> t.name);
        return Comparator.comparing(byDeletedTimestamp, Comparator.nullsFirst(Comparator.naturalOrder())).
                      thenComparing(Comparator.comparing(byName)).
                      compare(this, that);
    }

}
