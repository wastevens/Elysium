package com.dstevens.characters;

import static com.dstevens.collections.Sets.set;
import static org.junit.Assert.assertEquals;
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
        characterDao.save(character.withSkill(new CharacterSkill(Skill.ATHLETICS, 2)).
                withSkill(new CharacterSkill(Skill.CRAFTS, 3, "Pottery")));

        PlayerCharacter characterWithSkills = characterDao.findOne(character.getId());
        assertEquals(set(new CharacterSkill(Skill.ATHLETICS, 2), new CharacterSkill(Skill.CRAFTS, 3, "Pottery")), 
                     characterWithSkills.getSkills());
    }
}
