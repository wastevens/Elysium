package com.dstevens.players;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.*;
import org.mockito.*;

import com.dstevens.persistence.*;
import com.dstevens.persistence.auditing.*;

public class TroupeRepositoryTest {

    @Mock private AuditableRepositoryProvider auditableRepositoryProvider;
    @Mock private TroupeDao troupeDao;
    @Mock private AuditableRepository<Troupe> auditableRepository;
    @Mock private Troupe troupe;
    @Mock private Troupe savedTroupe;
    
    private TroupeRepository troupeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(auditableRepositoryProvider.repositoryFor(troupeDao)).thenReturn(auditableRepository);
        
        troupeRepository = new TroupeRepository(troupeDao, auditableRepositoryProvider);
    }
    
    @Test
    public void testThatEventsAreRecordedForCreate() {
        when(auditableRepository.create(troupe)).thenReturn(savedTroupe);
        assertEquals(savedTroupe, troupeRepository.create(troupe));
    }
    
    @Test
    public void testThatEventsAreRecordedForUpdate() {
        when(auditableRepository.update(troupe)).thenReturn(savedTroupe);
        assertEquals(savedTroupe, troupeRepository.update(troupe));
    }
    
    @Test
    public void testThatEventsAreRecordedForSoftDelete() {
        troupeRepository.delete(troupe);
        
        verify(auditableRepository).delete(troupe);
    }
    
    @Test
    public void testThatEventsAreRecordedForUndelete() {
        when(auditableRepository.undelete(troupe)).thenReturn(savedTroupe);
        assertEquals(savedTroupe, troupeRepository.undelete(troupe));
    }
    
    @Test
    public void testThatEventsArePurgedWhenTroupeIsHardDeleted() {
        troupeRepository.hardDelete(troupe);
        
        verify(auditableRepository).hardDelete(troupe);
    }
    
    
}
