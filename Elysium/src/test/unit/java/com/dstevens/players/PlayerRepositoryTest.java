package com.dstevens.players;

import static com.dstevens.collections.Lists.list;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.*;
import org.mockito.*;

import com.dstevens.persistence.auditing.*;

public class PlayerRepositoryTest {

    @Mock private AuditableRepositoryProvider auditableRepositoryProvider;
    @Mock private PlayerDao dao;
    @Mock private AuditableRepository<Player> auditableRepository;
    @Mock private Player player;
    @Mock private Player savedPlayer;
    @Mock private Troupe troupe;
    @Mock private PlayerFactory factory;
    
    private PlayerRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(auditableRepositoryProvider.repositoryFor(dao)).thenReturn(auditableRepository);
        
        repository = new PlayerRepository(dao, auditableRepositoryProvider, factory);
    }
    
    @Test
    public void testThatEventsAreRecordedForCreate() {
        when(auditableRepository.create(player)).thenReturn(savedPlayer);
        assertEquals(savedPlayer, repository.create(player));
    }
    
    @Test
    public void testThatEventsAreRecordedForUpdate() {
        when(auditableRepository.update(player)).thenReturn(savedPlayer);
        assertEquals(savedPlayer, repository.update(player));
    }
    
    @Test
    public void testThatEventsAreRecordedForSoftDelete() {
        repository.delete(player);
        
        verify(auditableRepository).delete(player);
    }
    
    @Test
    public void testThatEventsAreRecordedForUndelete() {
        when(auditableRepository.undelete(player)).thenReturn(savedPlayer);
        assertEquals(savedPlayer, repository.undelete(player));
    }
    
    @Test
    public void testThatEventsArePurgedWhenTroupeIsHardDeleted() {
        repository.hardDelete(player);
        
        verify(auditableRepository).hardDelete(player);
    }
    
    @Test
    public void testThatEnsureExistsCreatesPlayerIfTroupeDoesNotExist() {
        String playerName = "Player Name";
        String playerEmail = "Player Email";
        when(dao.findUndeletedWithEmail(playerEmail)).thenReturn(null);
        when(factory.createPlayer(playerName, playerEmail, troupe)).thenReturn(player);
        when(auditableRepository.create(player)).thenReturn(savedPlayer);
        
        assertEquals(savedPlayer, repository.ensureExists(playerName, playerEmail, troupe));
    }
    
    @Test
    public void testThatEnsureExistsDoesNotCreateTroupeIfTroupeExists() {
        String playerName = "Player Name";
        String playerEmail = "Player Email";
        when(dao.findNamed(playerName)).thenReturn(list(player));
        
        assertEquals(player, repository.ensureExists(playerName, playerEmail, troupe));
    }
}
