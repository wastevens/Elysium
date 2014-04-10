package com.dstevens.players;

import static com.dstevens.testing.Assertions.assertEqualValues;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.springframework.context.ApplicationContext;

import com.dstevens.configuration.ApplicationConfiguration;

public class PlayerDaoTest {

    private static final ApplicationContext APP_CONFIG = ApplicationConfiguration.appConfig();
    
    private PlayerDao playerDao;
    private PlayerFactory playerFactory;

    @Before
    public void setUp() {
        playerDao       = APP_CONFIG.getBean(PlayerDao.class);
        playerFactory    = APP_CONFIG.getBean(PlayerFactory.class);
    }
    
    @Test
    public void testSavePlayer() {
        Player player = playerFactory.createPlayer("name", "email");
        Player savedPlayer = playerDao.save(player);
        try {
            assertEquals(player, savedPlayer);
            assertEqualValues(savedPlayer, playerDao.findOne(player.getId()));
        } finally {
            playerDao.delete(savedPlayer);
        }
    }
    
    @Test
    public void testUpdatingPlayer() {
        Player player = playerFactory.createPlayer("name", "email");
        Player savedPlayer = playerDao.save(player);
        try {
            Player updatedPlayer = playerDao.save(savedPlayer.withName("new name").withEmail("new email"));
            assertEquals(player, updatedPlayer);
            assertEquals(savedPlayer, updatedPlayer);
            assertEquals("name", savedPlayer.getName());
            assertEquals("email", savedPlayer.getEmail());
            assertEquals("new name", updatedPlayer.getName());
            assertEquals("new email", updatedPlayer.getEmail());
            
            Player playerFound = playerDao.findOne(savedPlayer.getId());
            assertEqualValues(updatedPlayer, playerFound);
        } finally {
            playerDao.delete(savedPlayer);
        }
    }
    
}
