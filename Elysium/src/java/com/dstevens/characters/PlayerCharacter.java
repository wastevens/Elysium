package com.dstevens.characters;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.status.ApprovalStatus;
import com.dstevens.characters.status.PlayerStatusChange;
import com.dstevens.characters.traits.attributes.focuses.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.SocialAttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.CharacterBackground;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.distinctions.flaws.CharacterFlaw;
import com.dstevens.characters.traits.distinctions.merits.CharacterMerit;
import com.dstevens.characters.traits.experience.ExperienceAward;
import com.dstevens.characters.traits.powers.PowerType;
import com.dstevens.characters.traits.powers.disciplines.CharacterDiscipline;
import com.dstevens.characters.traits.powers.disciplines.Discipline;
import com.dstevens.characters.traits.powers.disciplines.ElderPower;
import com.dstevens.characters.traits.powers.disciplines.Technique;
import com.dstevens.characters.traits.powers.magic.necromancy.NecromanticRitual;
import com.dstevens.characters.traits.powers.magic.thaumaturgy.ThaumaturgicalRitual;
import com.dstevens.characters.traits.skills.CharacterSkill;
import com.dstevens.characters.traits.status.CharacterStatus;
import com.dstevens.players.Setting;
import com.dstevens.utilities.ObjectExtensions;

import static com.dstevens.collections.Lists.list;
import static com.dstevens.collections.Lists.listFrom;

import static com.dstevens.collections.Sets.set;

@SuppressWarnings("deprecation")
@Entity
@Table(name="PlayerCharacter")
public class PlayerCharacter implements Comparable<PlayerCharacter> {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "playerCharacter", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
    
    @Column(name="name")
    private final String name;
    
    @ElementCollection
    @OrderColumn(name="order_by")
    @ForeignKey(name="PlayerCharacter_PlayerStatusChanges_FK")
    private final List<PlayerStatusChange> playerStatusChanges;
    
    @Column(name="approvalStatus")
    private ApprovalStatus approvalStatus;
    
    @Column(name="setting")
    private final Setting setting;
    
    @Column(name="clan")
    private Clan clan;
    
    @Column(name="bloodline")
    private Bloodline bloodline;
    
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
    @OrderColumn(name="order_by")
    @ForeignKey(name="PlayerCharacter_InClanDisciplines_FK")
    private final List<Discipline> inClanDisciplines;

    @ElementCollection
    @ForeignKey(name="PlayerCharacter_ElderPowers_FK")
    private final Set<ElderPower> elderPowers;
    
    @ElementCollection
    @ForeignKey(name="PlayerCharacter_Techniques_FK")
    private final Set<Technique> techniques;
    
    @Column(name="primary_thaumaturgical_path")
    private Discipline primaryThaumaturgicalPath;
    
    @ElementCollection
    @ForeignKey(name="PlayerCharacter_CharacterThaumaturgicalRituals_FK")
    private final Set<ThaumaturgicalRitual> thaumaturgicalRituals;
    
    @Column(name="primary_necromantic_path")
    private Discipline primaryNecromanticPath;
    
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
    @JoinTable(name="PlayerCharacter_RequestedTraitChanges", uniqueConstraints={@UniqueConstraint(columnNames={"requesedTraitChanges_id"}, name="PlayerCharacter_RequestedTraitChanges_UC")})
    @ForeignKey(name="PlayerCharacter_RequestedTraitChanges_FK", inverseName="RequestedTraitChanges_PlayerCharacter_FK")
    private final List<TraitChange> requesedTraitChanges;
    
    @OneToMany(cascade={CascadeType.ALL})
    @OrderColumn(name="order_by")
    @JoinTable(name="PlayerCharacter_AppliedTraitChanges", uniqueConstraints={@UniqueConstraint(columnNames={"appliedTraitChanges_id"}, name="PlayerCharacter_AppliedTraitChanges_UC")})
    @ForeignKey(name="PlayerCharacter_AppliedTraitChanges_FK", inverseName="AppliedTraitChanges_PlayerCharacter_FK")
    private final List<TraitChange> appliedTraitChanges;
    
    @OneToMany(cascade={CascadeType.ALL})
    @JoinTable(uniqueConstraints={@UniqueConstraint(columnNames={"experienceAwards_id"}, name="PlayerCharacter_ExperienceAwards_UC")})
    @ForeignKey(name="PlayerCharacter_ExperienceAwards_FK", inverseName="ExperienceAwards_PlayerCharacter_FK")
    private final List<ExperienceAward> experienceAwards;
    
    @Column(name="baseXp")
    private int baseXp;
    
