package com.dstevens.characters;

import java.time.LocalDate;
import java.util.stream.StreamSupport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.PlayerCharacterRepository;
import com.dstevens.character.Setting;
import com.dstevens.character.clan.Bloodline;
import com.dstevens.character.clan.Clan;
import com.dstevens.character.trait.TraitQualities;
import com.dstevens.character.trait.TraitQualitiesBuilder;
import com.dstevens.character.trait.TraitType;
import com.dstevens.character.trait.attribute.Attribute;
import com.dstevens.character.trait.attribute.focus.MentalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.PhysicalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.SocialAttributeFocus;
import com.dstevens.character.trait.background.Background;
import com.dstevens.character.trait.background.CharacterBackground;
import com.dstevens.character.trait.change.TraitChange;
import com.dstevens.character.trait.change.TraitChangeFactory;
import com.dstevens.character.trait.change.TraitChangeFactoryProvider;
import com.dstevens.character.trait.distinction.flaw.CharacterFlaw;
import com.dstevens.character.trait.distinction.flaw.Flaw;
import com.dstevens.character.trait.distinction.merit.CharacterMerit;
import com.dstevens.character.trait.distinction.merit.Merit;
import com.dstevens.character.trait.experience.ExperienceAward;
import com.dstevens.character.trait.power.discipline.CharacterDiscipline;
import com.dstevens.character.trait.power.discipline.Discipline;
import com.dstevens.character.trait.power.discipline.ElderPower;
import com.dstevens.character.trait.power.discipline.Technique;
import com.dstevens.character.trait.power.magic.necromancy.NecromanticRitual;
import com.dstevens.character.trait.power.magic.thaumaturgy.ThaumaturgicalRitual;
import com.dstevens.character.trait.skill.CharacterSkill;
import com.dstevens.character.trait.skill.Skill;
import com.dstevens.character.trait.status.CharacterStatus;
import com.dstevens.character.trait.status.Status;
import com.dstevens.configuration.ApplicationConfiguration;

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
                                             withBaseXp(200));
        characterRepository.update(saved);
    }

    private void spendXpForMaryWollstonecraft() {
        PlayerCharacterRepository repository = appConfig.getBean(PlayerCharacterRepository.class);
        TraitChangeFactoryProvider TraitChangeFactoryProvider = appConfig.getBean(TraitChangeFactoryProvider.class);
		TraitChangeFactory experienceChart = TraitChangeFactoryProvider.buyTraitsFor(getMaryWollstonecraft());
        TraitChangeFactory traitFactory = TraitChangeFactoryProvider.giveTraits();
        
        repository.update(getMaryWollstonecraft()
        						.request(experienceChart.traitChange(TraitType.SKILL, Skill.ACADEMICS, new TraitQualitiesBuilder().rated(2).focused(set("Philosophy", "Latin Poetry")).build()))
                                .request(experienceChart.traitChange(TraitType.SKILL, Skill.ACADEMICS, new TraitQualitiesBuilder().rated(3).focused(set("Philosophy", "Latin Poetry", "Greek Poetry")).build()))
                                .request(experienceChart.traitChange(TraitType.MERIT, Merit.ARTISTS_BLESSING, new TraitQualitiesBuilder().specialized("Poetry").build()).and(traitFactory.traitChange(TraitType.SKILL, Skill.CRAFTS, new TraitQualitiesBuilder().rated(3).specialized("Poetry").build())))
        						.request(experienceChart.traitChange(TraitType.MERIT, Merit.LUCKY, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(TraitType.MERIT, Merit.VERSATILE, new TraitQualitiesBuilder().specialized("Wits").build()).and(traitFactory.traitChange(TraitType.MENTAL_FOCUS, MentalAttributeFocus.WITS, TraitQualities.NONE)))
		                        .request(experienceChart.traitChange(TraitType.FLAW, Flaw.CURIOUSITY, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(TraitType.FLAW, Flaw.LESSER_GENERATION_2, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(TraitType.ATTRIBUTE, Attribute.PHYSICAL,new TraitQualitiesBuilder().rated(getMaryWollstonecraft().getPhysicalAttribute()+1).build()))
		                        .request(experienceChart.traitChange(TraitType.ATTRIBUTE, Attribute.SOCIAL,new TraitQualitiesBuilder().rated(getMaryWollstonecraft().getSocialAttribute()+1).build()))
		                        .request(experienceChart.traitChange(TraitType.ATTRIBUTE, Attribute.MENTAL,new TraitQualitiesBuilder().rated(getMaryWollstonecraft().getMentalAttribute()+1).build()))
		                        .request(experienceChart.traitChange(TraitType.BACKGROUND,Background.HAVEN, new TraitQualitiesBuilder().rated(1).specialized("Luxury Home").focused(set("Luxury")).build()))
		                        .request(experienceChart.traitChange(TraitType.BACKGROUND,Background.HAVEN, new TraitQualitiesBuilder().rated(1).specialized("Luxury Home").focused(set("Luxury", "Location")).build()))
		                        .request(experienceChart.traitChange(TraitType.DISCIPLINE,Discipline.AUSPEX, new TraitQualitiesBuilder().rated(2).build()))
		                        .request(experienceChart.traitChange(TraitType.DISCIPLINE,Discipline.AUSPEX, new TraitQualitiesBuilder().rated(3).build()))
		                        .request(experienceChart.traitChange(TraitType.DISCIPLINE,Discipline.ANIMALISM, new TraitQualitiesBuilder().rated(1).build()))
		                        .request(experienceChart.traitChange(TraitType.DISCIPLINE,Discipline.PATH_OF_BLOOD, new TraitQualitiesBuilder().rated(1).build()))
		                        .request(experienceChart.traitChange(TraitType.DISCIPLINE,Discipline.PATH_OF_BLOOD, new TraitQualitiesBuilder().rated(2).build()))
		                        .request(experienceChart.traitChange(TraitType.DISCIPLINE,Discipline.LURE_OF_FLAMES, new TraitQualitiesBuilder().rated(1).build()))
		                        .request(experienceChart.traitChange(TraitType.DISCIPLINE,Discipline.ASH_PATH, new TraitQualitiesBuilder().rated(1).build()))
		                        .request(experienceChart.traitChange(TraitType.DISCIPLINE,Discipline.ASH_PATH, new TraitQualitiesBuilder().rated(2).build()))
		                        .request(experienceChart.traitChange(TraitType.DISCIPLINE,Discipline.BONE_PATH, new TraitQualitiesBuilder().rated(1).build()))
		                        .request(experienceChart.traitChange(TraitType.THAUMATURGICAL_RITUAL, ThaumaturgicalRitual.CRAFT_BLOODSTONE, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(TraitType.THAUMATURGICAL_RITUAL, ThaumaturgicalRitual.BURNING_BLADE, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(TraitType.NECROMANTIC_RITUAL, NecromanticRitual.BLACK_BLOOD, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(TraitType.NECROMANTIC_RITUAL, NecromanticRitual.DARK_ASSISTANT, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(TraitType.TECHNIQUE, Technique.ARMOR_OF_DARKNESS, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(TraitType.TECHNIQUE, Technique.CONTROL_THE_SAVAGE_BEAST, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(TraitType.ELDER_POWER, ElderPower.CLAIRVOYANCE, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(TraitType.ELDER_POWER, ElderPower.ACID_BLOOD, TraitQualities.NONE))
		                        .request(experienceChart.traitChange(TraitType.MERIT, Merit.THAUMATURGIC_TRAINING, new TraitQualitiesBuilder().specialized("Path of Corruption").build()).and(traitFactory.traitChange(TraitType.DISCIPLINE, Discipline.PATH_OF_CORRUPTION, TraitQualities.NONE)))
		                        .request(experienceChart.traitChange(TraitType.MERIT, Merit.NECROMANTIC_TRAINING, new TraitQualitiesBuilder().specialized("Ash Path").build()).and(traitFactory.traitChange(TraitType.DISCIPLINE, Discipline.ASH_PATH, TraitQualities.NONE)))
		                        .request(experienceChart.traitChange(TraitType.MERIT, Merit.ADDITIONAL_COMMON_DISCIPLINE, new TraitQualitiesBuilder().specialized("Dominate").build()).and(traitFactory.traitChange(TraitType.DISCIPLINE, Discipline.DOMINATE, TraitQualities.NONE)))
		                        .request(traitFactory.traitChange(TraitType.STATUS, Status.AWESOME, new TraitQualitiesBuilder().specialized("So very awesome").build())
                                ));
    }
    
    private void earnXpForMaryWollstonecraft() {
    	PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
    	assertEquals(200, getMaryWollstonecraft().getXpGained());
		
    	characterRepository.update(getMaryWollstonecraft().addExperienceAward(new ExperienceAward(3, LocalDate.now(), "")));
    	assertEquals(203, getMaryWollstonecraft().getXpGained());
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
