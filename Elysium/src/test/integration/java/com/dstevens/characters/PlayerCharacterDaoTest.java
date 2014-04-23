package com.dstevens.characters;

import static com.dstevens.collections.Lists.*;
import static com.dstevens.collections.Sets.set;
import static com.dstevens.testing.Assertions.assertListsEqual;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;

import com.dstevens.characters.attributes.*;
import com.dstevens.characters.backgrounds.*;
import com.dstevens.characters.changes.*;
import com.dstevens.characters.clans.*;
import com.dstevens.characters.merits.*;
import com.dstevens.characters.powers.*;
import com.dstevens.characters.powers.magics.*;
import com.dstevens.characters.skills.*;
import com.dstevens.configuration.ApplicationConfiguration;
import com.dstevens.players.*;

public class PlayerCharacterDaoTest {

    private static final ApplicationContext APP_CONFIG = ApplicationConfiguration.appConfig();
    
    private PlayerCharacterDao characterDao;
    private PlayerCharacterFactory characterFactory;
    private CharacterSkillFactory skillFactory;
    private CharacterBackgroundFactory backgroundFactory;
    
    private TroupeDao troupeDao;
    private TroupeFactory troupeFactory;
    private PlayerDao playerDao;
    private PlayerFactory playerFactory;
    
    private TraitChangeBuilder traitChangeBuilder;
    private TraitChangeFactory traitChangeFactory;
    
    private Troupe troupe;
    private Player player;
    private PlayerCharacter character;

    @Before
    public void setUp() {
        troupeDao       = APP_CONFIG.getBean(TroupeDao.class);
        troupeFactory    = APP_CONFIG.getBean(TroupeFactory.class);
        playerDao       = APP_CONFIG.getBean(PlayerDao.class);
        playerFactory    = APP_CONFIG.getBean(PlayerFactory.class);
        characterDao       = APP_CONFIG.getBean(PlayerCharacterDao.class);
        characterFactory    = APP_CONFIG.getBean(PlayerCharacterFactory.class);
        skillFactory = APP_CONFIG.getBean(CharacterSkillFactory.class);
        backgroundFactory = APP_CONFIG.getBean(CharacterBackgroundFactory.class);
        traitChangeBuilder = APP_CONFIG.getBean(TraitChangeBuilder.class);
        traitChangeFactory = APP_CONFIG.getBean(TraitChangeFactory.class);
        
        troupe = troupeDao.save(troupeFactory.createTroupe("troupe name", Setting.ANARCH));
        player = playerDao.save(playerFactory.createPlayer("player name", "player email").joinTroupe(troupe));
        character = characterDao.save(characterFactory.createPlayerCharacter(troupe, player, "character name"));
    }
    
    @After
    public void tearDown() {
        troupeDao.delete(troupe.getId());
        playerDao.delete(player.getId());
        characterDao.delete(character.getId());
    }
    
    @Test
    public void testSave() {
        assertEquals(playerDao.findOne(player.getId()).getCharacters(), set(character));
        assertEquals(troupeDao.findOne(troupe.getId()).getCharacters(), set(character));
        assertEquals(characterDao.findOne(character.getId()).getTroupe(), troupe);
        assertEquals(characterDao.findOne(character.getId()).getPlayer(), player);
        
        characterDao.save(character.ofClan(Clan.VENTRUE).ofBloodline(Bloodline.COYOTE));
        PlayerCharacter characterWithClan = characterDao.findOne(character.getId());
        
        assertEquals(Clan.VENTRUE, characterWithClan.getClan());
        assertEquals(Bloodline.COYOTE, characterWithClan.getBloodline());
    }
    
    @Test
    public void testSaveWithAttributes() {
        characterDao.save(character.withPhysicalAttribute(character.getPhysicalAttribute().withRating(7).withFocus(PhysicalAttribute.Focus.STAMINA)).
                withMentalAttribute(character.getMentalAttribute().withRating(5).withFocus(MentalAttribute.Focus.INTELLIGENCE).withFocus(MentalAttribute.Focus.PERCEPTION)).
                withSocialAttribute(character.getSocialAttribute().withRating(3).withFocus(SocialAttribute.Focus.CHARISMA).withFocus(SocialAttribute.Focus.APPEARANCE).withFocus(SocialAttribute.Focus.MANIPULATION)));

        PlayerCharacter foundCharacter = characterDao.findOne(character.getId());
        assertEquals(7, foundCharacter.getPhysicalAttribute().getRating());
        assertEquals(set(PhysicalAttribute.Focus.STAMINA), foundCharacter.getPhysicalAttribute().getFocuses());
        assertEquals(5, foundCharacter.getMentalAttribute().getRating());
        assertEquals(set(MentalAttribute.Focus.INTELLIGENCE, MentalAttribute.Focus.PERCEPTION), foundCharacter.getMentalAttribute().getFocuses());
        assertEquals(3, foundCharacter.getSocialAttribute().getRating());
        assertEquals(set(SocialAttribute.Focus.CHARISMA, SocialAttribute.Focus.MANIPULATION, SocialAttribute.Focus.APPEARANCE), foundCharacter.getSocialAttribute().getFocuses());
    }
    
