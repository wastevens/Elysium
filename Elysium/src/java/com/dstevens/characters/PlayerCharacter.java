package com.dstevens.characters;

import static com.dstevens.collections.Lists.list;

import static com.dstevens.collections.Sets.set;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.hibernate.annotations.ForeignKey;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.attributes.focuses.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.SocialAttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.CharacterBackground;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.distinctions.flaws.CharacterFlaw;
import com.dstevens.characters.traits.distinctions.merits.CharacterMerit;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.disciplines.CharacterDiscipline;
import com.dstevens.characters.traits.powers.disciplines.Discipline;
import com.dstevens.characters.traits.powers.disciplines.ElderPower;
import com.dstevens.characters.traits.powers.disciplines.Technique;
import com.dstevens.characters.traits.powers.magic.necromancy.CharacterNecromancy;
import com.dstevens.characters.traits.powers.magic.necromancy.Necromancy;
import com.dstevens.characters.traits.powers.magic.necromancy.NecromanticRitual;
import com.dstevens.characters.traits.powers.magic.thaumaturgy.CharacterThaumaturgy;
import com.dstevens.characters.traits.powers.magic.thaumaturgy.ThaumaturgicalRitual;
import com.dstevens.characters.traits.powers.magic.thaumaturgy.Thaumaturgy;
import com.dstevens.characters.traits.skills.CharacterSkill;
import com.dstevens.characters.traits.status.CharacterStatus;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("deprecation")
@Entity
@Table(name="PlayerCharacter")
public class PlayerCharacter implements Comparable<PlayerCharacter> {

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
    
    @Column(name="physical_attribute")
    private int physicalAttribute;
    
    @Column(name="mental_attribute")
    private int mentalAttribute;

    @Column(name="social_attribute")
    private int socialAttribute;
    
    @ElementCollection
    @ForeignKey(name="PlayerCharacter_PhysicalAttributeFocuses_FK")
    private Set<PhysicalAttributeFocus> physicalAttributeFocuses;

    @ElementCollection
    @ForeignKey(name="PlayerCharacter_MentalAttributeFocuses_FK")
    private Set<MentalAttributeFocus> mentalAttrbuteFocuses;
    
