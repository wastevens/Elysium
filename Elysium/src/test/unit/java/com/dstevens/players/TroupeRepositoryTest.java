package com.dstevens.players;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.time.*;
import java.util.Date;

import org.junit.*;
import org.mockito.*;

import com.dstevens.persistence.*;

public class TroupeRepositoryTest {

    private static final String TROUPE_NAME = "troupe name";
    private static final Setting TROUPE_SETTING = Setting.ANARCH;
    private static final Instant NOW = Instant.ofEpochMilli(2357L);
    
    @Mock private TroupeDao troupeDao;
    @Mock private TroupeFactory troupeFactory;
    @Mock private AuditableRepository auditableRepository;
    @Mock private Troupe troupe;
    @Mock private Troupe savedTroupe;
    @Mock private Troupe deletedTroupe;
    @Mock private ClockProvider clockProvider;
    @Mock private Clock clock;
    
    private TroupeRepository troupeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(troupeFactory.createTroupe(TROUPE_NAME, TROUPE_SETTING)).thenReturn(troupe);
        when(clockProvider.getClock()).thenReturn(clock);
        when(clock.instant()).thenReturn(NOW);
        
        troupeRepository = new TroupeRepository(troupeDao, troupeFactory, auditableRepository, clockProvider);
    }
    
    @Test
    public void testThatEventsAreRecordedForCreate() {
        when(troupeDao.save(troupe)).thenReturn(savedTroupe);
        
        assertEquals(savedTroupe, troupeRepository.createTroupe(TROUPE_NAME, TROUPE_SETTING));
        verify(auditableRepository).recordAuditableFor(savedTroupe, "Created");
    }
    
    @Test
    public void testThatEventsAreRecordedForUpdate() {
        when(troupeDao.save(troupe)).thenReturn(savedTroupe);
        
        troupeRepository.updateTroupe(troupe);
        
        verify(auditableRepository).recordAuditableFor(savedTroupe, "Updated");
    }
    
    @Test
    public void testThatEventsAreRecordedForSoftDelete() {
        when(troupe.deleteAt(Date.from(NOW))).thenReturn(deletedTroupe);
        when(troupeDao.save(deletedTroupe)).thenReturn(savedTroupe);
        
        troupeRepository.deleteTroupe(troupe);
        
        verify(auditableRepository).recordAuditableFor(savedTroupe, "Deleted");
    }
    
    @Test
    public void testThatEventsAreRecordedForUndelete() {
        when(deletedTroupe.undelete()).thenReturn(troupe);
        when(troupeDao.save(troupe)).thenReturn(savedTroupe);
        
        assertEquals(savedTroupe, troupeRepository.undeleteTroupe(deletedTroupe));
        
        verify(auditableRepository).recordAuditableFor(savedTroupe, "Undeleted");
    }
    
    @Test
    public void testThatEventsArePurgedWhenTroupeIsHardDeleted() {
        troupeRepository.hardDeleteTroupe(troupe);
        
        verify(troupeDao).delete(troupe);
        verify(auditableRepository).purgeAuditablesFor(troupe);
    }
    
    
}
