package com.dstevens.characters;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.*;
import org.mockito.*;

import com.dstevens.persistence.auditing.*;
import com.dstevens.players.*;

public class PlayerCharacterRepositoryTest {

    @Mock private AuditableRepositoryProvider auditableRepositoryProvider;
    @Mock private PlayerCharacterDao dao;
    @Mock private AuditableRepository<PlayerCharacter> auditableRepository;
    @Mock private PlayerCharacter playerCharacter;
    @Mock private PlayerCharacter savedPlayerCharacter;
    @Mock private PlayerCharacterFactory factory;
    @Mock private Troupe troupe;
    @Mock private Player player;
    
    private PlayerCharacterRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(auditableRepositoryProvider.repositoryFor(dao)).thenReturn(auditableRepository);
        
        repository = new PlayerCharacterRepository(dao, auditableRepositoryProvider, factory);
    }
    
    @Test
    public void testThatEventsAreRecordedForCreate() {
        when(auditableRepository.create(playerCharacter)).thenReturn(savedPlayerCharacter);
        assertEquals(savedPlayerCharacter, repository.create(playerCharacter));
    }
    
    @Test
    public void testThatEventsAreRecordedForUpdate() {
        when(auditableRepository.update(playerCharacter)).thenReturn(savedPlayerCharacter);
        assertEquals(savedPlayerCharacter, repository.update(playerCharacter));
    }
    
    @Test
    public void testThatEventsAreRecordedForSoftDelete() {
        repository.delete(playerCharacter);
        
        verify(auditableRepository).delete(playerCharacter);
    }
    
    @Test
    public void testThatEventsAreRecordedForUndelete() {
        when(auditableRepository.undelete(playerCharacter)).thenReturn(savedPlayerCharacter);
        assertEquals(savedPlayerCharacter, repository.undelete(playerCharacter));
    }
    
    @Test
    public void testThatEventsArePurgedWhenTroupeIsHardDeleted() {
        repository.hardDelete(playerCharacter);
        
        verify(auditableRepository).hardDelete(playerCharacter);
    }
    
    @Test
    public void testCreateCharacterFor() {
        String characterName = "Character name";
        when(factory.createPlayerCharacter(troupe, player, characterName)).thenReturn(playerCharacter);
        when(auditableRepository.create(playerCharacter)).thenReturn(savedPlayerCharacter);
        
        assertEquals(savedPlayerCharacter, repository.createNewCharacterFor(troupe, player, characterName));
    }
    
}