    @Test
    public void testSaveWithSkills() {
        CharacterSkill athletics = skillFactory.skillFor(Skill.ATHLETICS, 2);
        CharacterSkill pottery = skillFactory.skillFor(Skill.CRAFTS, 3, "Pottery");
        CharacterSkill painting = skillFactory.skillFor(Skill.CRAFTS, 4, "Painting");
        CharacterSkill academics = skillFactory.skillFor(Skill.ACADEMICS, 5, set("Underwater Basket Weaving", "Ancient Greek Poems"));
        
        characterDao.save(character.withSkill(athletics).withSkill(pottery).withSkill(painting).withSkill(academics));
        PlayerCharacter characterWithSkills = characterDao.findOne(character.getId());
        
        List<CharacterSkill> sortedExpected = sort(listFrom(set(athletics, pottery, painting, academics)));
        List<CharacterSkill> sortedActual = sort(listFrom(characterWithSkills.getSkills()));
        assertListsEqual(sortedExpected, sortedActual);
        
        for (int i=0;i<sortedExpected.size();i++) {
            assertSkillValuesEqual(sortedExpected.get(i), sortedActual.get(i));
        }
    }
    
    private void assertSkillValuesEqual(CharacterSkill expected, CharacterSkill actual) {
        assertEquals(expected.getSkill(), actual.getSkill());
        assertEquals(expected.getFocuses(), actual.getFocuses());
        assertEquals(expected.getRating(), actual.getRating());
        assertEquals(expected.getSpecialization(), actual.getSpecialization());
    }
    
    @Test
    public void testSaveWithBackground() {
        CharacterBackground resources = backgroundFactory.backgroundFor(Background.RESOURCES, 2);
        CharacterBackground contacts = backgroundFactory.backgroundFor(Background.CONTACTS, 3, set("Bob", "Joe"));
        CharacterBackground alternateIdentity1 = backgroundFactory.backgroundFor(Background.ALTERNATE_IDENTITY, 1, "Jimmy");
        CharacterBackground alternateIdentity2 = backgroundFactory.backgroundFor(Background.ALTERNATE_IDENTITY, 1, "Johny");
        CharacterBackground haven1 = backgroundFactory.backgroundFor(Background.HAVEN, 2, "That place", set("Location", "Luxury"));
        CharacterBackground haven2 = backgroundFactory.backgroundFor(Background.HAVEN, 2, "That other place", set("Location", "Security"));
        
        
        characterDao.save(character.withBackground(haven2).
                                    withBackground(haven1).
                                    withBackground(resources).
                                    withBackground(alternateIdentity2).
                                    withBackground(contacts).
                                    withBackground(alternateIdentity1));
        PlayerCharacter characterWithBackgrounds = characterDao.findOne(character.getId());
        
        List<CharacterBackground> sortedExpected = sort(listFrom(set(resources, contacts, alternateIdentity1, alternateIdentity2, haven1, haven2)));
        List<CharacterBackground> sortedActual = sort(listFrom(characterWithBackgrounds.getBackgrounds()));
        assertListsEqual(sortedExpected, sortedActual);
        for (int i=0;i<sortedExpected.size();i++) {
            assertBackgroundValuesEqual(sortedExpected.get(i), sortedActual.get(i));
        }
    }
    
    private void assertBackgroundValuesEqual(CharacterBackground expected, CharacterBackground actual) {
        assertEquals(expected.getBackground(), actual.getBackground());
        assertEquals(expected.getFocuses(), actual.getFocuses());
        assertEquals(expected.getRating(), actual.getRating());
        assertEquals(expected.getSpecialization(), actual.getSpecialization());
    }
    
