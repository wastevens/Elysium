package com.dstevens.players;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.springframework.context.ApplicationContext;

import com.dstevens.collections.Sets;
import com.dstevens.configuration.ApplicationConfiguration;

public class TroupeRepositoryTest {

    private static TroupeRepository repository;
    private static PlayerRepository playerRepository;

    @BeforeClass
    public static void setUp() {
        ApplicationContext appConfig = ApplicationConfiguration.appConfig();
        repository = appConfig.getBean(TroupeRepository.class);
        playerRepository = appConfig.getBean(PlayerRepository.class);
    }
    
    @Test
    public void testTroupeRepository() {
        Troupe troupeToSave = Troupe.newTroupe("some name", Setting.ANARCH);
        Troupe savedTroupe = repository.save(troupeToSave);
        try {
            assertEquals(troupeToSave, savedTroupe);
            assertEquals(troupeToSave.getName(), savedTroupe.getName());
            assertEquals(troupeToSave.getSetting(), savedTroupe.getSetting());
        } finally {
            repository.delete(savedTroupe);
        }
    }
    
    @Test
    public void testChangingNameAndSetting() {
        Troupe troupeToSave = Troupe.newTroupe("some name", Setting.ANARCH);
        Troupe savedTroupe = repository.save(troupeToSave);
        try {
            assertEquals(troupeToSave, savedTroupe);
            assertEquals(troupeToSave.getName(), savedTroupe.getName());
            assertEquals(troupeToSave.getSetting(), savedTroupe.getSetting());
            
            Troupe modifiedSavedTroupe = repository.save(savedTroupe.withName("new name").withSetting(Setting.CAMARILLA));
            
            assertEquals(troupeToSave, modifiedSavedTroupe);
            assertEquals(savedTroupe, modifiedSavedTroupe);
            assertEquals("new name", modifiedSavedTroupe.getName());
            assertEquals(Setting.CAMARILLA, modifiedSavedTroupe.getSetting());
            assertEquals("some name", savedTroupe.getName());
            assertEquals(Setting.ANARCH, savedTroupe.getSetting());
            
        } finally {
            repository.delete(savedTroupe);
        }
    }
    
    @Test
    public void testWithPlayers() {
        Troupe troupeToSave = Troupe.newTroupe("some name", Setting.ANARCH);
        Troupe savedTroupe = repository.save(troupeToSave);
        Player player1 = Player.newPlayer("player 1 name", "player 1 email");
        Player player2 = Player.newPlayer("player 2 name", "player 2 email");
        Player player3 = Player.newPlayer("player 3 name", "player 3 email");
        try {
            Troupe troupeWithPlayers = repository.save(savedTroupe.withPlayer(player1).withPlayer(player2));
            assertEquals(Sets.set(player1, player2), troupeWithPlayers.getPlayers());
            
            Troupe troupeWithAnotherPlayer = repository.save(troupeWithPlayers.withPlayer(player3));
            
            assertEquals(Sets.set(player1, player2, player3), troupeWithAnotherPlayer.getPlayers());
        } finally {
            repository.save(savedTroupe.clearPlayers());
            repository.delete(savedTroupe);
            playerRepository.delete(player1);
            playerRepository.delete(player2);
            playerRepository.delete(player3);
        }
    }
    
}
