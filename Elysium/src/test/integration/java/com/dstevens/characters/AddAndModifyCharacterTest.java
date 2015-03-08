package com.dstevens.characters;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.StreamSupport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.status.PlayerStatus;
import com.dstevens.characters.status.PlayerStatusChange;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.characters.traits.TraitQualitiesBuilder;
import com.dstevens.characters.traits.Traits;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.SocialAttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.CharacterBackground;
import com.dstevens.characters.traits.changes.ExperienceAwardService;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeFactory;
import com.dstevens.characters.traits.changes.TraitChangeFactoryProvider;
import com.dstevens.characters.traits.distinctions.flaws.CharacterFlaw;
import com.dstevens.characters.traits.distinctions.flaws.Flaw;
import com.dstevens.characters.traits.distinctions.merits.CharacterMerit;
import com.dstevens.characters.traits.distinctions.merits.Merit;
import com.dstevens.characters.traits.powers.disciplines.CharacterDiscipline;
import com.dstevens.characters.traits.powers.disciplines.Discipline;
import com.dstevens.characters.traits.powers.disciplines.ElderPower;
import com.dstevens.characters.traits.powers.disciplines.Technique;
import com.dstevens.characters.traits.powers.magic.necromancy.NecromanticRitual;
import com.dstevens.characters.traits.powers.magic.thaumaturgy.ThaumaturgicalRitual;
import com.dstevens.characters.traits.skills.CharacterSkill;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.status.CharacterStatus;
import com.dstevens.characters.traits.status.Status;
import com.dstevens.configuration.ApplicationConfiguration;
import com.dstevens.players.Setting;
import com.dstevens.time.DateTimeUtilities;

import static com.dstevens.collections.Sets.set;

import static org.junit.Assert.assertEquals;

public class AddAndModifyCharacterTest {

    private ApplicationContext appConfig = ApplicationConfiguration.appConfig();
    
    @Before
    public void setUp() {
    	this.appConfig = ApplicationConfiguration.appConfig();
    	deleteAllNamed();
    }
    
    @After
    public void tearDown() {
    	deleteAllNamed();
    }

	private void deleteAllNamed() {
		PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
    	Iterable<PlayerCharacter> findAllNamed = characterRepository.findAllNamed("Mary Wollstonecraft");
    	StreamSupport.stream(findAllNamed.spliterator(), false).forEach((PlayerCharacter p) -> characterRepository.hardDelete(p));
	}
    