    @Test
    public void testSaveWithPowers() {
        characterDao.save(character.withDiscipline(new CharacterDiscipline(Discipline.ANIMALISM, 2)).
                                    withDiscipline(new CharacterDiscipline(Discipline.QUIETUS, 4)).
                                    withElderPower(ElderPower.ACID_BLOOD).
                                    withElderPower(ElderPower.CRIMSON_FURY).
                                    withTechnique(Technique.AN_DA_SHEALLADH).
                                    withTechnique(Technique.FEARFUL_BLOW).
                                    withInClanDisciplines(Discipline.AUSPEX).
                                    withInClanDisciplines(Discipline.CHIMERSTRY).
                                    withInClanDisciplines(Thaumaturgy.MOVEMENT_OF_THE_MIND).
                                    withInClanDisciplines(Thaumaturgy.PATH_OF_CONJURING).
                                    withInClanDisciplines(Necromancy.BONE_PATH).
                                    withInClanDisciplines(Necromancy.SEPULCHRE_PATH)
                                    );
        
        PlayerCharacter characterWithPowers = characterDao.findOne(character.getId());
        
        assertEquals(set(new CharacterDiscipline(Discipline.ANIMALISM, 2), new CharacterDiscipline(Discipline.QUIETUS, 4)),
                     characterWithPowers.getDisciplines());
        assertEquals(set(ElderPower.ACID_BLOOD, ElderPower.CRIMSON_FURY),
                     characterWithPowers.getElderPowers());
        assertEquals(set(Technique.AN_DA_SHEALLADH, Technique.FEARFUL_BLOW),
                     characterWithPowers.getTechniques());
        assertEquals(set((Power) Discipline.AUSPEX, Discipline.CHIMERSTRY, Thaumaturgy.MOVEMENT_OF_THE_MIND, Thaumaturgy.PATH_OF_CONJURING, Necromancy.BONE_PATH, Necromancy.SEPULCHRE_PATH),
                     characterWithPowers.getInClanDisciplines());
    }
    
    @Test
    public void testSaveWithThaumaturgy() {
        characterDao.save(character.withThaumaturgicalPath(new CharacterThaumaturgy(Thaumaturgy.LURE_OF_FLAMES, 2)).
                                    withThaumaturgicalPath(new CharacterThaumaturgy(Thaumaturgy.PATH_OF_CONJURING, 3)).
                                    setPrimaryThaumaturgicalPath(Thaumaturgy.PATH_OF_CONJURING).
                                    withThaumaturgicalRitual(ThaumaturgicalRitual.BANISH_BIG_BROTHER).
                                    withThaumaturgicalRitual(ThaumaturgicalRitual.DETECT_THE_HIDDEN_OBSERVER));
        
        PlayerCharacter characterWithPowers = characterDao.findOne(character.getId());
        
        assertEquals(set(new CharacterThaumaturgy(Thaumaturgy.LURE_OF_FLAMES, 2), new CharacterThaumaturgy(Thaumaturgy.PATH_OF_CONJURING, 3)),
                     characterWithPowers.getThaumaturgicalPaths());
        assertEquals(Thaumaturgy.PATH_OF_CONJURING, characterWithPowers.getPrimaryThaumaturgicalPath());
        assertEquals(set(ThaumaturgicalRitual.BANISH_BIG_BROTHER, ThaumaturgicalRitual.DETECT_THE_HIDDEN_OBSERVER),
                     characterWithPowers.getThaumaturgicalRituals());
        
    }
    
    @Test
    public void testSaveWithNecromancy() {
        characterDao.save(character.withNecromanticPath(new CharacterNecromancy(Necromancy.ASH_PATH, 2)).
                                    withNecromanticPath(new CharacterNecromancy(Necromancy.MORTIS_PATH, 3)).
                                    setPrimaryNecromanticPath(Necromancy.MORTIS_PATH).
                                    withNecromanticRitual(NecromanticRitual.BASTONE_DIABOLICO).
                                    withNecromanticRitual(NecromanticRitual.MOLDERING_PRESENCE));
        
        PlayerCharacter characterWithPowers = characterDao.findOne(character.getId());
        
        assertEquals(set(new CharacterNecromancy(Necromancy.ASH_PATH, 2), new CharacterNecromancy(Necromancy.MORTIS_PATH, 3)),
                     characterWithPowers.getNecromanticPaths());
        assertEquals(Necromancy.MORTIS_PATH, characterWithPowers.getPrimaryNecromanticPath());
        assertEquals(set(NecromanticRitual.BASTONE_DIABOLICO, NecromanticRitual.MOLDERING_PRESENCE),
                     characterWithPowers.getNecromanticRituals());
    }
    
