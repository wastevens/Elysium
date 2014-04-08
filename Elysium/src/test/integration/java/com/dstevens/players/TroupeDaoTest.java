package com.dstevens.players;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.springframework.context.ApplicationContext;

import com.dstevens.collections.Sets;
import com.dstevens.configuration.ApplicationConfiguration;

public class TroupeDaoTest {

    private static final ApplicationContext APP_CONFIG = ApplicationConfiguration.appConfig();
    
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
    }
    
    @Test
    public void testTroupeDao() {
        Troupe troupeToSave = troupeFactory.createTroupe("some name", Setting.ANARCH);
        Troupe savedTroupe = troupeDao.save(troupeToSave);
        try {
            assertEquals(troupeToSave, savedTroupe);
            assertEquals(troupeToSave.getName(), savedTroupe.getName());
            assertEquals(troupeToSave.getSetting(), savedTroupe.getSetting());
        } finally {
            troupeDao.delete(savedTroupe);
        }
    }
    
    @Test
    public void testChangingNameAndSetting() {
        Troupe troupeToSave = troupeFactory.createTroupe("some name", Setting.ANARCH);
        Troupe savedTroupe = troupeDao.save(troupeToSave);
        try {
            assertEquals(troupeToSave, savedTroupe);
            assertEquals(troupeToSave.getName(), savedTroupe.getName());
            assertEquals(troupeToSave.getSetting(), savedTroupe.getSetting());
            
            Troupe modifiedSavedTroupe = troupeDao.save(savedTroupe.withName("new name").withSetting(Setting.CAMARILLA));
            
            assertEquals(troupeToSave, modifiedSavedTroupe);
            assertEquals(savedTroupe, modifiedSavedTroupe);
            assertEquals("new name", modifiedSavedTroupe.getName());
            assertEquals(Setting.CAMARILLA, modifiedSavedTroupe.getSetting());
            assertEquals("some name", savedTroupe.getName());
            assertEquals(Setting.ANARCH, savedTroupe.getSetting());
            
        } finally {
            troupeDao.delete(savedTroupe);
        }
    }
    
    @Test
    public void testWithPlayers() {
        Troupe troupeToSave = troupeFactory.createTroupe("some name", Setting.ANARCH);
        Troupe savedTroupe = troupeDao.save(troupeToSave);
        Player player1 = playerFactory.createPlayer("player 1 name", "player 1 email");
        Player player2 = playerFactory.createPlayer("player 2 name", "player 2 email");
        Player player3 = playerFactory.createPlayer("player 3 name", "player 3 email");
        try {
            Troupe troupeWithPlayers = troupeDao.save(savedTroupe.withPlayer(player1).withPlayer(player2));
            assertEquals(Sets.set(player1, player2), troupeWithPlayers.getPlayers());
            
            Troupe troupeWithAnotherPlayer = troupeDao.save(troupeWithPlayers.withPlayer(player3));
            
            assertEquals(Sets.set(player1, player2, player3), troupeWithAnotherPlayer.getPlayers());
        } finally {
            troupeDao.save(savedTroupe.clearPlayers());
            troupeDao.delete(savedTroupe);
            playerDao.delete(player1);
            playerDao.delete(player2);
            playerDao.delete(player3);
        }
    }
    
}