    @Test   
    public void createAndModify() {
        createMaryWollstonecraft();
        PlayerCharacter maryWollstonecraftWhenNewlyCreated = getMaryWollstonecraft();
        assertEquals(maryWollstonecraftWhenNewlyCreated.getClan(), Clan.TOREADOR);
        assertEquals(maryWollstonecraftWhenNewlyCreated.getBloodline(), Bloodline.TOREADOR);
		assertEquals(3, maryWollstonecraftWhenNewlyCreated.getPhysicalAttribute());
		assertEquals(set(PhysicalAttributeFocus.DEXTERITY), maryWollstonecraftWhenNewlyCreated.getPhysicalAttributeFocuses());
		assertEquals(5, maryWollstonecraftWhenNewlyCreated.getSocialAttribute());
		assertEquals(set(SocialAttributeFocus.MANIPULATION), maryWollstonecraftWhenNewlyCreated.getSocialAttributeFocuses());
		assertEquals(7, maryWollstonecraftWhenNewlyCreated.getMentalAttribute());
		assertEquals(set(MentalAttributeFocus.PERCEPTION), maryWollstonecraftWhenNewlyCreated.getMentalAttributeFocuses());
		assertEquals(set(new CharacterSkill(Skill.ACADEMICS, 1, null, set("Philosophy")),
				         new CharacterSkill(Skill.AWARENESS, 1, null, set()),
				         new CharacterSkill(Skill.COMPUTER, 1, null, set()),
				         new CharacterSkill(Skill.LEADERSHIP, 1, null, set()),
				         new CharacterSkill(Skill.DODGE, 2, null, set()),
				         new CharacterSkill(Skill.EMPATHY, 2, null, set()),
				         new CharacterSkill(Skill.STEALTH, 2, null, set()),
				         new CharacterSkill(Skill.SUBTERFUGE, 3, null, set()),
				         new CharacterSkill(Skill.INVESTIGATION, 3, null, set()),
				         new CharacterSkill(Skill.CRAFTS, 4, "Writing", set())), 
				     maryWollstonecraftWhenNewlyCreated.getSkills());
		assertEquals(set(CharacterBackground.backgroundFor(Background.FAME, 3, "Writing"),
				         CharacterBackground.backgroundFor(Background.RESOURCES, 2),
				         CharacterBackground.backgroundFor(Background.GENERATION, 1)), 
		             maryWollstonecraftWhenNewlyCreated.getBackgrounds());
		assertEquals(set(new CharacterDiscipline(Discipline.AUSPEX, 1), 
				         new CharacterDiscipline(Discipline.CELERITY, 1), 
				         new CharacterDiscipline(Discipline.PRESENCE, 2)), 
   		             maryWollstonecraftWhenNewlyCreated.getDisciplines());
		assertEquals(set(), maryWollstonecraftWhenNewlyCreated.getMerits());
		assertEquals(set(), maryWollstonecraftWhenNewlyCreated.getFlaws());
		assertEquals(200, maryWollstonecraftWhenNewlyCreated.getXpGained());
		assertEquals(0, maryWollstonecraftWhenNewlyCreated.getAppliedXpSpent());
		assertEquals(0, maryWollstonecraftWhenNewlyCreated.getRequestedXpSpent());
		
		assertEquals(0, maryWollstonecraftWhenNewlyCreated.getRequestedTraitChanges().size());
		assertEquals(0, maryWollstonecraftWhenNewlyCreated.getAppliedTraitChanges().size());
		
        spendXpForMaryWollstonecraft();
        
        PlayerCharacter maryWollstonecraftWithExperienceSpentButNotYetApproved = getMaryWollstonecraft();
        assertEquals(maryWollstonecraftWithExperienceSpentButNotYetApproved.getClan(), Clan.TOREADOR);
        assertEquals(maryWollstonecraftWithExperienceSpentButNotYetApproved.getBloodline(), Bloodline.TOREADOR);
		assertEquals(3, maryWollstonecraftWithExperienceSpentButNotYetApproved.getPhysicalAttribute());
		assertEquals(set(PhysicalAttributeFocus.DEXTERITY), maryWollstonecraftWithExperienceSpentButNotYetApproved.getPhysicalAttributeFocuses());
		assertEquals(5, maryWollstonecraftWithExperienceSpentButNotYetApproved.getSocialAttribute());
		assertEquals(set(SocialAttributeFocus.MANIPULATION), maryWollstonecraftWithExperienceSpentButNotYetApproved.getSocialAttributeFocuses());
		assertEquals(7, maryWollstonecraftWithExperienceSpentButNotYetApproved.getMentalAttribute());
		assertEquals(set(MentalAttributeFocus.PERCEPTION), maryWollstonecraftWithExperienceSpentButNotYetApproved.getMentalAttributeFocuses());
		assertEquals(set(new CharacterSkill(Skill.ACADEMICS, 1, null, set("Philosophy")),
				         new CharacterSkill(Skill.AWARENESS, 1, null, set()),
				         new CharacterSkill(Skill.COMPUTER, 1, null, set()),
				         new CharacterSkill(Skill.LEADERSHIP, 1, null, set()),
				         new CharacterSkill(Skill.DODGE, 2, null, set()),
				         new CharacterSkill(Skill.EMPATHY, 2, null, set()),
				         new CharacterSkill(Skill.STEALTH, 2, null, set()),
				         new CharacterSkill(Skill.SUBTERFUGE, 3, null, set()),
				         new CharacterSkill(Skill.INVESTIGATION, 3, null, set()),
				         new CharacterSkill(Skill.CRAFTS, 4, "Writing", set())), 
				     maryWollstonecraftWithExperienceSpentButNotYetApproved.getSkills());
		assertEquals(set(CharacterBackground.backgroundFor(Background.FAME, 3, "Writing"),
				         CharacterBackground.backgroundFor(Background.RESOURCES, 2),
				         CharacterBackground.backgroundFor(Background.GENERATION, 1)), 
		             maryWollstonecraftWithExperienceSpentButNotYetApproved.getBackgrounds());
		assertEquals(set(new CharacterDiscipline(Discipline.AUSPEX, 1), 
				         new CharacterDiscipline(Discipline.CELERITY, 1), 
				         new CharacterDiscipline(Discipline.PRESENCE, 2)), 
   		             maryWollstonecraftWithExperienceSpentButNotYetApproved.getDisciplines());
		assertEquals(set(), maryWollstonecraftWithExperienceSpentButNotYetApproved.getMerits());
		assertEquals(set(), maryWollstonecraftWithExperienceSpentButNotYetApproved.getFlaws());
		assertEquals(200, maryWollstonecraftWhenNewlyCreated.getXpGained());
		assertEquals(162, maryWollstonecraftWithExperienceSpentButNotYetApproved.getRequestedXpSpent());
		assertEquals(0, maryWollstonecraftWithExperienceSpentButNotYetApproved.getAppliedXpSpent());
		
		assertEquals(33, maryWollstonecraftWithExperienceSpentButNotYetApproved.getRequestedTraitChanges().size());
		assertEquals(0, maryWollstonecraftWithExperienceSpentButNotYetApproved.getAppliedTraitChanges().size());
		
        approveChangesOnMary();
        
        PlayerCharacter maryWollstonecraftWithExperienceSpentAndApproved = getMaryWollstonecraft();
        
        assertEquals(maryWollstonecraftWithExperienceSpentAndApproved.getClan(), Clan.TOREADOR);
        assertEquals(maryWollstonecraftWithExperienceSpentAndApproved.getBloodline(), Bloodline.TOREADOR);
		assertEquals(4, maryWollstonecraftWithExperienceSpentAndApproved.getPhysicalAttribute());
		assertEquals(set(PhysicalAttributeFocus.DEXTERITY), maryWollstonecraftWithExperienceSpentAndApproved.getPhysicalAttributeFocuses());
		assertEquals(6, maryWollstonecraftWithExperienceSpentAndApproved.getSocialAttribute());
		assertEquals(set(SocialAttributeFocus.MANIPULATION), maryWollstonecraftWithExperienceSpentAndApproved.getSocialAttributeFocuses());
		assertEquals(8, maryWollstonecraftWithExperienceSpentAndApproved.getMentalAttribute());
		assertEquals(set(MentalAttributeFocus.PERCEPTION, MentalAttributeFocus.WITS), maryWollstonecraftWithExperienceSpentAndApproved.getMentalAttributeFocuses());
		assertEquals(set(new CharacterSkill(Skill.ACADEMICS, 3, null, set("Philosophy", "Latin Poetry", "Greek Poetry")),
				         new CharacterSkill(Skill.AWARENESS, 1, null, set()),
				         new CharacterSkill(Skill.COMPUTER, 1, null, set()),
				         new CharacterSkill(Skill.LEADERSHIP, 1, null, set()),
				         new CharacterSkill(Skill.DODGE, 2, null, set()),
				         new CharacterSkill(Skill.EMPATHY, 2, null, set()),
				         new CharacterSkill(Skill.STEALTH, 2, null, set()),
				         new CharacterSkill(Skill.SUBTERFUGE, 3, null, set()),
				         new CharacterSkill(Skill.INVESTIGATION, 3, null, set()),
				         new CharacterSkill(Skill.CRAFTS, 4, "Writing", set()),
				         new CharacterSkill(Skill.CRAFTS, 3, "Poetry", set())), 
				     maryWollstonecraftWithExperienceSpentAndApproved.getSkills());
		assertEquals(set(CharacterBackground.backgroundFor(Background.FAME, 3, "Writing"),
				         CharacterBackground.backgroundFor(Background.RESOURCES, 2),
				         CharacterBackground.backgroundFor(Background.GENERATION, 1),
				         CharacterBackground.backgroundFor(Background.HAVEN, 2, "Luxury Home", set("Luxury", "Location"))), 
		             maryWollstonecraftWithExperienceSpentAndApproved.getBackgrounds());
		assertEquals(set(new CharacterDiscipline(Discipline.AUSPEX, 3), 
				         new CharacterDiscipline(Discipline.CELERITY, 1), 
				         new CharacterDiscipline(Discipline.PRESENCE, 2),
				         new CharacterDiscipline(Discipline.ANIMALISM, 1),
				         new CharacterDiscipline(Discipline.PATH_OF_BLOOD, 2),
				         new CharacterDiscipline(Discipline.LURE_OF_FLAMES, 1),
				         new CharacterDiscipline(Discipline.ASH_PATH, 2),
		                 new CharacterDiscipline(Discipline.BONE_PATH, 1)), 
   		             maryWollstonecraftWithExperienceSpentAndApproved.getDisciplines());
		assertEquals(set(ThaumaturgicalRitual.CRAFT_BLOODSTONE, ThaumaturgicalRitual.BURNING_BLADE),
                     maryWollstonecraftWithExperienceSpentAndApproved.getThaumaturgicalRituals());
        assertEquals(set(NecromanticRitual.BLACK_BLOOD, NecromanticRitual.DARK_ASSISTANT), 
                     maryWollstonecraftWithExperienceSpentAndApproved.getNecromanticRituals());
        assertEquals(set(Technique.ARMOR_OF_DARKNESS, Technique.CONTROL_THE_SAVAGE_BEAST),
        		     maryWollstonecraftWithExperienceSpentAndApproved.getTechniques());
		assertEquals(set(ElderPower.CLAIRVOYANCE, ElderPower.ACID_BLOOD),
		             maryWollstonecraftWithExperienceSpentAndApproved.getElderPowers());
		assertEquals(set(new CharacterMerit(Merit.ARTISTS_BLESSING, "Poetry"),
				         new CharacterMerit(Merit.LUCKY),
				         new CharacterMerit(Merit.THAUMATURGIC_TRAINING, "Path of Corruption"),
				         new CharacterMerit(Merit.NECROMANTIC_TRAINING, "Ash Path"),
				         new CharacterMerit(Merit.ADDITIONAL_COMMON_DISCIPLINE, "Dominate"),
				         new CharacterMerit(Merit.VERSATILE, "Wits")), 
				     maryWollstonecraftWithExperienceSpentAndApproved.getMerits());
		assertEquals(set(new CharacterFlaw(Flaw.CURIOUSITY),
		                 new CharacterFlaw(Flaw.LESSER_GENERATION_2)), 
		             maryWollstonecraftWithExperienceSpentAndApproved.getFlaws());
		assertEquals(200, maryWollstonecraftWithExperienceSpentAndApproved.getXpGained());
		assertEquals(0, maryWollstonecraftWithExperienceSpentAndApproved.getRequestedXpSpent());
		assertEquals(163, maryWollstonecraftWithExperienceSpentAndApproved.getAppliedXpSpent());
		
		//Double check this
//		assertEquals(37, maryWollstonecraftWithExperienceSpentAndApproved.getXp());
		assertEquals(set(new CharacterStatus(Status.AWESOME, "So very awesome")), maryWollstonecraftWithExperienceSpentAndApproved.getStatus());
//		backoutSomeOfThoseChanges();
		assertEquals(0, maryWollstonecraftWithExperienceSpentAndApproved.getRequestedTraitChanges().size());
		assertEquals(33, maryWollstonecraftWithExperienceSpentAndApproved.getAppliedTraitChanges().size());
		
		earnXpForMaryWollstonecraft();
		assertEquals(0, maryWollstonecraftWithExperienceSpentAndApproved.getRequestedTraitChanges().size());
		assertEquals(33, maryWollstonecraftWithExperienceSpentAndApproved.getAppliedTraitChanges().size());
    }

