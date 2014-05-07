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
import com.dstevens.characters.changes.SetTrait;
import com.dstevens.characters.clans.*;
import com.dstevens.characters.distinctions.*;
import com.dstevens.characters.powers.*;
import com.dstevens.characters.powers.magics.*;
import com.dstevens.characters.skills.*;
import com.dstevens.configuration.ApplicationConfiguration;
import com.dstevens.players.*;

public class PlayerCharacterDaoTest {

    private static final ApplicationContext APP_CONFIG = ApplicationConfiguration.appConfig();
    
    private PlayerCharacterDao characterDao;
    private PlayerCharacterFactory characterFactory;
    
    private TroupeDao troupeDao;
    private TroupeFactory troupeFactory;
    private PlayerDao playerDao;
    private PlayerFactory playerFactory;
    
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
        
        troupe = troupeDao.save(troupeFactory.createTroupe("troupe name", Setting.ANARCH));
        player = playerDao.save(playerFactory.createPlayer("player name", "player email"));
        troupe = troupeDao.save(troupe.withPlayer(player));
        character = characterDao.save(characterFactory.createPlayerCharacter("character name"));
        player = playerDao.save(player.withCharacter(character));
        troupe = troupeDao.save(troupe.withCharacter(character));
    }
    
    @After
    public void tearDown() {
        characterDao.delete(character.getId());
        playerDao.delete(player.getId());
        troupeDao.delete(troupe.getId());
    }
    
    @Test
    public void testSave() {
        assertEquals(set(character), playerDao.findOne(player.getId()).getCharacters());
        assertEquals(set(character), troupeDao.findOne(troupe.getId()).getCharacters());
        
        characterDao.save(character.ofClan(Clan.VENTRUE).ofBloodline(Bloodline.COYOTE));
        PlayerCharacter characterWithClan = characterDao.findOne(character.getId());
        
        assertEquals(characterWithClan.getClan(), Clan.VENTRUE);
        assertEquals(characterWithClan.getBloodline(), Bloodline.COYOTE);
    }
    
    @Test
    public void testSaveWithAttributes() {
        characterDao.save(character.withPhysicalAttribute(7).withPhysicalAttributeFocus(PhysicalAttributeFocus.STAMINA).
                withMentalAttribute(5).withMentalAttributeFocus(MentalAttributeFocus.INTELLIGENCE).withMentalAttributeFocus(MentalAttributeFocus.PERCEPTION).
                withSocialAttribute(3).withSocialAttributeFocus(SocialAttributeFocus.CHARISMA).withSocialAttributeFocus(SocialAttributeFocus.APPEARANCE).withSocialAttributeFocus(SocialAttributeFocus.MANIPULATION));

        PlayerCharacter foundCharacter = characterDao.findOne(character.getId());
        assertEquals(7, foundCharacter.getPhysicalAttribute());
        assertEquals(set(PhysicalAttributeFocus.STAMINA), foundCharacter.getPhysicalAttributeFocuses());
        assertEquals(5, foundCharacter.getMentalAttribute());
        assertEquals(set(MentalAttributeFocus.INTELLIGENCE, MentalAttributeFocus.PERCEPTION), foundCharacter.getMentalAttributeFocuses());
        assertEquals(3, foundCharacter.getSocialAttribute());
        assertEquals(set(SocialAttributeFocus.CHARISMA, SocialAttributeFocus.MANIPULATION, SocialAttributeFocus.APPEARANCE), foundCharacter.getSocialAttributeFocuses());
    }
    
    @Test
    public void testSaveWithSkills() {
        CharacterSkill athletics = CharacterSkill.skillFor(Skill.ATHLETICS, 2);
        CharacterSkill pottery = CharacterSkill.skillFor(Skill.CRAFTS, 3, "Pottery");
        CharacterSkill painting = CharacterSkill.skillFor(Skill.CRAFTS, 4, "Painting");
        CharacterSkill academics = CharacterSkill.skillFor(Skill.ACADEMICS, 5, set("Underwater Basket Weaving", "Ancient Greek Poems"));
        
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
        assertEquals(expected.trait(), actual.trait());
        assertEquals(expected.getFocuses(), actual.getFocuses());
        assertEquals(expected.rating(), actual.rating());
        assertEquals(expected.getSpecialization(), actual.getSpecialization());
    }
    
    @Test
    public void testSaveWithBackground() {
        CharacterBackground resources = CharacterBackground.backgroundFor(Background.RESOURCES, 2);
        CharacterBackground contacts = CharacterBackground.backgroundFor(Background.CONTACTS, 3, set("Bob", "Joe"));
        CharacterBackground alternateIdentity1 = CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 1, "Jimmy");
        CharacterBackground alternateIdentity2 = CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 1, "Johny");
        CharacterBackground haven1 = CharacterBackground.backgroundFor(Background.HAVEN, 2, "That place", set("Location", "Luxury"));
        CharacterBackground haven2 = CharacterBackground.backgroundFor(Background.HAVEN, 2, "That other place", set("Location", "Security"));
        
        
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
        assertEquals(expected.trait(), actual.trait());
        assertEquals(expected.getFocuses(), actual.getFocuses());
        assertEquals(expected.rating(), actual.rating());
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
//        characterDao.save(character.withTraitChangeEvent(traitChangeBuilder.setSkill(Skill.ACADEMICS, 2).withFocuses(set("Reading", "Writing")).getEvent()).
//                                    withTraitChangeEvent(traitChangeBuilder.setSkill(Skill.CRAFTS, 3).withSpecialization("Pottery").getEvent()).
//                                    withTraitChangeEvent(traitChangeBuilder.setSkill(Skill.CRAFTS, 3).withSpecialization("Writing").getEvent()).
//                                    withTraitChangeEvent(traitChangeBuilder.setSkill(Skill.ATHLETICS, 4).getEvent()).
//                                    withTraitChangeEvent(traitChangeBuilder.setBackground(Background.GENERATION, 1).getEvent()).
//                                    withTraitChangeEvent(traitChangeBuilder.setBackground(Background.FAME, 2).withSpecialization("Pottery").getEvent()).
//                                    withTraitChangeEvent(traitChangeBuilder.setBackground(Background.ALLIES, 3).withFocuses(set("Bob", "Jim", "George")).getEvent()).
//                                    withTraitChangeEvent(traitChangeBuilder.setBackground(Background.HAVEN, 4).withSpecialization("My House").withFocuses(set("Location", "Security", "Wards", "Luxury")).getEvent()).
//                                    withTraitChangeEvent(traitChangeBuilder.gainXp(10, null)).
//                                    withTraitChangeEvent(traitChangeBuilder.spendXp(1, null)).
//                                    withTraitChangeEvent(traitChangeBuilder.setDiscipline(Discipline.ANIMALISM, 3)).
//                                    withTraitChangeEvent(traitChangeBuilder.setThaumaturgy(Thaumaturgy.PATH_OF_BLOOD, 2)).
//                                    withTraitChangeEvent(traitChangeBuilder.setNecromancy(Necromancy.ASH_PATH, 2)).
//                                    withTraitChangeEvent(traitChangeBuilder.setThaumaturgicalRitual(ThaumaturgicalRitual.BIND_THE_ACCUSING_TONGUE)).
//                                    withTraitChangeEvent(traitChangeBuilder.setNecromanticRitual(NecromanticRitual.CHILL_OF_OBLIVION)).
//                                    withTraitChangeEvent(traitChangeBuilder.setElderPower(ElderPower.ARMY_OF_APPARITIONS)).
//                                    withTraitChangeEvent(traitChangeBuilder.setTechnique(Technique.ARMOR_OF_DARKNESS)).
//                                    withTraitChangeEvent(traitChangeBuilder.setFlaw(GeneralFlaw.ADDICTION).withSpecialization("Uppers").getEvent()).
//                                    withTraitChangeEvent(traitChangeBuilder.setMerit(GeneralMerit.ACUTE_SENSE).withSpecialization("Eyesight").getEvent())
//                                    );
        
        PlayerCharacter characterWithPendingChanges = findCharacterWithPendingChanges();
        
        assertEquals(set(), characterWithPendingChanges.getFlaws());
        assertEquals(set(), characterWithPendingChanges.getMerits());
        assertEquals(set(), characterWithPendingChanges.getTechniques());
        assertEquals(set(), characterWithPendingChanges.getElderPowers());
        assertEquals(set(), characterWithPendingChanges.getNecromanticRituals());
        assertEquals(set(), characterWithPendingChanges.getThaumaturgicalRituals());
        assertEquals(set(), characterWithPendingChanges.getThaumaturgicalPaths());
        assertEquals(set(), characterWithPendingChanges.getNecromanticPaths());
        assertEquals(set(), characterWithPendingChanges.getDisciplines());
        
        characterDao.save(characterWithPendingChanges.approvePendingChanges());
        
        PlayerCharacter characterWithApprovedChanges = verifyThatAllPendingChangesApproved();
        
        assertEquals(set(new CharacterFlaw(GeneralFlaw.ADDICTION, "Uppers")), characterWithApprovedChanges.getFlaws());
        assertEquals(set(new CharacterMerit(GeneralMerit.ACUTE_SENSE, "Eyesight")), characterWithApprovedChanges.getMerits());
        assertEquals(set(Technique.ARMOR_OF_DARKNESS), characterWithApprovedChanges.getTechniques());
        assertEquals(set(ElderPower.ARMY_OF_APPARITIONS), characterWithApprovedChanges.getElderPowers());
        assertEquals(set(NecromanticRitual.CHILL_OF_OBLIVION), characterWithApprovedChanges.getNecromanticRituals());
        assertEquals(set(ThaumaturgicalRitual.BIND_THE_ACCUSING_TONGUE), characterWithApprovedChanges.getThaumaturgicalRituals());
        assertEquals(set(new CharacterThaumaturgy(Thaumaturgy.PATH_OF_BLOOD, 2)), characterWithApprovedChanges.getThaumaturgicalPaths());
        assertEquals(set(new CharacterNecromancy(Necromancy.ASH_PATH, 2)), characterWithApprovedChanges.getNecromanticPaths());
        assertEquals(set(new CharacterDiscipline(Discipline.ANIMALISM, 3)), characterWithApprovedChanges.getDisciplines());
        
        verifyThatMultipleApprovalsDoesNotApplyMultipleTimes(characterWithApprovedChanges);
    }
    
    private void verifyThatMultipleApprovalsDoesNotApplyMultipleTimes(PlayerCharacter characterWithApprovedChanges) {
        PlayerCharacter twiceApproved = characterWithApprovedChanges.approvePendingChanges();
        
        assertEquals(false, twiceApproved.getTraitChangeEvents().stream().anyMatch(((SetTrait t) -> t.isPending())));
        assertEquals(true, twiceApproved.getTraitChangeEvents().stream().allMatch(((SetTrait t) -> !t.isPending())));
        
        assertEquals(4, twiceApproved.getSkills().size());
        
        List<CharacterSkill> skills = sort(listFrom(twiceApproved.getSkills()));
        
        assertExpectedSkill(CharacterSkill.skillFor(Skill.ATHLETICS, 4, null, set()), skills.get(0));
        assertExpectedSkill(CharacterSkill.skillFor(Skill.CRAFTS, 3, "Pottery", set()), skills.get(1));
        assertExpectedSkill(CharacterSkill.skillFor(Skill.CRAFTS, 3, "Writing", set()), skills.get(2));
        assertExpectedSkill(CharacterSkill.skillFor(Skill.ACADEMICS, 2, null, set("Reading", "Writing")), skills.get(3));
        
        List<CharacterBackground> backgrounds = sort(listFrom(twiceApproved.getBackgrounds()));  
        assertEquals(4, twiceApproved.getBackgrounds().size());
        backgrounds = sort(listFrom(twiceApproved.getBackgrounds()));
        assertExpectedBackground(CharacterBackground.backgroundFor(Background.HAVEN, 4, "My House", set("Location", "Security", "Wards", "Luxury")), backgrounds.get(0));
        assertExpectedBackground(CharacterBackground.backgroundFor(Background.ALLIES, 3, null, set("Bob", "Jim", "George")), backgrounds.get(1));
        assertExpectedBackground(CharacterBackground.backgroundFor(Background.FAME, 2, "Pottery", set()), backgrounds.get(2));
        assertExpectedBackground(CharacterBackground.backgroundFor(Background.GENERATION, 1, null, set()), backgrounds.get(3));
        
        assertEquals(10 - 1, twiceApproved.getXp());
    }

    private PlayerCharacter findCharacterWithPendingChanges() {
        PlayerCharacter characterWithPendingChanges = characterDao.findOne(character.getId());
        
        assertEquals(set(), characterWithPendingChanges.getSkills());
        assertEquals(set(), characterWithPendingChanges.getBackgrounds());
        
        assertEquals(true, characterWithPendingChanges.getTraitChangeEvents().stream().anyMatch(((SetTrait t) -> t.isPending())));
        assertEquals(false, characterWithPendingChanges.getTraitChangeEvents().stream().allMatch(((SetTrait t) -> !t.isPending())));
        assertEquals(0, characterWithPendingChanges.getXp());
        return characterWithPendingChanges;
    }
    
    private PlayerCharacter verifyThatAllPendingChangesApproved() {
        PlayerCharacter characterWithApprovedChanges = characterDao.findOne(character.getId());
        
        assertEquals(false, characterWithApprovedChanges.getTraitChangeEvents().stream().anyMatch(((SetTrait t) -> t.isPending())));
        assertEquals(true, characterWithApprovedChanges.getTraitChangeEvents().stream().allMatch(((SetTrait t) -> !t.isPending())));
        
        assertEquals(4, characterWithApprovedChanges.getSkills().size());
        List<CharacterSkill> skills = sort(listFrom(characterWithApprovedChanges.getSkills()));
        
        assertExpectedSkill(CharacterSkill.skillFor(Skill.ATHLETICS, 4, null, set()), skills.get(0));
        assertExpectedSkill(CharacterSkill.skillFor(Skill.CRAFTS, 3, "Pottery", set()), skills.get(1));
        assertExpectedSkill(CharacterSkill.skillFor(Skill.CRAFTS, 3, "Writing", set()), skills.get(2));
        assertExpectedSkill(CharacterSkill.skillFor(Skill.ACADEMICS, 2, null, set("Reading", "Writing")), skills.get(3));
        
        assertEquals(4, characterWithApprovedChanges.getBackgrounds().size());
        List<CharacterBackground> backgrounds = sort(listFrom(characterWithApprovedChanges.getBackgrounds()));
        assertExpectedBackground(CharacterBackground.backgroundFor(Background.HAVEN, 4, "My House", set("Location", "Security", "Wards", "Luxury")), backgrounds.get(0));
        assertExpectedBackground(CharacterBackground.backgroundFor(Background.ALLIES, 3, null, set("Bob", "Jim", "George")), backgrounds.get(1));
        assertExpectedBackground(CharacterBackground.backgroundFor(Background.FAME, 2, "Pottery", set()), backgrounds.get(2));
        assertExpectedBackground(CharacterBackground.backgroundFor(Background.GENERATION, 1, null, set()), backgrounds.get(3));
        
        assertEquals(10 - 1, characterWithApprovedChanges.getXp());
        
        return characterWithApprovedChanges;
    }

    private void assertExpectedSkill(CharacterSkill expected, CharacterSkill actual) {
        assertEquals(expected.trait(), actual.trait());
        assertEquals(expected.rating(), actual.rating());
        assertEquals(expected.getSpecialization(), actual.getSpecialization());
        assertEquals(expected.getFocuses(), actual.getFocuses());
    }
    
    private void assertExpectedBackground(CharacterBackground expected, CharacterBackground actual) {
        assertEquals(expected.trait(), actual.trait());
        assertEquals(expected.rating(), actual.rating());
        assertEquals(expected.getSpecialization(), actual.getSpecialization());
        assertEquals(expected.getFocuses(), actual.getFocuses());
    }
}
