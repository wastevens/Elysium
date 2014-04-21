package com.dstevens.characters;

import static com.dstevens.collections.Sets.set;

import java.util.*;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.characters.attributes.*;
import com.dstevens.characters.backgrounds.CharacterBackground;
import com.dstevens.characters.clans.*;
import com.dstevens.characters.powers.*;
import com.dstevens.characters.powers.magics.*;
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
    private final String name;
    
    @Column(name="clan")
    private final Clan clan;
    
    @Column(name="bloodline")
    private final Bloodline bloodline;
    
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
            name="CharacterDisciplines",
            joinColumns=@JoinColumn(name="character_id")
            )
    private final Set<CharacterDiscipline> disciplines;
    
    @ElementCollection
    @CollectionTable(name="InClanDisciplines")
    @Column(name="in_clan_discipline")
    private final Set<Discipline> inClanDisciplines;

    @ElementCollection
    @CollectionTable(name="InClanThaumaturgy")
    @Column(name="in_clan_thaumaturgy")
    private final Set<Thaumaturgy> inClanThaumaturgicalPaths;
    
    @ElementCollection
    @CollectionTable(name="InClanNecromancy")
    @Column(name="in_clan_necromancy")
    private final Set<Necromancy> inClanNecromanticPaths;
    
    @ElementCollection
    @CollectionTable(name="CharacterElderPowers")
    @Column(name="elder_power")
    private final Set<ElderPower> elderPowers;
    
    @ElementCollection
    @CollectionTable(name="CharacterTechniques")
    @Column(name="technique")
    private final Set<Technique> techniques;
    
    @ElementCollection
    @CollectionTable(
            name="CharacterThamaturgicalPaths",
            joinColumns=@JoinColumn(name="character_id")
            )
    private final Set<CharacterThaumaturgy> thaumaturgicalPaths;
    
    @Column(name="primary_thaumaturgical_path")
    private Thaumaturgy primaryThaumaturgicalPath;
    
    @ElementCollection
    @CollectionTable(name="CharacterThamaturgicalRituals")
    @Column(name="thaumaturgical_ritual")
    private final Set<ThaumaturgicalRitual> thaumaturgicalRituals;
    
    @ElementCollection
    @CollectionTable(
            name="CharacterNecromanticPaths",
            joinColumns=@JoinColumn(name="character_id")
            )
    private final Set<CharacterNecromancy> necromanticPaths;
    
    @Column(name="primary_necromantic_path")
    private Necromancy primaryNecromanticPath;
    
    @ElementCollection
    @CollectionTable(name="CharacterThamaturgicalRituals")
    @Column(name="neromantic_ritual")
    private final Set<NecromanticRitual> necromanticRituals;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private PlayerCharacter() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
    
    PlayerCharacter(String id, Troupe troupe, Player player, String name) {
        this(id, player, troupe, name, null, null, 
             new PhysicalAttribute(id), new MentalAttribute(id), new SocialAttribute(id),
             set(), set(), 
             set(), set(), set(),
             set(), set(), set(),
             set(), null, set(),
             set(), null, set(),
             null);
    }
    
    private PlayerCharacter(String id, Player player, Troupe troupe, String name, Clan clan, Bloodline bloodline,
                            PhysicalAttribute physicalAttribute, MentalAttribute mentalAttribute, SocialAttribute socialAttribute, 
                            Set<CharacterSkill> skills, Set<CharacterBackground> backgrounds, 
                            Set<Discipline> inClanDisciplines, Set<Thaumaturgy> inClanThaumaturgicalPaths, Set<Necromancy> inClanNecromanticPaths,
                            Set<CharacterDiscipline> disciplines, Set<ElderPower> elderPowers, Set<Technique> techniques, 
                            Set<CharacterThaumaturgy> thaumaturgicalPaths, Thaumaturgy primaryThaumaturgicalPath, Set<ThaumaturgicalRitual> thaumaturgicalRituals,
                            Set<CharacterNecromancy> necromanticPaths, Necromancy primaryNecromanticPath, Set<NecromanticRitual> necromanticRituals,
                            Date deleteTimestamp) {
        this.id = id;
        this.player = player;
        this.troupe = troupe;
        this.name = name;
        this.clan = clan;
        this.bloodline = bloodline;
        this.physicalAttribute = physicalAttribute;
        this.mentalAttribute = mentalAttribute;
        this.socialAttribute = socialAttribute;
        this.skills = skills;
        this.backgrounds = backgrounds;
        this.inClanDisciplines = inClanDisciplines;
        this.inClanThaumaturgicalPaths = inClanThaumaturgicalPaths;
        this.inClanNecromanticPaths = inClanNecromanticPaths;
        this.disciplines = disciplines;
        this.elderPowers = elderPowers;
        this.techniques = techniques;
        this.thaumaturgicalPaths = thaumaturgicalPaths;
        this.primaryThaumaturgicalPath = primaryThaumaturgicalPath;
        this.thaumaturgicalRituals = thaumaturgicalRituals;
        this.necromanticPaths = necromanticPaths;
        this.primaryNecromanticPath = primaryNecromanticPath;
        this.necromanticRituals = necromanticRituals;
        this.deleteTimestamp = deleteTimestamp;
    }
    
    public String getId() {
        return id;
    }
    
    public PlayerCharacter withName(String name) {
        return new PlayerCharacter(id, player, troupe, name, clan, bloodline, 
                                   physicalAttribute, mentalAttribute, socialAttribute, 
                                   skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, inClanNecromanticPaths, 
                                   disciplines, elderPowers, techniques,
                                   thaumaturgicalPaths, primaryThaumaturgicalPath, thaumaturgicalRituals,
                                   necromanticPaths, primaryNecromanticPath, necromanticRituals,
                                   deleteTimestamp);
    }
    
    public String getName() {
        return name;
    }
    
    public PlayerCharacter ofClan(Clan clan) {
        return new PlayerCharacter(id, player, troupe, name, clan, bloodline, 
                physicalAttribute, mentalAttribute, socialAttribute, 
                skills, backgrounds, 
                inClanDisciplines, inClanThaumaturgicalPaths, inClanNecromanticPaths, 
                disciplines, elderPowers, techniques,
                thaumaturgicalPaths, primaryThaumaturgicalPath, thaumaturgicalRituals,
                necromanticPaths, primaryNecromanticPath, necromanticRituals,
                deleteTimestamp);
    }
    
    public Clan getClan() {
        return clan;
    }
    
    public PlayerCharacter ofBloodline(Bloodline bloodline) {
        return new PlayerCharacter(id, player, troupe, name, clan, bloodline, 
                physicalAttribute, mentalAttribute, socialAttribute, 
                skills, backgrounds, 
                inClanDisciplines, inClanThaumaturgicalPaths, inClanNecromanticPaths, 
                disciplines, elderPowers, techniques,
                thaumaturgicalPaths, primaryThaumaturgicalPath, thaumaturgicalRituals,
                necromanticPaths, primaryNecromanticPath, necromanticRituals,
                deleteTimestamp);
    }
    
    public Bloodline getBloodline() {
        return bloodline;
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
    
    public Set<Power> getInClanDisciplines() {
        Set<Power> powers = set();
        powers.addAll(inClanDisciplines);
        powers.addAll(inClanThaumaturgicalPaths);
        powers.addAll(inClanNecromanticPaths);
        return powers;
    }
    
    public PlayerCharacter withInClanDisciplines(Power power) {
        if (power instanceof Discipline) {
            inClanDisciplines.add((Discipline) power);
        }
        if (power instanceof Thaumaturgy) {
            inClanThaumaturgicalPaths.add((Thaumaturgy) power);
        }
        if (power instanceof Necromancy) {
            inClanNecromanticPaths.add((Necromancy) power);
        }
        return this;
    }
    
    public PlayerCharacter withoutInClanDisciplines(Power power) {
        if (power instanceof Discipline) {
            inClanDisciplines.remove((Discipline) power);
        }
        if (power instanceof Thaumaturgy) {
            inClanThaumaturgicalPaths.remove((Thaumaturgy) power);
        }
        if (power instanceof Necromancy) {
            inClanNecromanticPaths.remove((Necromancy) power);
        }
        return this;
    }
    
    public Set<CharacterDiscipline> getDisciplines() {
        return disciplines;
    }
    
    public PlayerCharacter withDiscipline(CharacterDiscipline power) {
        this.disciplines.add(power);
        return this;
    }
    
    public PlayerCharacter withoutDiscipline(CharacterDiscipline power) {
        this.disciplines.remove(power);
        return this;
    }
    
    public Set<ElderPower> getElderPowers() {
        return elderPowers;
    }
    
    public PlayerCharacter withElderPower(ElderPower power) {
        this.elderPowers.add(power);
        return this;
    }
    
    public PlayerCharacter withoutElderPower(ElderPower power) {
        this.elderPowers.remove(power);
        return this;
    }
    
    public Set<Technique> getTechniques() {
        return techniques;
    }
    
    public PlayerCharacter withTechnique(Technique power) {
        this.techniques.add(power);
        return this;
    }
    
    public PlayerCharacter withoutTechnique(Technique power) {
        this.techniques.remove(power);
        return this;
    }
    
    public Set<CharacterThaumaturgy> getThaumaturgicalPaths() {
        return thaumaturgicalPaths;
    }
    
    public PlayerCharacter withThaumaturgicalPath(CharacterThaumaturgy path) {
        this.thaumaturgicalPaths.add(path);
        return this;
    }
    
    public PlayerCharacter withoutThaumaturgicalPath(CharacterThaumaturgy path) {
        this.thaumaturgicalPaths.remove(path);
        return this;
    }
    
    public Thaumaturgy getPrimaryThaumaturgicalPath() {
        return primaryThaumaturgicalPath;
    }
    
    public PlayerCharacter setPrimaryThaumaturgicalPath(Thaumaturgy path) {
        this.primaryThaumaturgicalPath = path;
        return this;
    }
    
    public Set<ThaumaturgicalRitual> getThaumaturgicalRituals() {
        return thaumaturgicalRituals;
    }
    
    public PlayerCharacter withThaumaturgicalRitual(ThaumaturgicalRitual ritual) {
        this.thaumaturgicalRituals.add(ritual);
        return this;
    }
    
    public PlayerCharacter withoutThaumaturgicalRitual(ThaumaturgicalRitual ritual) {
        this.thaumaturgicalRituals.remove(ritual);
        return this;
    }
    
    public Set<CharacterNecromancy> getNecromanticPaths() {
        return necromanticPaths;
    }
    
    public PlayerCharacter withNecromanticPath(CharacterNecromancy path) {
        this.necromanticPaths.add(path);
        return this;
    }
    
    public PlayerCharacter withoutNecromanticPath(CharacterNecromancy path) {
        this.necromanticPaths.remove(path);
        return this;
    }
    
    public Necromancy getPrimaryNecromanticPath() {
        return primaryNecromanticPath;
    }
    
    public PlayerCharacter setPrimaryNecromanticPath(Necromancy path) {
        this.primaryNecromanticPath = path;
        return this;
    }

    public Set<NecromanticRitual> getNecromanticRituals() {
        return necromanticRituals;
    }
    
    public PlayerCharacter withNecromanticRitual(NecromanticRitual ritual) {
        this.necromanticRituals.add(ritual);
        return this;
    }
    
    public PlayerCharacter withoutNecromanticRitual(NecromanticRitual ritual) {
        this.necromanticRituals.remove(ritual);
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
        return new PlayerCharacter(id, player, troupe, name, clan, bloodline, 
                                   physicalAttribute, mentalAttribute, socialAttribute, 
                                   skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, inClanNecromanticPaths, 
                                   disciplines, elderPowers, techniques,
                                   thaumaturgicalPaths, primaryThaumaturgicalPath, thaumaturgicalRituals,
                                   necromanticPaths, primaryNecromanticPath, necromanticRituals,
                                   timestamp);
    }

    @Override
    public PlayerCharacter undelete() {
        return new PlayerCharacter(id, player, troupe, name, clan, bloodline, 
                                   physicalAttribute, mentalAttribute, socialAttribute, 
                                   skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, inClanNecromanticPaths, 
                                   disciplines, elderPowers, techniques,
                                   thaumaturgicalPaths, primaryThaumaturgicalPath, thaumaturgicalRituals,
                                   necromanticPaths, primaryNecromanticPath, necromanticRituals,
                                   null);
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
