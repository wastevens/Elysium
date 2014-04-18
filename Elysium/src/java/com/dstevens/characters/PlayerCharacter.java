package com.dstevens.characters;

import java.util.*;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.characters.attributes.Attribute;
import com.dstevens.characters.skills.CharacterSkill;
import com.dstevens.persistence.auditing.Auditable;
import com.dstevens.players.*;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="PlayerCharacter")
public class PlayerCharacter implements Auditable<PlayerCharacter>, Comparable<PlayerCharacter> {

    @Id
    private final String id;
    
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinTable(name="PlayerPlayerCharacters",
               joinColumns = @JoinColumn(name="player_character_id"),
               inverseJoinColumns = @JoinColumn(name="player_id"))
    private final Player player;
    
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinTable(name="TroupePlayerCharacters",
               joinColumns = @JoinColumn(name="player_character_id"),
               inverseJoinColumns = @JoinColumn(name="troupe_id"))
    private final Troupe troupe;
    
    @Column(name="deleted_at")
    private final Date deleteTimestamp;
    
    @Column(name="name")
    private final String name;
    
    @Transient
    private Attribute physicalTraits;
    @Transient
    private Attribute socialTraits;
    @Transient
    private Attribute mentalTraits;
    
    @Transient
    private List<CharacterSkill> skills;

    //Hibernate only
    @Deprecated
    private PlayerCharacter() {
        this(null, null, null, null, null);
    }
    
    private PlayerCharacter(String id, Player player, Troupe troupe, String name, Date deleteTimestamp) {
        this.id = id;
        this.player = player;
        this.troupe = troupe;
        this.name = name;
        this.deleteTimestamp = deleteTimestamp;
    }
    
    public PlayerCharacter withName(String name) {
        return new PlayerCharacter(id, player, troupe, name, deleteTimestamp);
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public Troupe getTroupe() {
        return troupe;
    }
    
    @Override
    public PlayerCharacter delete(Date timestamp) {
        return new PlayerCharacter(id, player, troupe, name, timestamp);
    }

    @Override
    public PlayerCharacter undelete() {
        return new PlayerCharacter(id, player, troupe, name, null);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PlayerCharacter) {
            PlayerCharacter that = PlayerCharacter.class.cast(obj);
            return this.id.equals(that.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return ObjectExtensions.hashCodeFor(this);
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(PlayerCharacter that) {
        Function<PlayerCharacter, Date>  byDeletedTimestamp = ((PlayerCharacter p) -> p.deleteTimestamp);
        Function<PlayerCharacter, String> byName = ((PlayerCharacter p) -> p.name);
        return Comparator.comparing(byDeletedTimestamp, Comparator.nullsFirst(Comparator.naturalOrder())).
                      thenComparing(Comparator.comparing(byName)).
                      compare(this, that);
    }

}
