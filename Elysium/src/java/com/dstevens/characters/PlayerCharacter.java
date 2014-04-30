package com.dstevens.characters;

import static com.dstevens.collections.Lists.list;
import static com.dstevens.collections.Sets.set;

import java.util.*;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.characters.attributes.*;
import com.dstevens.characters.backgrounds.CharacterBackground;
import com.dstevens.characters.changes.*;
import com.dstevens.characters.clans.*;
import com.dstevens.characters.merits.*;
import com.dstevens.characters.powers.*;
import com.dstevens.characters.powers.magics.*;
import com.dstevens.characters.skills.CharacterSkill;
import com.dstevens.persistence.auditing.Auditable;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="PlayerCharacter")
public class PlayerCharacter implements Auditable<PlayerCharacter>, Comparable<PlayerCharacter> {

    @Id
    private final String id;
    
    @Column(name="deleted_at")
    private final Date deleteTimestamp;
    
    @Column(name="xp")
    private int xp;
    
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
    private final Set<CharacterSkill> skills;
    
    @OneToMany(cascade={CascadeType.ALL})
    private final Set<CharacterBackground> backgrounds;

    @ElementCollection
    private final Set<CharacterDiscipline> disciplines;
    
    @ElementCollection
    private final Set<Discipline> inClanDisciplines;

    @ElementCollection
    private final Set<Thaumaturgy> inClanThaumaturgicalPaths;
    
    @ElementCollection
    private final Set<Necromancy> inClanNecromanticPaths;
    
    @ElementCollection
    private final Set<ElderPower> elderPowers;
    
    @ElementCollection
    private final Set<Technique> techniques;
    
    @ElementCollection
    private final Set<CharacterThaumaturgy> thaumaturgicalPaths;
    
    @Column(name="primary_thaumaturgical_path")
    private Thaumaturgy primaryThaumaturgicalPath;
    
    @ElementCollection
    private final Set<ThaumaturgicalRitual> thaumaturgicalRituals;
    
    @ElementCollection
    private final Set<CharacterNecromancy> necromanticPaths;
    
    @Column(name="primary_necromantic_path")
    private Necromancy primaryNecromanticPath;
    
    @ElementCollection
    private final Set<NecromanticRitual> necromanticRituals;
    
    @ElementCollection
    private final Set<CharacterMerit> merits;
    
    @ElementCollection
    private final Set<CharacterFlaw> flaws;
    
    @OneToMany(cascade={CascadeType.ALL})
    @OrderColumn(name="order_by")
    private final List<SetTrait> traitChangeEvents;
    
