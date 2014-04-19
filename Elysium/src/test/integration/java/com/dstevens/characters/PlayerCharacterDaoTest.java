package com.dstevens.characters;

import static com.dstevens.collections.Sets.set;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.springframework.context.ApplicationContext;

import com.dstevens.characters.attributes.PhysicalAttributeFocus;
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

    @Before
    public void setUp() {
        troupeDao       = APP_CONFIG.getBean(TroupeDao.class);
        troupeFactory    = APP_CONFIG.getBean(TroupeFactory.class);
        playerDao       = APP_CONFIG.getBean(PlayerDao.class);
        playerFactory    = APP_CONFIG.getBean(PlayerFactory.class);
        characterDao       = APP_CONFIG.getBean(PlayerCharacterDao.class);
        characterFactory    = APP_CONFIG.getBean(PlayerCharacterFactory.class);
    }
    
    @Test
    public void testSave() {
        Player player = playerDao.save(playerFactory.createPlayer("player name", "player email"));
        Troupe troupe = troupeDao.save(troupeFactory.createTroupe("troupe name", Setting.ANARCH));
        PlayerCharacter unsaved = characterFactory.createPlayerCharacter(troupe, player, "character name");
        PlayerCharacter character = characterDao.save(unsaved);
        
        assertEquals(playerDao.findOne(player.getId()).getCharacters(), set(character));
        assertEquals(troupeDao.findOne(troupe.getId()).getCharacters(), set(character));
        assertEquals(characterDao.findOne(character.getId()).getTroupe(), troupe);
        assertEquals(characterDao.findOne(character.getId()).getPlayer(), player);
        
        PlayerCharacter savedCharacter = characterDao.save(character.withPhysicalAttribute(character.getPhysicalAttribute().withRating(7).withFocus(PhysicalAttributeFocus.STAMINA)));
        
        PlayerCharacter foundCharacter = characterDao.findOne(character.getId());
        assertEquals(7, foundCharacter.getPhysicalAttribute().getRating());
        assertEquals(set(PhysicalAttributeFocus.STAMINA), foundCharacter.getPhysicalAttribute().getFocuses());
        assertEquals(set(PhysicalAttributeFocus.STAMINA), character.getPhysicalAttribute().getFocuses());
        assertEquals(set(PhysicalAttributeFocus.STAMINA), savedCharacter.getPhysicalAttribute().getFocuses());
        
        characterDao.delete(character.getId());
        
        assertEquals(playerDao.findOne(player.getId()).getCharacters(), set());
        assertEquals(troupeDao.findOne(troupe.getId()).getCharacters(), set());
        assertEquals(characterDao.count(), 0);
    }
    
}
