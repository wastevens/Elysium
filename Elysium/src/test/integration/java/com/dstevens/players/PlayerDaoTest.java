package com.dstevens.players;

import static com.dstevens.testing.Assertions.assertEqualValues;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.springframework.context.ApplicationContext;

import com.dstevens.configuration.ApplicationConfiguration;

public class PlayerDaoTest {

    private static final ApplicationContext APP_CONFIG = ApplicationConfiguration.appConfig();
    
    private TroupeDao troupeDao;
    private TroupeFactory troupeFactory;
    private PlayerDao playerDao;
    private PlayerFactory playerFactory;

    private Troupe troupe;

    private Player player;

    @Before
    public void setUp() {
        troupeDao       = APP_CONFIG.getBean(TroupeDao.class);
        troupeFactory    = APP_CONFIG.getBean(TroupeFactory.class);
        playerDao       = APP_CONFIG.getBean(PlayerDao.class);
        playerFactory    = APP_CONFIG.getBean(PlayerFactory.class);
        
        troupe = troupeDao.save(troupeFactory.createTroupe("troupe", Setting.CAMARILLA));
        player = playerFactory.createPlayer("name", "email");
    }
    
    @After
    public void tearDown() {
        playerDao.delete(player);
        troupeDao.delete(troupe);
    }
    
    @Test
    public void testSavePlayer() {
        Player savedPlayer = playerDao.save(player);
        
        assertEquals(player, savedPlayer);
        assertEqualValues(savedPlayer, playerDao.findOne(player.getId()));
    }
    
    @Test
    public void testUpdatingPlayer() {
        Player savedPlayer = playerDao.save(player);
        
        Player updatedPlayer = playerDao.save(savedPlayer.withName("new name").withEmail("new email"));
        assertEquals(player, updatedPlayer);
        assertEquals(savedPlayer, updatedPlayer);
        assertEquals("name", savedPlayer.getName());
        assertEquals("email", savedPlayer.getEmail());
        assertEquals("new name", updatedPlayer.getName());
        assertEquals("new email", updatedPlayer.getEmail());
        
        Player playerFound = playerDao.findOne(savedPlayer.getId());
        assertEqualValues(updatedPlayer, playerFound);
    }
    
}
