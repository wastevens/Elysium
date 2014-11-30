package com.dstevens.characters;

import static com.dstevens.collections.Sets.set;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.dstevens.characters.attributes.MentalAttributeFocus;
import com.dstevens.characters.attributes.PhysicalAttributeFocus;
import com.dstevens.characters.attributes.SocialAttributeFocus;
import com.dstevens.characters.backgrounds.Background;
import com.dstevens.characters.backgrounds.CharacterBackground;
import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.distinctions.CharacterFlaw;
import com.dstevens.characters.distinctions.CharacterMerit;
import com.dstevens.characters.distinctions.ClanSpecificMerit;
import com.dstevens.characters.distinctions.GeneralFlaw;
import com.dstevens.characters.distinctions.GeneralMerit;
import com.dstevens.characters.powers.CharacterDiscipline;
import com.dstevens.characters.powers.Discipline;
import com.dstevens.characters.skills.CharacterSkill;
import com.dstevens.characters.skills.Skill;
import com.dstevens.configuration.ApplicationConfiguration;
import com.dstevens.game.ExperienceChart;
import com.dstevens.players.Player;
import com.dstevens.players.PlayerRepository;
import com.dstevens.players.Setting;
import com.dstevens.players.Troupe;
import com.dstevens.players.TroupeRepository;

public class AddCharacterTest {

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
		assertEquals(set(CharacterSkill.skillFor(Skill.ACADEMICS, 1, set("Philosophy")),
				         CharacterSkill.skillFor(Skill.AWARENESS, 1),
				         CharacterSkill.skillFor(Skill.COMPUTER, 1),
				         CharacterSkill.skillFor(Skill.LEADERSHIP, 1),
				         CharacterSkill.skillFor(Skill.DODGE, 2),
				         CharacterSkill.skillFor(Skill.EMPATHY, 2),
				         CharacterSkill.skillFor(Skill.STEALTH, 2),
				         CharacterSkill.skillFor(Skill.SUBTERFUGE, 3),
				         CharacterSkill.skillFor(Skill.INVESTIGATION, 3),
				         CharacterSkill.skillFor(Skill.CRAFTS, 4, "Writing")), 
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
		assertEquals(30, maryWollstonecraftWhenNewlyCreated.getXp());
		
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
		assertEquals(set(CharacterSkill.skillFor(Skill.ACADEMICS, 1, set("Philosophy")),
				         CharacterSkill.skillFor(Skill.AWARENESS, 1),
				         CharacterSkill.skillFor(Skill.COMPUTER, 1),
				         CharacterSkill.skillFor(Skill.LEADERSHIP, 1),
				         CharacterSkill.skillFor(Skill.DODGE, 2),
				         CharacterSkill.skillFor(Skill.EMPATHY, 2),
				         CharacterSkill.skillFor(Skill.STEALTH, 2),
				         CharacterSkill.skillFor(Skill.SUBTERFUGE, 3),
				         CharacterSkill.skillFor(Skill.INVESTIGATION, 3),
				         CharacterSkill.skillFor(Skill.CRAFTS, 4, "Writing")), 
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
		assertEquals(30, maryWollstonecraftWhenNewlyCreated.getXp());
		
        approveSpendingXpForMaryWollstonecraft();
        
        PlayerCharacter maryWollstonecraftWithExperienceSpentAndApproved = getMaryWollstonecraft();
        assertEquals(maryWollstonecraftWithExperienceSpentAndApproved.getClan(), Clan.TOREADOR);
        assertEquals(maryWollstonecraftWithExperienceSpentAndApproved.getBloodline(), Bloodline.TOREADOR);
		assertEquals(4, maryWollstonecraftWithExperienceSpentAndApproved.getPhysicalAttribute());
		assertEquals(set(PhysicalAttributeFocus.DEXTERITY), maryWollstonecraftWithExperienceSpentAndApproved.getPhysicalAttributeFocuses());
		assertEquals(8, maryWollstonecraftWithExperienceSpentAndApproved.getSocialAttribute());
		assertEquals(set(SocialAttributeFocus.MANIPULATION), maryWollstonecraftWithExperienceSpentAndApproved.getSocialAttributeFocuses());
		assertEquals(10, maryWollstonecraftWithExperienceSpentAndApproved.getMentalAttribute());
		assertEquals(set(MentalAttributeFocus.PERCEPTION, MentalAttributeFocus.WITS), maryWollstonecraftWithExperienceSpentAndApproved.getMentalAttributeFocuses());
		assertEquals(set(CharacterSkill.skillFor(Skill.ACADEMICS, 3, set("Philosophy", "Latin Poetry", "Greek Poetry")),
				         CharacterSkill.skillFor(Skill.AWARENESS, 1),
				         CharacterSkill.skillFor(Skill.COMPUTER, 1),
				         CharacterSkill.skillFor(Skill.LEADERSHIP, 1),
				         CharacterSkill.skillFor(Skill.DODGE, 2),
				         CharacterSkill.skillFor(Skill.EMPATHY, 2),
				         CharacterSkill.skillFor(Skill.STEALTH, 2),
				         CharacterSkill.skillFor(Skill.SUBTERFUGE, 3),
				         CharacterSkill.skillFor(Skill.INVESTIGATION, 3),
				         CharacterSkill.skillFor(Skill.CRAFTS, 4, "Writing"),
				         CharacterSkill.skillFor(Skill.CRAFTS, 3, "Poetry")), 
				     maryWollstonecraftWithExperienceSpentAndApproved.getSkills());
		assertEquals(set(CharacterBackground.backgroundFor(Background.FAME, 3, "Writing"),
				         CharacterBackground.backgroundFor(Background.RESOURCES, 2),
				         CharacterBackground.backgroundFor(Background.GENERATION, 1)), 
		             maryWollstonecraftWithExperienceSpentAndApproved.getBackgrounds());
		assertEquals(set(new CharacterDiscipline(Discipline.AUSPEX, 1), 
				         new CharacterDiscipline(Discipline.CELERITY, 1), 
				         new CharacterDiscipline(Discipline.PRESENCE, 2)), 
   		             maryWollstonecraftWithExperienceSpentAndApproved.getDisciplines());
		assertEquals(set(new CharacterMerit(ClanSpecificMerit.ARTISTS_BLESSING, "Poetry"),
				         new CharacterMerit(GeneralMerit.LUCKY),
				         new CharacterMerit(GeneralMerit.VERSATILE, "Wits")), 
				     maryWollstonecraftWithExperienceSpentAndApproved.getMerits());
		assertEquals(set(new CharacterFlaw(GeneralFlaw.CURIOUSITY),
		                 new CharacterFlaw(GeneralFlaw.LESSER_GENERATION_2)), 
		             maryWollstonecraftWithExperienceSpentAndApproved.getFlaws());
		assertEquals(2, maryWollstonecraftWithExperienceSpentAndApproved.getXp());
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
                                             withInClanDisciplines(Discipline.PRESENCE).
                                             withInClanDisciplines(Discipline.CELERITY).
                                             withInClanDisciplines(Discipline.AUSPEX).
                                             withPhysicalAttribute(3).withPhysicalAttributeFocus(PhysicalAttributeFocus.DEXTERITY).
                                             withSocialAttribute(5).withSocialAttributeFocus(SocialAttributeFocus.MANIPULATION).
                                             withMentalAttribute(7).withMentalAttributeFocus(MentalAttributeFocus.PERCEPTION).
                                             withSkill(CharacterSkill.skillFor(Skill.ACADEMICS, 1, set("Philosophy"))).
                                             withSkill(CharacterSkill.skillFor(Skill.AWARENESS, 1)).
                                             withSkill(CharacterSkill.skillFor(Skill.COMPUTER, 1)).
                                             withSkill(CharacterSkill.skillFor(Skill.LEADERSHIP, 1)).
                                             withSkill(CharacterSkill.skillFor(Skill.DODGE, 2)).
                                             withSkill(CharacterSkill.skillFor(Skill.EMPATHY, 2)).
                                             withSkill(CharacterSkill.skillFor(Skill.STEALTH, 2)).
                                             withSkill(CharacterSkill.skillFor(Skill.SUBTERFUGE, 3)).
                                             withSkill(CharacterSkill.skillFor(Skill.INVESTIGATION, 3)).
                                             withSkill(CharacterSkill.skillFor(Skill.CRAFTS, 4, "Writing")).
                                             withBackground(CharacterBackground.backgroundFor(Background.FAME, 3, "Writing")).
                                             withBackground(CharacterBackground.backgroundFor(Background.RESOURCES, 2)).
                                             withBackground(CharacterBackground.backgroundFor(Background.GENERATION, 1)).
                                             withDiscipline(new CharacterDiscipline(Discipline.PRESENCE, 2)).
                                             withDiscipline(new CharacterDiscipline(Discipline.CELERITY, 1)).
                                             withDiscipline(new CharacterDiscipline(Discipline.AUSPEX, 1)).
                                             setXp(30));
        characterRepository.update(saved);
    }
    