    //Hibernate only
    @Deprecated
    public PlayerCharacter() {
        this(null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
    
    PlayerCharacter(String id, String name) {
        this(id, 0, name, null, null, new PhysicalAttribute(id), new MentalAttribute(id), 
             new SocialAttribute(id), set(), set(),
             set(), set(), 
             set(), set(), set(),
             set(), set(), null,
             set(), set(), null,
             set(), set(), set(),
             list(), null);
    }
    
    private PlayerCharacter(String id, int xp, String name, Clan clan, Bloodline bloodline, PhysicalAttribute physicalAttribute, MentalAttribute mentalAttribute,
                            SocialAttribute socialAttribute, Set<CharacterSkill> skills, Set<CharacterBackground> backgrounds, 
                            Set<Discipline> inClanDisciplines, Set<Thaumaturgy> inClanThaumaturgicalPaths, 
                            Set<Necromancy> inClanNecromanticPaths, Set<CharacterDiscipline> disciplines, Set<ElderPower> elderPowers,
                            Set<Technique> techniques, Set<CharacterThaumaturgy> thaumaturgicalPaths, Thaumaturgy primaryThaumaturgicalPath, 
                            Set<ThaumaturgicalRitual> thaumaturgicalRituals, Set<CharacterNecromancy> necromanticPaths, Necromancy primaryNecromanticPath,
                            Set<NecromanticRitual> necromanticRituals, Set<CharacterMerit> merits, Set<CharacterFlaw> flaws,
                            List<SetTrait> traitChangeEvents, Date deleteTimestamp) {
        this.id = id;
        this.xp = xp;
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
        this.merits = merits;
        this.flaws = flaws;
        this.traitChangeEvents = traitChangeEvents;
        this.deleteTimestamp = deleteTimestamp;
    }
    
    public String getId() {
        return id;
    }
    
    public PlayerCharacter withName(String name) {
        return new PlayerCharacter(id, xp, name, clan, bloodline, physicalAttribute, mentalAttribute, 
                                   socialAttribute, skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, 
                                   inClanNecromanticPaths, disciplines, elderPowers, 
                                   techniques, thaumaturgicalPaths, primaryThaumaturgicalPath,
                                   thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath,
                                   necromanticRituals, merits, flaws,
                                   traitChangeEvents, deleteTimestamp);
    }
    
    public String getName() {
        return name;
    }
    
    public PlayerCharacter ofClan(Clan clan) {
        return new PlayerCharacter(id, xp, name, clan, bloodline, physicalAttribute, mentalAttribute, 
                socialAttribute, skills, backgrounds, 
                inClanDisciplines, inClanThaumaturgicalPaths, 
                inClanNecromanticPaths, disciplines, elderPowers, 
                techniques, thaumaturgicalPaths, primaryThaumaturgicalPath,
                thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath,
                necromanticRituals, merits, flaws,
                traitChangeEvents, deleteTimestamp);
    }
    
    public Clan getClan() {
        return clan;
    }
    
    public PlayerCharacter ofBloodline(Bloodline bloodline) {
        return new PlayerCharacter(id, xp, name, clan, bloodline, physicalAttribute, mentalAttribute, 
                socialAttribute, skills, backgrounds, 
                inClanDisciplines, inClanThaumaturgicalPaths, 
                inClanNecromanticPaths, disciplines, elderPowers, 
                techniques, thaumaturgicalPaths, primaryThaumaturgicalPath,
                thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath,
                necromanticRituals, merits, flaws,
                traitChangeEvents, deleteTimestamp);
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
    

    public PlayerCharacter withPhysicalAttribute(int rating, PhysicalAttribute.Focus focus) {
        return new PlayerCharacter(id, rating, name, clan, bloodline, new PhysicalAttribute(getId()).withRating(rating).withFocus(focus), mentalAttribute, socialAttribute, skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, inClanNecromanticPaths, disciplines, elderPowers, techniques, 
                                   thaumaturgicalPaths, primaryThaumaturgicalPath, thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath, 
                                   necromanticRituals, merits, flaws, traitChangeEvents, deleteTimestamp);
    }
    
    public MentalAttribute getMentalAttribute() {
        return mentalAttribute;
    }
    
    public PlayerCharacter withMentalAttribute(MentalAttribute mentalAttribute) {
        this.mentalAttribute = mentalAttribute;
        return this;
    }
    
    public PlayerCharacter withMentalAttribute(int rating, MentalAttribute.Focus focus) {
        return new PlayerCharacter(id, rating, name, clan, bloodline, physicalAttribute, new MentalAttribute(getId()).withRating(rating).withFocus(focus), socialAttribute, skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, inClanNecromanticPaths, disciplines, elderPowers, techniques, 
                                   thaumaturgicalPaths, primaryThaumaturgicalPath, thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath, 
                                   necromanticRituals, merits, flaws, traitChangeEvents, deleteTimestamp);
    }
    
    public SocialAttribute getSocialAttribute() {
        return socialAttribute;
    }
    
    public PlayerCharacter withSocialAttribute(SocialAttribute socialAttribute) {
        this.socialAttribute = socialAttribute;
        return this;
    }
    
    public PlayerCharacter withSocialAttribute(int rating, SocialAttribute.Focus focus) {
        return new PlayerCharacter(id, rating, name, clan, bloodline, physicalAttribute, mentalAttribute, new SocialAttribute(getId()).withRating(rating).withFocus(focus), skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, inClanNecromanticPaths, disciplines, elderPowers, techniques, 
                                   thaumaturgicalPaths, primaryThaumaturgicalPath, thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath, 
                                   necromanticRituals, merits, flaws, traitChangeEvents, deleteTimestamp);
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
    
    public Set<CharacterMerit> getMerits() {
        return merits;
    }
    
    public PlayerCharacter withMerit(CharacterMerit merit) {
        this.merits.add(merit);
        return this;
    }
    
    public PlayerCharacter withoutMerit(CharacterMerit merit) {
        this.merits.remove(merit);
        return this;
    }
    
    public Set<CharacterFlaw> getFlaws() {
        return flaws;
    }
    
    public PlayerCharacter withFlaw(CharacterFlaw flaw) {
        this.flaws.add(flaw);
        return this;
    }
    
    public PlayerCharacter withoutFlaw(CharacterFlaw flaw) {
        this.flaws.remove(flaw);
        return this;
    }
    
    @Override
    public PlayerCharacter delete(Date timestamp) {
        return new PlayerCharacter(id, xp, name, clan, bloodline, physicalAttribute, mentalAttribute, 
                                   socialAttribute, skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, 
                                   inClanNecromanticPaths, disciplines, elderPowers, 
                                   techniques, thaumaturgicalPaths, primaryThaumaturgicalPath,
                                   thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath,
                                   necromanticRituals, merits, flaws,
                                   traitChangeEvents, timestamp);
    }

    @Override
    public PlayerCharacter undelete() {
        return new PlayerCharacter(id, xp, name, clan, bloodline, physicalAttribute, mentalAttribute, 
                                   socialAttribute, skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, 
                                   inClanNecromanticPaths, disciplines, elderPowers, 
                                   techniques, thaumaturgicalPaths, primaryThaumaturgicalPath,
                                   thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath,
                                   necromanticRituals, merits, flaws,
                                   traitChangeEvents, null);
    }
    
    public boolean isDeleted() {
        return deleteTimestamp != null;
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
        return id.hashCode();
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

    public int getXp() {
        return xp;
    }
    
    public PlayerCharacter setXp(int xp) {
        this.xp = xp;
        return this;
    }
    
    public List<SetTrait> getTraitChangeEvents() {
        return traitChangeEvents;
    }
    
    public PlayerCharacter withTraitChangeEvent(SetTrait event) {
        this.traitChangeEvents.add(event);
        return this;
    }
    
    public PlayerCharacter approvePendingChange(SetTrait event) {
        event.approve(this);
        return this;
    }
    
    public PlayerCharacter approvePendingChanges() {
        this.traitChangeEvents.forEach((SetTrait t) -> t.approve(this));
        return this;
    }
}
