package com.dstevens.players;

import static com.dstevens.collections.Sets.set;
import static com.dstevens.collections.Sets.setWith;
import static com.dstevens.collections.Sets.setWithout;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;

import org.hibernate.annotations.ForeignKey;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name="Troupe")
public class Troupe implements Comparable<Troupe> {

    @Id
    private final String id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="setting")
    private Setting setting;
    
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="troupe_id", referencedColumnName="id")
    @ForeignKey(name="Troupe_PlayerCharacters_FK")
    private final Set<PlayerCharacter> characters;

    @Column(name="deleted_at")
    private final Date deleteTimestamp;
    
    //Used only for hibernate
    @SuppressWarnings("unused")
	@Deprecated
    private Troupe() {
        this(null, null, null, set(), null);
    }
    
    Troupe(String id, String name, Setting setting) {
        this(id, name, setting, set(), null);
    }
    
    private Troupe(String id, String name, Setting setting, Set<PlayerCharacter> characters, Date deleteTimestamp) {
        this.id = id;
        this.name = name;
        this.setting = setting;
        this.characters = characters;
        this.deleteTimestamp = deleteTimestamp;
    }
    
    public String getId() {
        return id;
    }

    public Troupe withName(String name) {
        this.name = name;
        return this;
    }
    
    public String getName() {
        return name;
    }

    public Troupe withSetting(Setting setting) {
        this.setting = setting;
        return this;
    }
    
    public Setting getSetting() {
        return setting;
    }

    public Troupe withCharacter(PlayerCharacter character) {
        return new Troupe(id, name, setting, setWith(characters, character), deleteTimestamp);
    }
    
    public Troupe withoutCharacter(PlayerCharacter character) {
        return new Troupe(id, name, setting, setWithout(characters, character), deleteTimestamp);
    }
    
    public Set<PlayerCharacter> getCharacters() {
        return characters;
    }

    public Troupe delete(Date deleteTimestamp) {
        return new Troupe(id, name, setting, characters, deleteTimestamp);
    }
    
    public Troupe undelete() {
        return new Troupe(id, name, setting, characters, null);
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