    @ElementCollection
    @ForeignKey(name="PlayerCharacter_SocialAttributeFocuses_FK")
    private Set<SocialAttributeFocus> socialAttributeFocuses;
    
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="character_id", referencedColumnName="id")
    @ForeignKey(name="PlayerCharacter_Skills_FK", inverseName="none")
    private final Set<CharacterSkill> skills;
    
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="character_id", referencedColumnName="id")
    @ForeignKey(name="PlayerCharacter_Backgrounds_FK", inverseName="Backgrounds_PlayerCharacter_FK")
    private final Set<CharacterBackground> backgrounds;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="character_id", referencedColumnName="id")
    @ForeignKey(name="PlayerCharacter_Disciplines_FK", inverseName="Disciplines_PlayerCharacter_FK")
    private final Set<CharacterDiscipline> disciplines;
    
    @ElementCollection
    @ForeignKey(name="PlayerCharacter_InClanDisciplines_FK")
    private final Set<Discipline> inClanDisciplines;

    @ElementCollection
    @ForeignKey(name="PlayerCharacter_InClanThaumaturgicalPaths_FK")
    private final Set<Thaumaturgy> inClanThaumaturgicalPaths;
    
    @ElementCollection
    @ForeignKey(name="PlayerCharacter_InClanNecromanticPaths_FK")
    private final Set<Necromancy> inClanNecromanticPaths;
    
    @ElementCollection
    @ForeignKey(name="PlayerCharacter_ElderPowers_FK")
    private final Set<ElderPower> elderPowers;
    
    @ElementCollection
    @ForeignKey(name="PlayerCharacter_Techniques_FK")
    private final Set<Technique> techniques;
    
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="character_id", referencedColumnName="id")
    @ForeignKey(name="PlayerCharacter_ThaumaturgicalPaths_FK", inverseName="ThaumaturgicalPaths_PlayerCharacter_FK")
    private final Set<CharacterThaumaturgy> thaumaturgicalPaths;
    
    @Column(name="primary_thaumaturgical_path")
    private Thaumaturgy primaryThaumaturgicalPath;
    
    @ElementCollection
    @ForeignKey(name="PlayerCharacter_CharacterThaumaturgicalRituals_FK")
    private final Set<ThaumaturgicalRitual> thaumaturgicalRituals;
    
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="character_id", referencedColumnName="id")
    @ForeignKey(name="PlayerCharacter_NecromanticPaths_FK", inverseName="NecromanticPaths_PlayerCharacter_FK")
    private final Set<CharacterNecromancy> necromanticPaths;
    
    @Column(name="primary_necromantic_path")
    private Necromancy primaryNecromanticPath;
    
    @ElementCollection
    @ForeignKey(name="PlayerCharacter_CharacterNecromanticRituals_FK")
    private final Set<NecromanticRitual> necromanticRituals;
    
    @OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
    @JoinColumn(name="character_id", referencedColumnName="id")
    @ForeignKey(name="PlayerCharacter_Merits_FK")
    private final Set<CharacterMerit> merits;
    
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="character_id", referencedColumnName="id")
    @ForeignKey(name="PlayerCharacter_Flaws_FK", inverseName="Flaws_PlayerCharacter_FK")
    private final Set<CharacterFlaw> flaws;
    
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="character_id", referencedColumnName="id")
    @ForeignKey(name="PlayerCharacter_Status_FK", inverseName="Status_PlayerCharacter_FK")
    private final Set<CharacterStatus> status;
    
    @OneToMany(cascade={CascadeType.ALL})
    @OrderColumn(name="order_by")
    @JoinTable(uniqueConstraints={@UniqueConstraint(columnNames={"traitChanges_id"}, name="PlayerCharacter_TraitChanges_UC")})
    @ForeignKey(name="PlayerCharacter_TraitChanges_FK", inverseName="TraitChanges_PlayerCharacter_FK")
    private final List<TraitChange<?>> traitChanges;
    
    //Hibernate only
    @Deprecated
    public PlayerCharacter() {
        this(null, 0, null, null, null, 0, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
    
    PlayerCharacter(String id, String name) {
        this(id, 0, name, null, null, 0, 0, 0,
             set(), set(), set(),
             set(), set(),
             set(), set(), 
             set(), set(), set(),
             set(), set(), null,
             set(), set(), null,
             set(), set(), set(), set(),
             list(), null);
    }
    
    private PlayerCharacter(String id, int xp, String name, Clan clan, Bloodline bloodline, 
                            int physicalAttribute, int mentalAttribute, int socialAttribute, 
                            Set<PhysicalAttributeFocus> physicalAttrbuteFocuses, Set<MentalAttributeFocus> mentalAttrbuteFocuses,  Set<SocialAttributeFocus> socialAttrbuteFocuses,   
                            Set<CharacterSkill> skills, Set<CharacterBackground> backgrounds, 
                            Set<Discipline> inClanDisciplines, Set<Thaumaturgy> inClanThaumaturgicalPaths, 
                            Set<Necromancy> inClanNecromanticPaths, Set<CharacterDiscipline> disciplines, Set<ElderPower> elderPowers,
                            Set<Technique> techniques, Set<CharacterThaumaturgy> thaumaturgicalPaths, Thaumaturgy primaryThaumaturgicalPath, 
                            Set<ThaumaturgicalRitual> thaumaturgicalRituals, Set<CharacterNecromancy> necromanticPaths, Necromancy primaryNecromanticPath,
                            Set<NecromanticRitual> necromanticRituals, Set<CharacterMerit> merits, Set<CharacterFlaw> flaws, Set<CharacterStatus> status,
                            List<TraitChange<?>> traitChanges, Date deleteTimestamp) {
        this.id = id;
        this.xp = xp;
        this.name = name;
        this.clan = clan;
        this.bloodline = bloodline;
        this.physicalAttribute = physicalAttribute;
        this.mentalAttribute = mentalAttribute;
        this.socialAttribute = socialAttribute;
        this.physicalAttributeFocuses = physicalAttrbuteFocuses;
        this.mentalAttrbuteFocuses = mentalAttrbuteFocuses;
        this.socialAttributeFocuses = socialAttrbuteFocuses;
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
		this.status = status;
        this.traitChanges = traitChanges;
        this.deleteTimestamp = deleteTimestamp;
    }
    
    public String getId() {
        return id;
    }
    
    public PlayerCharacter withName(String name) {
        return new PlayerCharacter(id, xp, name, clan, bloodline, physicalAttribute, mentalAttribute, socialAttribute,
                                   physicalAttributeFocuses, mentalAttrbuteFocuses, socialAttributeFocuses,
                                   skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, 
                                   inClanNecromanticPaths, disciplines, elderPowers, 
                                   techniques, thaumaturgicalPaths, primaryThaumaturgicalPath,
                                   thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath,
                                   necromanticRituals, merits, flaws, status,
                                   traitChanges, deleteTimestamp);
    }
    
    public String getName() {
        return name;
    }
    
    public PlayerCharacter ofClan(Clan clan) {
        return new PlayerCharacter(id, xp, name, clan, bloodline, physicalAttribute, mentalAttribute, socialAttribute,
                physicalAttributeFocuses, mentalAttrbuteFocuses, socialAttributeFocuses,
                skills, backgrounds, 
                inClanDisciplines, inClanThaumaturgicalPaths, 
                inClanNecromanticPaths, disciplines, elderPowers, 
                techniques, thaumaturgicalPaths, primaryThaumaturgicalPath,
                thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath,
                necromanticRituals, merits, flaws, status,
                traitChanges, deleteTimestamp);
    }
    
    public Clan getClan() {
        return clan;
    }
    
    public PlayerCharacter ofBloodline(Bloodline bloodline) {
        return new PlayerCharacter(id, xp, name, clan, bloodline, physicalAttribute, mentalAttribute, socialAttribute,
                physicalAttributeFocuses, mentalAttrbuteFocuses, socialAttributeFocuses, 
                skills, backgrounds, 
                inClanDisciplines, inClanThaumaturgicalPaths, 
                inClanNecromanticPaths, disciplines, elderPowers, 
                techniques, thaumaturgicalPaths, primaryThaumaturgicalPath,
                thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath,
                necromanticRituals, merits, flaws, status,
                traitChanges, deleteTimestamp);
    }
    
    public Bloodline getBloodline() {
        return bloodline;
    }

    public int getPhysicalAttribute() {
        return physicalAttribute;
    }
    
    public int getMentalAttribute() {
        return mentalAttribute;
    }
    
    public int getSocialAttribute() {
        return socialAttribute;
    }
    
    public Set<PhysicalAttributeFocus> getPhysicalAttributeFocuses() {
        return physicalAttributeFocuses;
    }
    
    public Set<MentalAttributeFocus> getMentalAttributeFocuses() {
        return mentalAttrbuteFocuses;
    }
    
    public Set<SocialAttributeFocus> getSocialAttributeFocuses() {
        return socialAttributeFocuses;
    }
    
    public PlayerCharacter withPhysicalAttribute(int physicalAttribute) {
        this.physicalAttribute = physicalAttribute;
        return this;
    }
    
    public PlayerCharacter withMentalAttribute(int mentalAttribute) {
        this.mentalAttribute = mentalAttribute;
        return this;
    }
    
    public PlayerCharacter withSocialAttribute(int socialAttribute) {
        this.socialAttribute = socialAttribute;
        return this;
    }
    
    public PlayerCharacter withPhysicalAttributeFocus(PhysicalAttributeFocus focus) {
        this.physicalAttributeFocuses.add(focus);
        return this;
    }
    
    public PlayerCharacter withMentalAttributeFocus(MentalAttributeFocus focus) {
        this.mentalAttrbuteFocuses.add(focus);
        return this;
    }
    
    public PlayerCharacter withSocialAttributeFocus(SocialAttributeFocus focus) {
        this.socialAttributeFocuses.add(focus);
        return this;
    }
    
    public PlayerCharacter withoutPhysicalAttributeFocus(PhysicalAttributeFocus focus) {
    	this.physicalAttributeFocuses.remove(focus);
    	return this;
    }
    
    public PlayerCharacter withoutMentalAttributeFocus(MentalAttributeFocus focus) {
    	this.mentalAttrbuteFocuses.remove(focus);
    	return this;
    }
    
    public PlayerCharacter withoutSocialAttributeFocus(SocialAttributeFocus focus) {
    	this.socialAttributeFocuses.remove(focus);
    	return this;
    }
    
    public Set<CharacterSkill> getSkills() {
        return skills;
    }
    
    public PlayerCharacter withSkill(CharacterSkill skill) {
    	this.withoutSkill(skill).skills.add(skill);
        return this;
    }

	public PlayerCharacter withoutSkill(CharacterSkill skill) {
		this.skills.removeIf(skill.matches());
        return this;
    }

	public PlayerCharacter withMerit(CharacterMerit characterMerits) {
		this.withoutMerit(characterMerits).merits.add(characterMerits);
		return this;
	}

	public PlayerCharacter withoutMerit(CharacterMerit characterMerits) {
		this.merits.removeIf(characterMerits.matches());
		return this;
	}
	
    public Set<CharacterBackground> getBackgrounds() {
        return backgrounds;
    }
    
    public Optional<Integer> getGeneration() {
    	return getBackgrounds().stream().filter((CharacterBackground t) -> t.trait().equals(Background.GENERATION)).map((CharacterBackground t) -> t.rating()).findFirst();
    }
    
    public PlayerCharacter withBackground(CharacterBackground background) {
    	this.withoutBackground(background).backgrounds.add(background);
        return this;
    }
    
    public PlayerCharacter withoutBackground(CharacterBackground background) {
    	this.backgrounds.removeIf(background.matches());
        return this;
    }
    
    public Set<Power<?>> getInClanDisciplines() {
        Set<Power<?>> powers = set();
        powers.addAll(inClanDisciplines);
        powers.addAll(inClanThaumaturgicalPaths);
        powers.addAll(inClanNecromanticPaths);
        return powers;
    }
    
    public PlayerCharacter withInClanDiscipline(Power<?> power) {
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
    
    public PlayerCharacter withoutInClanDiscipline(Power<?> power) {
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
        this.withoutDiscipline(power).disciplines.add(power);
        return this;
    }
    
    public PlayerCharacter withoutDiscipline(CharacterDiscipline power) {
    	this.disciplines.removeIf(power.matches());
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
        this.withoutThaumaturgicalPath(path).thaumaturgicalPaths.add(path);
        return this;
    }
    
    public PlayerCharacter withoutThaumaturgicalPath(CharacterThaumaturgy path) {
    	this.thaumaturgicalPaths.removeIf(path.matches());
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
        this.withoutNecromanticPath(path).necromanticPaths.add(path);
        return this;
    }
    
    public PlayerCharacter withoutNecromanticPath(CharacterNecromancy path) {
    	this.necromanticPaths.removeIf(path.matches());
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
    
    public Set<CharacterFlaw> getFlaws() {
        return flaws;
    }
    
    public PlayerCharacter withFlaw(CharacterFlaw flaw) {
    	this.withoutFlaw(flaw).flaws.add(flaw);
        return this;
    }
    
    public PlayerCharacter withoutFlaw(CharacterFlaw flaw) {
    	this.flaws.removeIf(flaw.matches());
        return this;
    }
    
    public PlayerCharacter delete(Date timestamp) {
        return new PlayerCharacter(id, xp, name, clan, bloodline, physicalAttribute, mentalAttribute, socialAttribute,
                                   physicalAttributeFocuses, mentalAttrbuteFocuses, socialAttributeFocuses, 
                                   skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, 
                                   inClanNecromanticPaths, disciplines, elderPowers, 
                                   techniques, thaumaturgicalPaths, primaryThaumaturgicalPath,
                                   thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath,
                                   necromanticRituals, merits, flaws, status,
                                   traitChanges, timestamp);
    }

    public PlayerCharacter undelete() {
        return new PlayerCharacter(id, xp, name, clan, bloodline, physicalAttribute, mentalAttribute, socialAttribute,
                                   physicalAttributeFocuses, mentalAttrbuteFocuses, socialAttributeFocuses, 
                                   skills, backgrounds, 
                                   inClanDisciplines, inClanThaumaturgicalPaths, 
                                   inClanNecromanticPaths, disciplines, elderPowers, 
                                   techniques, thaumaturgicalPaths, primaryThaumaturgicalPath,
                                   thaumaturgicalRituals, necromanticPaths, primaryNecromanticPath,
                                   necromanticRituals, merits, flaws, status,
                                   traitChanges, null);
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
    
    public List<TraitChange<?>> getTraitChanges() {
        return traitChanges;
    }
    
    public PlayerCharacter withTraitChangeEvent(TraitChange<?> event) {
        this.traitChanges.add(event);
        return this;
    }
    
    public PlayerCharacter approvePendingChange(TraitChange<?> event) {
        event.approve(this);
        return this;
    }
    
    public PlayerCharacter approvePendingChanges() {
        this.traitChanges.forEach((TraitChange<?> t) -> t.approve(this));
        return this;
    }

    public Set<CharacterStatus> getStatus() {
    	return status;
    }
    
	public PlayerCharacter withStatus(CharacterStatus characterStatus) {
		this.status.add(characterStatus);
		return this;
	}
	
	public PlayerCharacter withoutStatus(CharacterStatus characterStatus) {
		this.status.removeIf(characterStatus.matching());
		return this;
	}
}