    @Test
    public void testSaveWithMeritsAndFlaws() {
        characterDao.save(character.withMerit(new CharacterMerit(RarityMerit.RARE)).
                                    withMerit(new CharacterMerit(ClanSpecificMerit.PARAGON, "Bob the Ventrue Assistant")).
                                    withFlaw(new CharacterFlaw(GeneralFlaw.BAD_SIGHT)).
                                    withFlaw(new CharacterFlaw(GeneralFlaw.ADDICTION, "To meth!")).
                                    withFlaw(new CharacterFlaw(SettingSpecificFlaw.DUBIOUS_LOYALTIES)));
        
        PlayerCharacter characterWithMerits = characterDao.findOne(character.getId());
        
        assertEquals(set(new CharacterMerit(RarityMerit.RARE), new CharacterMerit(ClanSpecificMerit.PARAGON, "Bob the Ventrue Assistant")),
                     characterWithMerits.getMerits());
        assertEquals(set(new CharacterFlaw(GeneralFlaw.BAD_SIGHT), new CharacterFlaw(GeneralFlaw.ADDICTION, "To meth!"), new CharacterFlaw(SettingSpecificFlaw.DUBIOUS_LOYALTIES)), 
                     characterWithMerits.getFlaws());
        
    }
    
    @Test
    public void testTraitChanges() {
        characterDao.save(character.withTraitChangeEvent(traitChangeBuilder.setSkill(Skill.ACADEMICS, 2).withFocuses(set("Reading", "Writing")).getEvent()).
                                    withTraitChangeEvent(traitChangeBuilder.setSkill(Skill.CRAFTS, 3).withSpecialization("Pottery").getEvent()).
                                    withTraitChangeEvent(traitChangeBuilder.setSkill(Skill.CRAFTS, 3).withSpecialization("Writing").getEvent()).
                                    withTraitChangeEvent(traitChangeBuilder.setSkill(Skill.ATHLETICS, 4).getEvent()).
                                    withTraitChangeEvent(traitChangeBuilder.setBackground(Background.GENERATION, 1).getEvent()).
                                    withTraitChangeEvent(traitChangeBuilder.setBackground(Background.FAME, 2).withSpecialization("Pottery").getEvent()).
                                    withTraitChangeEvent(traitChangeBuilder.setBackground(Background.ALLIES, 3).withFocuses(set("Bob", "Jim", "George")).getEvent()).
                                    withTraitChangeEvent(traitChangeBuilder.setBackground(Background.HAVEN, 4).withSpecialization("My House").withFocuses(set("Location", "Security", "Wards", "Luxury")).getEvent()).
                                    withTraitChangeEvent(traitChangeBuilder.gainXp(10)).
                                    withTraitChangeEvent(traitChangeBuilder.spendXp(1))
                                    );
        
        PlayerCharacter characterWithPendingChanges = findCharacterWithPendingChanges();
        
        characterDao.save(characterWithPendingChanges.approvePendingChanges(traitChangeFactory));
        
        PlayerCharacter characterWithApprovedChanges = verifyThatAllPendingChangesApproved();
                
        verifyThatMultipleApprovalsDoesNotApplyMultipleTimes(characterWithApprovedChanges);
    }

    private void verifyThatMultipleApprovalsDoesNotApplyMultipleTimes(PlayerCharacter characterWithApprovedChanges) {
        PlayerCharacter twiceApproved = characterWithApprovedChanges.approvePendingChanges(traitChangeFactory);
        
        assertEquals(false, twiceApproved.getTraitChangeEvents().stream().anyMatch(((TraitChange t) -> t.isPending())));
        assertEquals(true, twiceApproved.getTraitChangeEvents().stream().allMatch(((TraitChange t) -> !t.isPending())));
        
        assertEquals(4, twiceApproved.getSkills().size());
        List<CharacterSkill> skills = sort(listFrom(twiceApproved.getSkills()));
        
        assertExpectedSkill(new CharacterSkill("", Skill.ACADEMICS, 2, null, set("Reading", "Writing")), skills.get(0));
        assertExpectedSkill(new CharacterSkill("", Skill.ATHLETICS, 4, null, set()), skills.get(1));
        assertExpectedSkill(new CharacterSkill("", Skill.CRAFTS, 3, "Pottery", set()), skills.get(2));
        assertExpectedSkill(new CharacterSkill("", Skill.CRAFTS, 3, "Writing", set()), skills.get(3));
        
        List<CharacterBackground> backgrounds = sort(listFrom(twiceApproved.getBackgrounds()));  
        assertEquals(4, twiceApproved.getBackgrounds().size());
        backgrounds = sort(listFrom(twiceApproved.getBackgrounds()));
        assertExpectedBackground(new CharacterBackground("", Background.ALLIES, 3, null, set("Bob", "Jim", "George")), backgrounds.get(0));
        assertExpectedBackground(new CharacterBackground("", Background.FAME, 2, "Pottery", set()), backgrounds.get(1));
        assertExpectedBackground(new CharacterBackground("", Background.GENERATION, 1, null, set()), backgrounds.get(2));
        assertExpectedBackground(new CharacterBackground("", Background.HAVEN, 4, "My House", set("Location", "Security", "Wards", "Luxury")), backgrounds.get(3));
        
        assertEquals(10 - 1, twiceApproved.getXp());
    }

