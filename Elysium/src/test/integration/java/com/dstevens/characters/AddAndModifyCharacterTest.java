package com.dstevens.characters;

import static com.dstevens.collections.Sets.set;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.TraitChangeFactory;
import com.dstevens.characters.traits.TraitChangeFactoryProvider;
import com.dstevens.characters.traits.attributes.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.SocialAttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.CharacterBackground;
import com.dstevens.characters.traits.distinctions.CharacterFlaw;
import com.dstevens.characters.traits.distinctions.CharacterMerit;
import com.dstevens.characters.traits.distinctions.Flaw;
import com.dstevens.characters.traits.distinctions.Merit;
import com.dstevens.characters.traits.powers.CharacterDiscipline;
import com.dstevens.characters.traits.powers.CharacterNecromancy;
import com.dstevens.characters.traits.powers.CharacterThaumaturgy;
import com.dstevens.characters.traits.powers.Discipline;
import com.dstevens.characters.traits.powers.ElderPower;
import com.dstevens.characters.traits.powers.Necromancy;
import com.dstevens.characters.traits.powers.NecromanticRitual;
import com.dstevens.characters.traits.powers.Technique;
import com.dstevens.characters.traits.powers.ThaumaturgicalRitual;
import com.dstevens.characters.traits.powers.Thaumaturgy;
import com.dstevens.characters.traits.skills.CharacterSkill;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.configuration.ApplicationConfiguration;
import com.dstevens.players.Player;
import com.dstevens.players.PlayerRepository;
import com.dstevens.players.Setting;
import com.dstevens.players.Troupe;
import com.dstevens.players.TroupeRepository;

public class AddAndModifyCharacterTest {

	private static final String TROUPE_NAME = "some troupe";
	private static final String PLAYER_NAME = "some player name";
	private static final String PLAYER_EMAIL = "some email";
	
    private ApplicationContext appConfig = ApplicationConfiguration.appConfig();
    
    @Before
    public void setUp() {
    	this.appConfig = ApplicationConfiguration.appConfig();
        TroupeRepository troupeRepository = appConfig.getBean(TroupeRepository.class);
        PlayerRepository playerRepository = appConfig.getBean(PlayerRepository.class);
        
        Troupe troupe = troupeRepository.ensureExists(TROUPE_NAME, Setting.CAMARILLA);
        playerRepository.ensureExists(PLAYER_NAME, PLAYER_EMAIL, troupe);
    }
    
    @After
    public void tearDown() {
    	TroupeRepository troupeRepository = appConfig.getBean(TroupeRepository.class);
        PlayerRepository playerRepository = appConfig.getBean(PlayerRepository.class);
        PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
        
        Troupe troupe = troupeRepository.findNamed(TROUPE_NAME);
        troupe.getCharacters().stream().forEach(((PlayerCharacter pc) -> characterRepository.hardDelete(pc)));
        troupe.getPlayers().stream().forEach(((Player pc) -> playerRepository.hardDelete(pc)));
        troupeRepository.hardDelete(troupe);
    }
    
    @Test   
    public void testCreateAndModify() {
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
		
		System.out.println("Created");
        spendXpForMaryWollstonecraft();
        System.out.println("Spent");
        
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
		
		System.out.println("Approved");
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
		assertEquals(37, maryWollstonecraftWithExperienceSpentAndApproved.getXp());
		
		System.out.println("Removed");
		backoutSomeOfThoseChanges();
    }