    private void createMaryWollstonecraft() {
        PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
        
        PlayerCharacter character = characterRepository.ensureExists("Mary Wollstonecraft", Setting.CAMARILLA);
        PlayerCharacter saved = characterRepository.update(character.
        		                             withClan(Clan.TOREADOR).
                                             withBloodline(Bloodline.TOREADOR).
                                             withInClanDiscipline(Discipline.PRESENCE).
                                             withInClanDiscipline(Discipline.CELERITY).
                                             withInClanDiscipline(Discipline.AUSPEX).
                                             withPhysicalAttribute(3).withPhysicalAttributeFocus(PhysicalAttributeFocus.DEXTERITY).
                                             withSocialAttribute(5).withSocialAttributeFocus(SocialAttributeFocus.MANIPULATION).
                                             withMentalAttribute(7).withMentalAttributeFocus(MentalAttributeFocus.PERCEPTION).
                                             withSkill(new CharacterSkill(Skill.ACADEMICS, 1, null, set("Philosophy"))).
                                             withSkill(new CharacterSkill(Skill.AWARENESS, 1, null, set())).
                                             withSkill(new CharacterSkill(Skill.COMPUTER, 1, null, set())).
                                             withSkill(new CharacterSkill(Skill.LEADERSHIP, 1, null, set())).
                                             withSkill(new CharacterSkill(Skill.DODGE, 2, null, set())).
                                             withSkill(new CharacterSkill(Skill.EMPATHY, 2, null, set())).
                                             withSkill(new CharacterSkill(Skill.STEALTH, 2, null, set())).
                                             withSkill(new CharacterSkill(Skill.SUBTERFUGE, 3, null, set())).
                                             withSkill(new CharacterSkill(Skill.INVESTIGATION, 3, null, set())).
                                             withSkill(new CharacterSkill(Skill.CRAFTS, 4, "Writing", set())).
                                             withBackground(CharacterBackground.backgroundFor(Background.FAME, 3, "Writing")).
                                             withBackground(CharacterBackground.backgroundFor(Background.RESOURCES, 2)).
                                             withBackground(CharacterBackground.backgroundFor(Background.GENERATION, 1)).
                                             withDiscipline(new CharacterDiscipline(Discipline.PRESENCE, 2)).
                                             withDiscipline(new CharacterDiscipline(Discipline.CELERITY, 1)).
                                             withDiscipline(new CharacterDiscipline(Discipline.AUSPEX, 1)).
                                             changeActivityStatus(new PlayerStatusChange(PlayerStatus.PRIMARY, DateTimeUtilities.fromLocalDateInUTC(LocalDate.of(2014, 12, 1)))).
                                             withBaseXp(200));
        characterRepository.update(saved);
    }