    private PlayerCharacter findCharacterWithPendingChanges() {
        PlayerCharacter characterWithPendingChanges = characterDao.findOne(character.getId());
        
        assertEquals(set(), characterWithPendingChanges.getSkills());
        assertEquals(set(), characterWithPendingChanges.getBackgrounds());
        
        assertEquals(true, characterWithPendingChanges.getTraitChangeEvents().stream().anyMatch(((TraitChange t) -> t.isPending())));
        assertEquals(false, characterWithPendingChanges.getTraitChangeEvents().stream().allMatch(((TraitChange t) -> !t.isPending())));
        assertEquals(0, characterWithPendingChanges.getXp());
        return characterWithPendingChanges;
    }
    
    private PlayerCharacter verifyThatAllPendingChangesApproved() {
        PlayerCharacter characterWithApprovedChanges = characterDao.findOne(character.getId());
        
        assertEquals(false, characterWithApprovedChanges.getTraitChangeEvents().stream().anyMatch(((TraitChange t) -> t.isPending())));
        assertEquals(true, characterWithApprovedChanges.getTraitChangeEvents().stream().allMatch(((TraitChange t) -> !t.isPending())));
        
        assertEquals(4, characterWithApprovedChanges.getSkills().size());
        List<CharacterSkill> skills = sort(listFrom(characterWithApprovedChanges.getSkills()));
        
        assertExpectedSkill(new CharacterSkill("", Skill.ACADEMICS, 2, null, set("Reading", "Writing")), skills.get(0));
        assertExpectedSkill(new CharacterSkill("", Skill.ATHLETICS, 4, null, set()), skills.get(1));
        assertExpectedSkill(new CharacterSkill("", Skill.CRAFTS, 3, "Pottery", set()), skills.get(2));
        assertExpectedSkill(new CharacterSkill("", Skill.CRAFTS, 3, "Writing", set()), skills.get(3));
        
        assertEquals(4, characterWithApprovedChanges.getBackgrounds().size());
        List<CharacterBackground> backgrounds = sort(listFrom(characterWithApprovedChanges.getBackgrounds()));
        assertExpectedBackground(new CharacterBackground("", Background.ALLIES, 3, null, set("Bob", "Jim", "George")), backgrounds.get(0));
        assertExpectedBackground(new CharacterBackground("", Background.FAME, 2, "Pottery", set()), backgrounds.get(1));
        assertExpectedBackground(new CharacterBackground("", Background.GENERATION, 1, null, set()), backgrounds.get(2));
        assertExpectedBackground(new CharacterBackground("", Background.HAVEN, 4, "My House", set("Location", "Security", "Wards", "Luxury")), backgrounds.get(3));
        
        assertEquals(10 - 1, characterWithApprovedChanges.getXp());
        
        return characterWithApprovedChanges;
    }

    private void assertExpectedSkill(CharacterSkill expected, CharacterSkill actual) {
        assertEquals(expected.getSkill(), actual.getSkill());
        assertEquals(expected.getRating(), actual.getRating());
        assertEquals(expected.getSpecialization(), actual.getSpecialization());
        assertEquals(expected.getFocuses(), actual.getFocuses());
    }
    
    private void assertExpectedBackground(CharacterBackground expected, CharacterBackground actual) {
        assertEquals(expected.getBackground(), actual.getBackground());
        assertEquals(expected.getRating(), actual.getRating());
        assertEquals(expected.getSpecialization(), actual.getSpecialization());
        assertEquals(expected.getFocuses(), actual.getFocuses());
    }
}
