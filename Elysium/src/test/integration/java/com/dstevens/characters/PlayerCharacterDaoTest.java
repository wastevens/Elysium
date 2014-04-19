package com.dstevens.characters;

import static com.dstevens.collections.Lists.*;
import static com.dstevens.collections.Sets.set;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;

import com.dstevens.characters.attributes.*;
import com.dstevens.characters.skills.*;
import com.dstevens.configuration.ApplicationConfiguration;
import com.dstevens.players.*;

public class PlayerCharacterDaoTest {

    private static final ApplicationContext APP_CONFIG = ApplicationConfiguration.appConfig();
    
    private PlayerCharacterDao characterDao;
    private PlayerCharacterFactory characterFactory;
    private CharacterSkillFactory skillFactory;
    
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
        skillFactory = APP_CONFIG.getBean(CharacterSkillFactory.class);
        
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
        CharacterSkill athletics = skillFactory.skillFor(character, Skill.ATHLETICS, 2);
        CharacterSkill pottery = skillFactory.skillFor(character, Skill.CRAFTS, 3, "Pottery");
        CharacterSkill painting = skillFactory.skillFor(character, Skill.CRAFTS, 4, "Painting");
        CharacterSkill academics = skillFactory.skillFor(character, Skill.ACADEMICS, 5, set("Underwater Basket Weaving", "Ancient Greek Poems"));
        
        characterDao.save(character.withSkill(athletics).withSkill(pottery).withSkill(painting).withSkill(academics));
        PlayerCharacter characterWithSkills = characterDao.findOne(character.getId());
        assertEquals(set(athletics, pottery, painting, academics), characterWithSkills.getSkills());
        
        List<CharacterSkill> sortedSkills = sort(listFrom(character.getSkills()));
        assertEquals(Skill.ACADEMICS, sortedSkills.get(0).getSkill());
        assertEquals(5, sortedSkills.get(0).getRating());
        assertEquals(set("Underwater Basket Weaving", "Ancient Greek Poems"), sortedSkills.get(0).getFocuses());
        
        assertEquals(Skill.ATHLETICS, sortedSkills.get(1).getSkill());
        assertEquals(2, sortedSkills.get(1).getRating());
        
        assertEquals(Skill.CRAFTS, sortedSkills.get(2).getSkill());
        assertEquals(4, sortedSkills.get(2).getRating());
        assertEquals("Painting", sortedSkills.get(2).getSpecialization());
        
        assertEquals(Skill.CRAFTS, sortedSkills.get(3).getSkill());
        assertEquals(3, sortedSkills.get(3).getRating());
        assertEquals("Pottery", sortedSkills.get(3).getSpecialization());
        
        
        
    }
}
