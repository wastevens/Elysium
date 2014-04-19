package com.dstevens.characters;

import static com.dstevens.collections.Sets.set;

import java.util.*;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.characters.attributes.*;
import com.dstevens.characters.backgrounds.CharacterBackground;
import com.dstevens.characters.power.CharacterPower;
import com.dstevens.characters.skills.CharacterSkill;
import com.dstevens.persistence.auditing.Auditable;
import com.dstevens.players.*;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="PlayerCharacter")
public class PlayerCharacter implements Auditable<PlayerCharacter>, Comparable<PlayerCharacter> {

    @Id
    private final String id;
    
    @Column(name="deleted_at")
    private final Date deleteTimestamp;
    
    @OneToOne
    @JoinTable(name="PlayerPlayerCharacters",
               joinColumns = @JoinColumn(name="character_id"),
               inverseJoinColumns = @JoinColumn(name="player_id"))
    private Player player;
    
    @OneToOne
    @JoinTable(name="TroupePlayerCharacters",
               joinColumns = @JoinColumn(name="character_id"),
               inverseJoinColumns = @JoinColumn(name="troupe_id"))
    private Troupe troupe;
    
    @Column(name="name")
    private String name;
    
    @OneToOne(cascade={CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private PhysicalAttribute physicalAttribute;
    
    @OneToOne(cascade={CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private MentalAttribute mentalAttribute;
    
    @OneToOne(cascade={CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private SocialAttribute socialAttribute;
    
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="character_id", referencedColumnName="id")
    private final Set<CharacterSkill> skills;
    
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="character_id", referencedColumnName="id")
    private final Set<CharacterBackground> backgrounds;

    @ElementCollection
    @CollectionTable(
            name="CharacterPowers",
            joinColumns=@JoinColumn(name="character_id")
            )
    private final Set<CharacterPower> powers;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private PlayerCharacter() {
        this(null, null, null, null, null, null, null, null, null, null, null);
    }
    
    PlayerCharacter(String id, Troupe troupe, Player player, String name) {
        this(id, player, troupe, name, new PhysicalAttribute(id), new MentalAttribute(id), new SocialAttribute(id), set(), set(), set(), null);
    }
    
    private PlayerCharacter(String id, Player player, Troupe troupe, String name, 
                            PhysicalAttribute physicalAttribute, MentalAttribute mentalAttribute, SocialAttribute socialAttribute, 
                            Set<CharacterSkill> skills, Set<CharacterBackground> backgrounds, Set<CharacterPower> powers, 
                            Date deleteTimestamp) {
        this.id = id;
        this.player = player;
        this.troupe = troupe;
        this.name = name;
        this.physicalAttribute = physicalAttribute;
        this.mentalAttribute = mentalAttribute;
        this.socialAttribute = socialAttribute;
        this.skills = skills;
        this.backgrounds = backgrounds;
        this.powers = powers;
        this.deleteTimestamp = deleteTimestamp;
    }
    
    public PlayerCharacter withName(String name) {
        this.name = name;
        return this;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public PhysicalAttribute getPhysicalAttribute() {
        return physicalAttribute;
    }
    
    public PlayerCharacter withPhysicalAttribute(PhysicalAttribute physicalAttribute) {
        this.physicalAttribute = physicalAttribute;
        return this;
    }
    
    public MentalAttribute getMentalAttribute() {
        return mentalAttribute;
    }
    
    public PlayerCharacter withMentalAttribute(MentalAttribute mentalAttribute) {
        this.mentalAttribute = mentalAttribute;
        return this;
    }
    
    public SocialAttribute getSocialAttribute() {
        return socialAttribute;
    }
    
    public PlayerCharacter withSocialAttribute(SocialAttribute socialAttribute) {
        this.socialAttribute = socialAttribute;
        return this;
    }

    public Set<CharacterSkill> getSkills() {
        return skills;
    }
    
    public PlayerCharacter withSkill(CharacterSkill skill) {
        this.skills.add(skill);
        return this;
    }
    
    public PlayerCharacter withoutSkill(CharacterSkill skill) {
        this.skills.remove(skill);
        return this;
    }

    public Set<CharacterBackground> getBackgrounds() {
        return backgrounds;
    }
    
    public PlayerCharacter withBackground(CharacterBackground background) {
        this.backgrounds.add(background);
        return this;
    }
    
    public PlayerCharacter withoutBackground(CharacterBackground background) {
        this.backgrounds.remove(background);
        return this;
    }
    
    public Set<CharacterPower> getPowers() {
        return powers;
    }
    
    public PlayerCharacter withPower(CharacterPower power) {
        this.powers.add(power);
        return this;
    }
    
    public PlayerCharacter withoutPower(CharacterPower power) {
        this.powers.remove(power);
        return this;
    }
    
    public PlayerCharacter belongingToPlayer(Player player) {
        this.player = player;
        return this;
    }
    
    public PlayerCharacter removePlayer() {
        this.player = null;
        return this;
    }
    
    public Player getPlayer() {
        return player;
    }

    public PlayerCharacter inTrouope(Troupe troupe) {
        this.troupe = troupe;
        return this;
    }
    
    public PlayerCharacter leaveTrouope() {
        this.troupe = null;
        return this;
    }
    
    public Troupe getTroupe() {
        return troupe;
    }
    
    @Override
    public PlayerCharacter delete(Date timestamp) {
        return new PlayerCharacter(id, player, troupe, name, physicalAttribute, mentalAttribute, socialAttribute, skills, backgrounds, powers, timestamp);
    }

    @Override
    public PlayerCharacter undelete() {
        return new PlayerCharacter(id, player, troupe, name, physicalAttribute, mentalAttribute, socialAttribute, skills, backgrounds, powers, null);
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
