package com.dstevens.players;

import java.util.List;
import javax.persistence.*;

import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="troupe")
public class Troupe {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name") 
    private String name;
    
    @Column(name="setting") 
    private Setting setting;
    
    private List<Player> players;
    
    //Used only by Hibernate
    @SuppressWarnings("unused")
    private Troupe() {
    }
    
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
