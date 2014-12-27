package com.dstevens.characters;

import static com.dstevens.collections.Sets.set;

import static org.junit.Assert.assertEquals;

import java.util.stream.StreamSupport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.SocialAttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.CharacterBackground;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeFactory;
import com.dstevens.characters.traits.changes.TraitChangeFactoryProvider;
import com.dstevens.characters.traits.changes.TraitChangeStatusFactory;
import com.dstevens.characters.traits.distinctions.flaws.CharacterFlaw;
import com.dstevens.characters.traits.distinctions.flaws.Flaw;
import com.dstevens.characters.traits.distinctions.merits.CharacterMerit;
import com.dstevens.characters.traits.distinctions.merits.Merit;
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
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.status.CharacterStatus;
import com.dstevens.characters.traits.status.Status;
import com.dstevens.configuration.ApplicationConfiguration;

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
		assertEquals(200, maryWollstonecraftWhenNewlyCreated.getXp());
		
		assertEquals(0, maryWollstonecraftWhenNewlyCreated.getTraitChanges().stream().filter((TraitChange<?> t) -> t.currentStatus().pending()).count());
		assertEquals(0, maryWollstonecraftWhenNewlyCreated.getTraitChanges().stream().filter((TraitChange<?> t) -> t.currentStatus().applied()).count());
		
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
		assertEquals(200, maryWollstonecraftWhenNewlyCreated.getXp());
		
		assertEquals(33, maryWollstonecraftWithExperienceSpentButNotYetApproved.getTraitChanges().stream().filter((TraitChange<?> t) -> t.currentStatus().pending()).count());
		assertEquals(0, maryWollstonecraftWithExperienceSpentButNotYetApproved.getTraitChanges().stream().filter((TraitChange<?> t) -> t.currentStatus().applied()).count());
		
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
				         new CharacterDiscipline(Discipline.ANIMALISM, 1)), 
   		             maryWollstonecraftWithExperienceSpentAndApproved.getDisciplines());
		assertEquals(set(new CharacterThaumaturgy(Thaumaturgy.PATH_OF_BLOOD, 2),
				         new CharacterThaumaturgy(Thaumaturgy.LURE_OF_FLAMES, 1)), 
	                 maryWollstonecraftWithExperienceSpentAndApproved.getThaumaturgicalPaths());
		assertEquals(set(new CharacterNecromancy(Necromancy.ASH_PATH, 2),
		                 new CharacterNecromancy(Necromancy.BONE_PATH, 1)), 
                     maryWollstonecraftWithExperienceSpentAndApproved.getNecromanticPaths());
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
		//Double check this
//		assertEquals(37, maryWollstonecraftWithExperienceSpentAndApproved.getXp());
		assertEquals(set(new CharacterStatus(Status.AWESOME, "So very awesome")), maryWollstonecraftWithExperienceSpentAndApproved.getStatus());
