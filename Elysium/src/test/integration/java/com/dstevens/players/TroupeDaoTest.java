package com.dstevens.players;

import static com.dstevens.collections.Sets.*;
import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;
import org.springframework.context.ApplicationContext;

import com.dstevens.configuration.ApplicationConfiguration;

public class TroupeDaoTest {

    private static final ApplicationContext APP_CONFIG = ApplicationConfiguration.appConfig();
    
    private TroupeDao troupeDao;
    private TroupeFactory troupeFactory;
    private PlayerDao playerDao;
    private PlayerFactory playerFactory;
    
    private Set<Troupe> troupesToDelete;
    private Set<Player> playersToDelete;

    @Before
    public void setUp() {
        troupeDao       = APP_CONFIG.getBean(TroupeDao.class);
        troupeFactory    = APP_CONFIG.getBean(TroupeFactory.class);
        playerDao       = APP_CONFIG.getBean(PlayerDao.class);
        playerFactory    = APP_CONFIG.getBean(PlayerFactory.class);
        
        troupesToDelete = set();
        playersToDelete = set();
    }
    
    @After
    public void tearDown() {
        troupeDao.delete(troupesToDelete);
        playerDao.delete(playersToDelete);
    }
    
    @Test
    public void testTroupeDao() {
        Troupe troupeToSave = troupeFactory.createTroupe("some name", Setting.ANARCH);
        Troupe savedTroupe = troupeDao.save(troupeToSave);
        troupesToDelete.add(savedTroupe);
        assertEquals(troupeToSave, savedTroupe);
        assertEquals(troupeToSave.getName(), savedTroupe.getName());
        assertEquals(troupeToSave.getSetting(), savedTroupe.getSetting());
    }
    
    @Test
    public void testChangingNameAndSetting() {
        Troupe troupeToSave = troupeFactory.createTroupe("some name", Setting.ANARCH);
        Troupe savedTroupe = troupeDao.save(troupeToSave);
        troupesToDelete.add(savedTroupe);
        assertEquals(troupeToSave, savedTroupe);
        assertEquals(troupeToSave.getName(), savedTroupe.getName());
        assertEquals(troupeToSave.getSetting(), savedTroupe.getSetting());
        
        Troupe modifiedSavedTroupe = troupeDao.save(savedTroupe.withName("new name").withSetting(Setting.CAMARILLA));
        
        assertEquals(troupeToSave, modifiedSavedTroupe);
        assertEquals(savedTroupe, modifiedSavedTroupe);
        assertEquals("new name", savedTroupe.getName());
        assertEquals(Setting.CAMARILLA, savedTroupe.getSetting());
        assertEquals("new name", modifiedSavedTroupe.getName());
        assertEquals(Setting.CAMARILLA, modifiedSavedTroupe.getSetting());
            
    }
    
    @Test
    public void testFindingAllWithNameAndNotDeleted() {
        String name = "some name";
        Troupe troupe1 = troupeDao.save(troupeFactory.createTroupe(name, Setting.ANARCH));
        Troupe troupe2 = troupeDao.save(troupeFactory.createTroupe(name, Setting.ANARCH).delete(new Date(2357L)));
        Troupe troupe3 = troupeDao.save(troupeFactory.createTroupe(name, Setting.ANARCH));
        Troupe troupe4 = troupeDao.save(troupeFactory.createTroupe(name, Setting.ANARCH).delete(new Date(111317L)));
        Troupe troupe5 = troupeDao.save(troupeFactory.createTroupe(name, Setting.ANARCH));
        Troupe troupe6 = troupeDao.save(troupeFactory.createTroupe(name, Setting.ANARCH));
        troupesToDelete.addAll(set(troupe1, troupe2, troupe3, troupe4, troupe5, troupe6));
        
        assertEquals(set(troupe1, troupe2, troupe3, troupe4, troupe5, troupe6), setFrom(troupeDao.findNamed(name)));
        assertEquals(set(troupe1, troupe3, troupe5, troupe6), setFrom(troupeDao.findUndeletedNamed(name)));
    }
    
    @Test
    public void testWithPlayers() {
        Troupe savedTroupe = troupeDao.save(troupeFactory.createTroupe("some name", Setting.ANARCH));
        Player player1 = playerDao.save(playerFactory.createPlayer("player 1 name", "player 1 email"));
        Player player2 = playerDao.save(playerFactory.createPlayer("player 2 name", "player 2 email"));
        Player player3 = playerDao.save(playerFactory.createPlayer("player 3 name", "player 3 email"));
        Troupe troupeWithPlayers = troupeDao.save(savedTroupe.withPlayer(player1).withPlayer(player2));
        assertEquals(set(player1, player2), troupeWithPlayers.getPlayers());
        
        Troupe troupeWithAnotherPlayer = troupeDao.save(troupeWithPlayers.withPlayer(player3));
        troupesToDelete.add(troupeWithAnotherPlayer);
        playersToDelete.addAll(set(player1, player2, player3));
        
        assertEquals(set(player1, player2, player3), troupeWithAnotherPlayer.getPlayers());
    }
    
}
