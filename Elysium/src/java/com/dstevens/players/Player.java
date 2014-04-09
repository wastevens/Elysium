package com.dstevens.players;

import static com.dstevens.collections.Sets.setWith;

import java.util.Set;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="Player")
public class Player {

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

    //Used only for hibernate
    @Deprecated
    public Player() {
        this(null, null, null, null, null);
    }
    
    Player(String id, String name, String email, Set<Troupe> troupes, Set<PlayerCharacter> characters) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.troupes = troupes;
        this.characters = characters;
    }
    
    public final String getId() {
        return id;
    }

    public final Player withName(String name) {
        return new Player(id, name, email, troupes, characters);
    }
    
    public final String getName() {
        return name;
    }

    public final Player withEmail(String email) {
        return new Player(id, name, email, troupes, characters);
    }
    
    public final String getEmail() {
        return email;
    }

    public final Player withPlayer(PlayerCharacter character) {
        return new Player(id, name, email, troupes, setWith(characters, character));
    }
    
    public final Set<PlayerCharacter> getCharacters() {
        return characters;
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
}