//		backoutSomeOfThoseChanges();
		assertEquals(0, maryWollstonecraftWithExperienceSpentAndApproved.getTraitChanges().stream().filter((TraitChange<?> t) -> t.currentStatus().pending()).count());
		assertEquals(33, maryWollstonecraftWithExperienceSpentAndApproved.getTraitChanges().stream().filter((TraitChange<?> t) -> t.currentStatus().applied()).count());
		
		earnXpForMaryWollstonecraft();
		assertEquals(0, getMaryWollstonecraft().getTraitChanges().stream().filter((TraitChange<?> t) -> t.currentStatus().pending()).count());
		assertEquals(36, getMaryWollstonecraft().getTraitChanges().stream().filter((TraitChange<?> t) -> t.currentStatus().applied()).count());
    }

    private void createMaryWollstonecraft() {
        PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
        
        PlayerCharacter character = characterRepository.ensureExists("Mary Wollstonecraft");
        PlayerCharacter saved = characterRepository.update(character.ofClan(Clan.TOREADOR).
                                             ofBloodline(Bloodline.TOREADOR).
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
                                             setXp(200));
        characterRepository.update(saved);
    }

    private void spendXpForMaryWollstonecraft() {
        PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
        TraitChangeFactoryProvider TraitChangeFactoryProvider = appConfig.getBean(TraitChangeFactoryProvider.class);
		TraitChangeFactory experienceChart = TraitChangeFactoryProvider.buyTraitsFor(getMaryWollstonecraft());
        TraitChangeFactory traitFactory = TraitChangeFactoryProvider.giveTraits();
        
        characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.skill(Skill.ACADEMICS, 2, null, set("Philosophy", "Latin Poetry"))));
        characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.skill(Skill.ACADEMICS, 3, null, set("Philosophy", "Latin Poetry", "Greek Poetry"))));
        characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.merit(Merit.ARTISTS_BLESSING, "Poetry", traitFactory.skill(Skill.CRAFTS, 3, "Poetry", set()))));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.merit(Merit.LUCKY, null, null)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.merit(Merit.VERSATILE, "Wits", traitFactory.focus(MentalAttributeFocus.WITS))));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.flaw(Flaw.CURIOUSITY, null, null)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.flaw(Flaw.LESSER_GENERATION_2, null, null)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.attribute(Attribute.PHYSICAL, getMaryWollstonecraft().getPhysicalAttribute()+1)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.attribute(Attribute.SOCIAL ,getMaryWollstonecraft().getSocialAttribute()+1)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.attribute(Attribute.MENTAL, getMaryWollstonecraft().getMentalAttribute()+1)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.background(Background.HAVEN, 1, "Luxury Home", set("Luxury"))));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.background(Background.HAVEN, 2, "Luxury Home", set("Luxury", "Location"))));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.power(Discipline.AUSPEX, 2)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.power(Discipline.AUSPEX, 3)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.power(Discipline.ANIMALISM, 1)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.power(Thaumaturgy.PATH_OF_BLOOD, 1)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.power(Thaumaturgy.PATH_OF_BLOOD, 2)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.power(Thaumaturgy.LURE_OF_FLAMES, 1)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.power(Necromancy.ASH_PATH, 1)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.power(Necromancy.ASH_PATH, 2)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.power(Necromancy.BONE_PATH, 1)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.ritual(ThaumaturgicalRitual.CRAFT_BLOODSTONE)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.ritual(ThaumaturgicalRitual.BURNING_BLADE)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.ritual(NecromanticRitual.BLACK_BLOOD)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.ritual(NecromanticRitual.DARK_ASSISTANT)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.technique(Technique.ARMOR_OF_DARKNESS)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.technique(Technique.CONTROL_THE_SAVAGE_BEAST)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.elderPower(ElderPower.CLAIRVOYANCE)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.elderPower(ElderPower.ACID_BLOOD)));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.merit(Merit.THAUMATURGIC_TRAINING, "Path of Corruption", traitFactory.inClanPower(Thaumaturgy.PATH_OF_CORRUPTION))));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.merit(Merit.NECROMANTIC_TRAINING, "Ash Path", traitFactory.inClanPower(Necromancy.ASH_PATH))));
		characterRepository.update(getMaryWollstonecraft().addTraitChange(experienceChart.merit(Merit.ADDITIONAL_COMMON_DISCIPLINE, "Dominate", traitFactory.inClanPower(Discipline.DOMINATE))));
		
		characterRepository.update(getMaryWollstonecraft().addTraitChange(traitFactory.status(Status.AWESOME, "So very awesome")));
    }
    
    private void earnXpForMaryWollstonecraft() {
//    	PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
//    	ExperienceAwardService awardService = appConfig.getBean(ExperienceAwardService.class);
//    	assertEquals(37, getMaryWollstonecraft().getXp());
//    	Instant july2022 = LocalDateTime.of(2022, Month.JULY.getValue(), 2, 10, 12).toInstant(ZoneOffset.UTC);
//    	
//    	characterRepository.update(awardService.awardCharacter(getMaryWollstonecraft(), 3, Date.from(july2022)));
//    	assertEquals(40, getMaryWollstonecraft().getXp());
//    	
//    	characterRepository.update(awardService.awardCharacter(getMaryWollstonecraft(), 8, Date.from(july2022)));
//    	assertEquals(47, getMaryWollstonecraft().getXp());
//    	
//    	Instant august2022 = LocalDateTime.of(2022, Month.AUGUST.getValue(), 2, 10, 12).toInstant(ZoneOffset.UTC);
//    	characterRepository.update(awardService.awardCharacter(getMaryWollstonecraft(), 11, Date.from(august2022)));
//    	assertEquals(57, getMaryWollstonecraft().getXp());
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
        PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
        TraitChangeStatusFactory traitChangeStatusFactory = appConfig.getBean(TraitChangeStatusFactory.class);
        characterRepository.update(getMaryWollstonecraft().approveAllOutstandingRequests(traitChangeStatusFactory.applied()));
    }
}