    private void spendXpForMaryWollstonecraft() {
        PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
        ExperienceChart experienceChart = ExperienceChart.chartFor(getMaryWollstonecraft());
        
        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.skill(Skill.ACADEMICS).withRating(2).withFocus("Philosophy").withFocus("Latin Poetry").buy()));
        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.skill(Skill.ACADEMICS).withRating(3).withFocus("Philosophy").withFocus("Latin Poetry").withFocus("Greek Poetry").buy()));
        characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(ClanSpecificMerit.ARTISTS_BLESSING).withDetails("Poetry").withTraitChange(experienceChart.skill(Skill.CRAFTS).withSpecialization("Poetry").withRating(3).add()).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(GeneralMerit.LUCKY).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.merit(GeneralMerit.VERSATILE).withDetails("Wits").withTraitChange(experienceChart.mentalAttributeFocus(MentalAttributeFocus.WITS).add()).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.flaw(GeneralFlaw.CURIOUSITY).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.flaw(GeneralFlaw.LESSER_GENERATION_2).buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.physicalAttribute().buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.socialAttribute().buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.socialAttribute().buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.socialAttribute().buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.mentalAttribute().buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.mentalAttribute().buy()));
		characterRepository.update(getMaryWollstonecraft().withTraitChangeEvent(experienceChart.mentalAttribute().buy()));
    }

	private PlayerCharacter getMaryWollstonecraft() {
		TroupeRepository troupeRepository = appConfig.getBean(TroupeRepository.class);
		Troupe troupe = troupeRepository.findNamed(TROUPE_NAME);
        Player player = troupe.getPlayers().stream().filter((Player p) -> p.getEmail().equals(PLAYER_EMAIL)).findFirst().get();
        return player.getCharacters().stream().filter((PlayerCharacter pc) -> pc.getName().equals("Mary Wollstonecraft")).findFirst().get();
	}

    private void approveSpendingXpForMaryWollstonecraft() {
        PlayerCharacterRepository characterRepository = appConfig.getBean(PlayerCharacterRepository.class);
        
        PlayerCharacter character = getMaryWollstonecraft();
        
        characterRepository.update(character.approvePendingChanges());
    }

}