    private void createMaryWollstonecraft() {
        TroupeRepository troupeRepository = appConfig.getBean(TroupeRepository.class);
        PlayerRepository playerRepository = appConfig.getBean(PlayerRepository.class);
        PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
        
        Troupe troupe = troupeRepository.ensureExists(TROUPE_NAME, Setting.CAMARILLA);
        Player player = playerRepository.ensureExists(PLAYER_NAME, PLAYER_EMAIL, troupe);
        
        PlayerCharacter character = characterRepository.ensureExists(troupe, player, "Mary Wollstonecraft");
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
        
        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.skill(Skill.ACADEMICS, 2, null, set("Philosophy", "Latin Poetry"))));
        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.skill(Skill.ACADEMICS, 3, null, set("Philosophy", "Latin Poetry", "Greek Poetry"))));
        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(Merit.ARTISTS_BLESSING, "Poetry", traitFactory.skill(Skill.CRAFTS, 3, "Poetry", set())).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(Merit.LUCKY, null, null).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(Merit.VERSATILE, "Wits", traitFactory.mentalFocus(MentalAttributeFocus.WITS)).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.flaw(Flaw.CURIOUSITY, null, null).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.flaw(Flaw.LESSER_GENERATION_2, null, null).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.physical(getMaryWollstonecraft())));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.social(getMaryWollstonecraft())));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.mental(getMaryWollstonecraft())));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.background(Background.HAVEN, 1, "Luxury Home", set("Luxury"))));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.background(Background.HAVEN, 2, "Luxury Home", set("Luxury", "Location"))));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Discipline.AUSPEX, 2)));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Discipline.AUSPEX, 3)));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Discipline.ANIMALISM, 1)));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Thaumaturgy.PATH_OF_BLOOD, 1)));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Thaumaturgy.PATH_OF_BLOOD, 2)));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Thaumaturgy.LURE_OF_FLAMES, 1)));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Necromancy.ASH_PATH, 1)));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Necromancy.ASH_PATH, 2)));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.power(Necromancy.BONE_PATH, 1)));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.ritual(ThaumaturgicalRitual.CRAFT_BLOODSTONE).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.ritual(ThaumaturgicalRitual.BURNING_BLADE).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.ritual(NecromanticRitual.BLACK_BLOOD).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.ritual(NecromanticRitual.DARK_ASSISTANT).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.technique(Technique.ARMOR_OF_DARKNESS).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.technique(Technique.CONTROL_THE_SAVAGE_BEAST).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.elderPower(ElderPower.CLAIRVOYANCE).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.elderPower(ElderPower.ACID_BLOOD).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(Merit.THAUMATURGIC_TRAINING, "Path of Corruption", experienceChart.inClanPower(Thaumaturgy.PATH_OF_CORRUPTION).add()).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(Merit.NECROMANTIC_TRAINING, "Ash Path", experienceChart.inClanPower(Necromancy.ASH_PATH).add()).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(Merit.ADDITIONAL_COMMON_DISCIPLINE, "Dominate", experienceChart.inClanPower(Discipline.DOMINATE).add()).buy()));
    }

    private void backoutSomeOfThoseChanges() {
//    	PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
//        ExperienceChart experienceChart = ExperienceChart.chartFor(getMaryWollstonecraft());
        
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.skill(Skill.ACADEMICS).withRating(3).withFocus("Philosophy").withFocus("Latin Poetry").withFocus("Greek Poetry").buy().remove().and(experienceChart.skill(Skill.ACADEMICS).withRating(2).withFocus("Philosophy").withFocus("Latin Poetry").add())));
//        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(ClanSpecificMerit.ARTISTS_BLESSING).withDetails("Writing").withTraitChange(experienceChart.skill(Skill.CRAFTS).withSpecialization("Poetry").withRating(3).add()).buy().remove()));
        
//        approveChangesOnMary();
    }
    
	private PlayerCharacter getMaryWollstonecraft() {
		TroupeRepository troupeRepository = appConfig.getBean(TroupeRepository.class);
		Troupe troupe = troupeRepository.findNamed(TROUPE_NAME);
        Player player = troupe.getPlayers().stream().filter((Player p) -> p.getEmail().equals(PLAYER_EMAIL)).findFirst().get();
        return player.getCharacters().stream().filter((PlayerCharacter pc) -> pc.getName().equals("Mary Wollstonecraft")).findFirst().get();
	}

    private void approveChangesOnMary() {
        PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
        characterRepository.update(getMaryWollstonecraft().approvePendingChanges());
    }

}