    @Column(name="groundXp")
    private int groundXp;
    
    @Column(name="awardedXp")
    private int awardedXp;
    
    @Column(name="pendingSpentXp")
    private int xpRequestedSpent;
    
    @Column(name="appliedSpentXp")
    private int xpAppliedSpent;
    
    //Hibernate only
    @Deprecated
    public PlayerCharacter() {
        this(null, null, null, null, null, null, null, 0, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
    
    PlayerCharacter(Integer id, String name, Setting setting) {
        this(id, name, list(), null, setting, null, null, 0, 0, 0,
             set(), set(), set(),
             set(), set(),
             list(), set(), 
             set(), set(), null,
             set(), null, set(),
             set(), set(), set(),
             list(), list(), list());
    }
    
    private PlayerCharacter(Integer id, String name, List<PlayerStatusChange> activityStatusChanges, ApprovalStatus approvalStatus, 
    		                Setting setting, Clan clan, Bloodline bloodline, 
                            int physicalAttribute, int mentalAttribute, int socialAttribute, 
                            Set<PhysicalAttributeFocus> physicalAttrbuteFocuses, Set<MentalAttributeFocus> mentalAttrbuteFocuses,  Set<SocialAttributeFocus> socialAttrbuteFocuses,   
                            Set<CharacterSkill> skills, Set<CharacterBackground> backgrounds, 
                            List<Discipline> inClanDisciplines, Set<CharacterDiscipline> disciplines, 
                            Set<ElderPower> elderPowers, Set<Technique> techniques, Discipline primaryThaumaturgicalPath,
                            Set<ThaumaturgicalRitual> thaumaturgicalRituals, Discipline primaryNecromanticPath, Set<NecromanticRitual> necromanticRituals, 
                            Set<CharacterMerit> merits, Set<CharacterFlaw> flaws, Set<CharacterStatus> status,
                            List<TraitChange> traitChanges, List<TraitChange> appliedTraitChanges, List<ExperienceAward> experienceAwards) {
        this.id = id;
        this.name = name;
        this.playerStatusChanges = activityStatusChanges;
		this.setting = setting;
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
        this.disciplines = disciplines;
        this.elderPowers = elderPowers;
        this.techniques = techniques;
        this.primaryThaumaturgicalPath = primaryThaumaturgicalPath;
        this.thaumaturgicalRituals = thaumaturgicalRituals;
        this.primaryNecromanticPath = primaryNecromanticPath;
        this.necromanticRituals = necromanticRituals;
        this.merits = merits;
        this.flaws = flaws;
		this.status = status;
        this.requesedTraitChanges = traitChanges;
		this.appliedTraitChanges = appliedTraitChanges;
		this.experienceAwards = experienceAwards;
    }
    
    public Integer getId() {
        return id;
    }
    
    public PlayerCharacter withName(String name) {
        return new PlayerCharacter(id, name, playerStatusChanges, approvalStatus,  
        		                   setting, clan, bloodline, physicalAttribute, mentalAttribute, socialAttribute,
                                   physicalAttributeFocuses, mentalAttrbuteFocuses, socialAttributeFocuses,
                                   skills, backgrounds, 
                                   inClanDisciplines, disciplines, 
                                   elderPowers, techniques, primaryThaumaturgicalPath, 
                                   thaumaturgicalRituals, primaryNecromanticPath, necromanticRituals,
                                   merits, flaws, status,
                                   requesedTraitChanges, appliedTraitChanges, experienceAwards);
    }
    
    public String getName() {
        return name;
    }
    
    public PlayerStatusChange getCurrentStatus() {
    	return playerStatusChanges.get(0);
    }
    
    public List<PlayerStatusChange> getActivityStatusChangeHistory() {
    	return playerStatusChanges;
    }
    
    public PlayerCharacter changeActivityStatus(PlayerStatusChange activityStatusChange) {
    	this.playerStatusChanges.add(0, activityStatusChange);
    	return this;
    }
    
    public ApprovalStatus getApprovalStatus() {
    	return approvalStatus;
    }
    
    public boolean isApproved() {
    	return approvalStatus.equals(ApprovalStatus.APPROVED);
    }
    
    public boolean hasRequestedApproval() {
    	return approvalStatus.equals(ApprovalStatus.APPROVAL_REQUESTED);
    }
    
    public PlayerCharacter approve() {
    	this.approvalStatus = ApprovalStatus.APPROVED;
    	return this;
    }
    
    public PlayerCharacter requestApproval() {
    	this.approvalStatus = ApprovalStatus.APPROVAL_REQUESTED;
    	return this;
    }
    
    public PlayerCharacter beginCreation() {
    	this.approvalStatus = ApprovalStatus.IN_CREATION;
    	return this;
    }
    
    public PlayerCharacter withClan(Clan clan) {
    	this.clan = clan;
    	return this;
    }
    
    public Clan getClan() {
        return clan;
    }
    
    public PlayerCharacter withBloodline(Bloodline bloodline) {
    	this.bloodline = bloodline;
    	return this;
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
    
    public List<Discipline> getInClanDisciplines() {
        return inClanDisciplines;
    }
    
    public PlayerCharacter withInClanDiscipline(Discipline power) {
    	this.inClanDisciplines.add(power);
        return this;
    }
    
    public PlayerCharacter withoutInClanDiscipline(Discipline power) {
    	this.inClanDisciplines.remove(power);
    	return this;
    }
    
    public Set<CharacterDiscipline> getDisciplines() {
        return this.disciplines;
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
    
    public Set<CharacterDiscipline> getThaumaturgicalPaths() {
    	return disciplines.stream().filter((CharacterDiscipline t) -> t.trait().powerType.equals(PowerType.THAUMATURGY)).collect(Collectors.toSet());
    }
    
    public Discipline getPrimaryThaumaturgicalPath() {
        return primaryThaumaturgicalPath;
    }
    
    public PlayerCharacter setPrimaryThaumaturgicalPath(Discipline path) {
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
    
    public Set<CharacterDiscipline> getNecromanticPaths() {
        return disciplines.stream().filter((CharacterDiscipline t) -> t.trait().powerType.equals(PowerType.NECROMANCY)).collect(Collectors.toSet());
    }
    
    public Discipline getPrimaryNecromanticPath() {
        return primaryNecromanticPath;
    }
    
    public PlayerCharacter setPrimaryNecromanticPath(Discipline path) {
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
    
	public Setting getSetting() {
		return setting;
	}
	
    public PlayerCharacter withBaseXp(int xp) {
    	this.baseXp = xp;
    	return this;
    }
    
    public int getBaseXp() {
    	return baseXp;
    }
    
    public PlayerCharacter withGroundXp(int xp) {
    	this.groundXp = xp;
    	return this;
    }
    
    public int getGroundXp() {
    	return groundXp;
    }
    
    public int getAwardedXp() {
    	return awardedXp;
    }
    
    public int getXpGained() {
        return baseXp + groundXp + awardedXp;
    }
    
    public int getAppliedXpSpent() {
    	return xpAppliedSpent;
    }
    
    public int getRequestedXpSpent() {
    	return xpRequestedSpent;
    }
    
    public PlayerCharacter requestXpSpend(int xp) {
    	this.xpRequestedSpent += xp;
    	return this;
    }
    
    public PlayerCharacter applyXpSpend(int xp) {
    	this.xpRequestedSpent -= xp;
    	this.xpAppliedSpent += xp;
    	return this;
    }
    
    public List<ExperienceAward> getExperienceAwards() {
        return experienceAwards;
    }
    
    public PlayerCharacter addExperienceAward(ExperienceAward award) {
    	this.awardedXp += award.experience();
        this.experienceAwards.add(award);
        return this;
    }
    
    public List<TraitChange> getRequestedTraitChanges() {
        return listFrom(requesedTraitChanges);
    }
    
    public List<TraitChange> getAppliedTraitChanges() {
    	return listFrom(appliedTraitChanges);
    }
    
    public PlayerCharacter request(TraitChange traitChange) {
        this.requesedTraitChanges.add(traitChange);
        traitChange.cost().ifPresent((Integer xp) -> this.xpRequestedSpent += xp);
        return this;
    }
    
    public PlayerCharacter apply(TraitChange traitChange) {
    	this.requesedTraitChanges.remove(traitChange);
    	this.appliedTraitChanges.add(traitChange);
    	
    	traitChange.cost().ifPresent((Integer xp) -> {
    		this.xpRequestedSpent -= xp;
    		this.xpAppliedSpent += xp;
    	});
    	
        traitChange.apply(this);
        return this;
    }
	
    @Override
    public boolean equals(Object obj) {
    	if(this.id == null) {
    		return false;
    	}
        if (obj instanceof PlayerCharacter) {
            PlayerCharacter that = PlayerCharacter.class.cast(obj);
            return this.id == that.id;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id;
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(PlayerCharacter that) {
        Function<PlayerCharacter, String> byName = ((PlayerCharacter p) -> p.name);
        return Comparator.comparing(byName).compare(this, that);
    }
}
