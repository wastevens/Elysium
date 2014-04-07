package com.dstevens.players;

import static com.dstevens.collections.Lists.listWith;

import java.util.List;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.collections.Lists;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="Player")
public class Player {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final Long id;
    
    @Column(name="name")
    private final String name;
    
    @Column(name="setting")
    private final String email;
    
    @Transient
    private final List<PlayerCharacter> characters;
    
    public static Player newPlayer(String name, String email) {
        return new Player(null, name, email, Lists.<PlayerCharacter>list());
    }
    
    private Player() {
        this(null, null, null, Lists.<PlayerCharacter>list());
    }
    
    private Player(Long id, String name, String email, List<PlayerCharacter> characters) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.characters = characters;
    }
    
    public final Long getId() {
        return id;
    }

    public final Player withName(String name) {
        return new Player(id, name, email, characters);
    }
    
    public final String getName() {
        return name;
    }

    public final Player withEmail(String email) {
        return new Player(id, name, email, characters);
    }
    
    public final String getEmail() {
        return email;
    }

    public final Player withPlayer(PlayerCharacter character) {
        return new Player(id, name, email, listWith(characters, character));
    }
    
    public final List<PlayerCharacter> getCharacters() {
        return characters;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            Player that = Player.class.cast(obj);
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