    private void spendXpForMaryWollstonecraft() {
        PlayerCharacterRepository repository = appConfig.getBean(PlayerCharacterRepository.class);
        TraitChangeFactoryProvider TraitChangeFactoryProvider = appConfig.getBean(TraitChangeFactoryProvider.class);
		TraitChangeFactory experienceChart = TraitChangeFactoryProvider.buyTraitsFor(getMaryWollstonecraft());
        TraitChangeFactory traitFactory = TraitChangeFactoryProvider.giveTraits();
        
        repository.update(getMaryWollstonecraft()
        						.request(experienceChart.traitChange(Traits.SKILLS, Skill.ACADEMICS, new TraitQualitiesBuilder().rated(2).focused(set("Philosophy", "Latin Poetry")).build()))
                                .request(experienceChart.traitChange(Traits.SKILLS, Skill.ACADEMICS, new TraitQualitiesBuilder().rated(3).focused(set("Philosophy", "Latin Poetry", "Greek Poetry")).build()))
                                .request(experienceChart.traitChange(Traits.MERIT, Merit.ARTISTS_BLESSING, new TraitQualitiesBuilder().specialized("Poetry").build()).and(traitFactory.traitChange(Traits.SKILLS, Skill.CRAFTS, new TraitQualitiesBuilder().rated(3).specialized("Poetry").build())))
        						.request(experienceChart.traitChange(Traits.MERIT, Merit.LUCKY, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(Traits.MERIT, Merit.VERSATILE, new TraitQualitiesBuilder().specialized("Wits").build()).and(traitFactory.traitChange(Traits.MENTAL_FOCUS, MentalAttributeFocus.WITS, TraitQualities.NONE)))
		                        .request(experienceChart.traitChange(Traits.FLAW, Flaw.CURIOUSITY, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(Traits.FLAW, Flaw.LESSER_GENERATION_2, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(Traits.ATTRIBUTE, Attribute.PHYSICAL,new TraitQualitiesBuilder().rated(getMaryWollstonecraft().getPhysicalAttribute()+1).build()))
		                        .request(experienceChart.traitChange(Traits.ATTRIBUTE, Attribute.SOCIAL,new TraitQualitiesBuilder().rated(getMaryWollstonecraft().getSocialAttribute()+1).build()))
		                        .request(experienceChart.traitChange(Traits.ATTRIBUTE, Attribute.MENTAL,new TraitQualitiesBuilder().rated(getMaryWollstonecraft().getMentalAttribute()+1).build()))
		                        .request(experienceChart.traitChange(Traits.BACKGROUNDS,Background.HAVEN, new TraitQualitiesBuilder().rated(1).specialized("Luxury Home").focused(set("Luxury")).build()))
		                        .request(experienceChart.traitChange(Traits.BACKGROUNDS,Background.HAVEN, new TraitQualitiesBuilder().rated(1).specialized("Luxury Home").focused(set("Luxury", "Location")).build()))
		                        .request(experienceChart.traitChange(Traits.DISCIPLINE,Discipline.AUSPEX, new TraitQualitiesBuilder().rated(2).build()))
		                        .request(experienceChart.traitChange(Traits.DISCIPLINE,Discipline.AUSPEX, new TraitQualitiesBuilder().rated(3).build()))
		                        .request(experienceChart.traitChange(Traits.DISCIPLINE,Discipline.ANIMALISM, new TraitQualitiesBuilder().rated(1).build()))
		                        .request(experienceChart.traitChange(Traits.DISCIPLINE,Discipline.PATH_OF_BLOOD, new TraitQualitiesBuilder().rated(1).build()))
		                        .request(experienceChart.traitChange(Traits.DISCIPLINE,Discipline.PATH_OF_BLOOD, new TraitQualitiesBuilder().rated(2).build()))
		                        .request(experienceChart.traitChange(Traits.DISCIPLINE,Discipline.LURE_OF_FLAMES, new TraitQualitiesBuilder().rated(1).build()))
		                        .request(experienceChart.traitChange(Traits.DISCIPLINE,Discipline.ASH_PATH, new TraitQualitiesBuilder().rated(1).build()))
		                        .request(experienceChart.traitChange(Traits.DISCIPLINE,Discipline.ASH_PATH, new TraitQualitiesBuilder().rated(2).build()))
		                        .request(experienceChart.traitChange(Traits.DISCIPLINE,Discipline.BONE_PATH, new TraitQualitiesBuilder().rated(1).build()))
		                        .request(experienceChart.traitChange(Traits.THAUMATURGICAL_RITUAL, ThaumaturgicalRitual.CRAFT_BLOODSTONE, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(Traits.THAUMATURGICAL_RITUAL, ThaumaturgicalRitual.BURNING_BLADE, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(Traits.NECROMANTIC_RITUAL, NecromanticRitual.BLACK_BLOOD, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(Traits.NECROMANTIC_RITUAL, NecromanticRitual.DARK_ASSISTANT, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(Traits.TECHNIQUE, Technique.ARMOR_OF_DARKNESS, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(Traits.TECHNIQUE, Technique.CONTROL_THE_SAVAGE_BEAST, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(Traits.ELDER_POWER, ElderPower.CLAIRVOYANCE, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(Traits.ELDER_POWER, ElderPower.ACID_BLOOD, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(Traits.MERIT, Merit.THAUMATURGIC_TRAINING, new TraitQualitiesBuilder().specialized("Path of Corruption").build()).and(traitFactory.traitChange(Traits.DISCIPLINE, Discipline.PATH_OF_CORRUPTION, TraitQualities.NONE)))
		                        .request(experienceChart.traitChange(Traits.MERIT, Merit.NECROMANTIC_TRAINING, new TraitQualitiesBuilder().specialized("Ash Path").build()).and(traitFactory.traitChange(Traits.DISCIPLINE, Discipline.ASH_PATH, TraitQualities.NONE)))
		                        .request(experienceChart.traitChange(Traits.MERIT, Merit.ADDITIONAL_COMMON_DISCIPLINE, new TraitQualitiesBuilder().specialized("Dominate").build()).and(traitFactory.traitChange(Traits.DISCIPLINE, Discipline.DOMINATE, TraitQualities.NONE)))
		                        .request(traitFactory.traitChange(Traits.STATUS, Status.AWESOME, new TraitQualitiesBuilder().specialized("So very awesome").build())
                                ));
    }
    
    private void earnXpForMaryWollstonecraft() {
    	PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
    	ExperienceAwardService awardService = appConfig.getBean(ExperienceAwardService.class);
    	assertEquals(200, getMaryWollstonecraft().getXpGained());
		
    	characterRepository.update(awardService.awardCharacter(getMaryWollstonecraft(), 3, LocalDate.of(2022, Month.JULY, 1), "Attendance"));
    	assertEquals(203, getMaryWollstonecraft().getXpGained());
    	
    	characterRepository.update(awardService.awardCharacter(getMaryWollstonecraft(), 8, LocalDate.of(2022, Month.JULY, 1), "Attendance"));
    	assertEquals(210, getMaryWollstonecraft().getXpGained());
    	
    	characterRepository.update(awardService.awardCharacter(getMaryWollstonecraft(), 11, LocalDate.of(2022, Month.AUGUST, 1), "Attendance"));
    	assertEquals(220, getMaryWollstonecraft().getXpGained());
    }

//    private void backoutSomeOfThoseChanges() {
//    	PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
//        TraitChangeFactoryProvider TraitChangeFactoryProvider = appConfig.getBean(TraitChangeFactoryProvider.class);
//		TraitChangeFactory experienceChart = TraitChangeFactoryProvider.buyTraitsFor(getMaryWollstonecraft());
//        TraitChangeFactory traitFactory = TraitChangeFactoryProvider.giveTraits();
//      
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.skill(Skill.ACADEMICS, 3, null, set("Philosophy", "Latin Poetry", "Greek Poetry")).remove().
//        		                                                               and(traitFactory.skill(Skill.ACADEMICS, 2, null, set("Philosophy", "Latin Poetry")))));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(Merit.LUCKY, null, null).remove()));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(Merit.VERSATILE, null, traitFactory.focus(MentalAttributeFocus.WITS)).remove()));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.flaw(Flaw.CURIOUSITY, null, null).remove()));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Necromancy.BONE_PATH, 1).remove()));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Necromancy.ASH_PATH, 2).remove().
//                                                                            and(traitFactory.power(Necromancy.ASH_PATH, 1))));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Thaumaturgy.LURE_OF_FLAMES, 1).remove()));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Thaumaturgy.PATH_OF_BLOOD, 2).remove().
//        		                                                            and(traitFactory.power(Thaumaturgy.PATH_OF_BLOOD, 1))));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.technique(Technique.ARMOR_OF_DARKNESS).remove()));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.elderPower(ElderPower.ACID_BLOOD).remove()));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Discipline.ANIMALISM, 1).remove()));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(traitFactory.status(Status.AWESOME, "So very awesome").remove()));
//        approveChangesOnMary();
//    }
    
	private PlayerCharacter getMaryWollstonecraft() {
		PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
		return characterRepository.findNamed("Mary Wollstonecraft");
	}

    private void approveChangesOnMary() {
        PlayerCharacter character = getMaryWollstonecraft();
		PlayerCharacterRepository repository = appConfig.getBean(PlayerCharacterRepository.class);
		character.getRequestedTraitChanges().forEach((TraitChange t) -> character.apply(t));
		repository.update(character);
    }
}
