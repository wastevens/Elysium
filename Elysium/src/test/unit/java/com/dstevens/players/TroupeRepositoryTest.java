package com.dstevens.players;

import static com.dstevens.collections.Lists.list;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.*;
import org.mockito.*;

import com.dstevens.persistence.auditing.*;

public class TroupeRepositoryTest {

    @Mock private AuditableRepositoryProvider auditableRepositoryProvider;
    @Mock private TroupeDao troupeDao;
    @Mock private AuditableRepository<Troupe> auditableRepository;
    @Mock private Troupe troupe;
    @Mock private Troupe savedTroupe;
    @Mock private TroupeFactory troupeFactory;
    
    private TroupeRepository troupeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(auditableRepositoryProvider.repositoryFor(troupeDao)).thenReturn(auditableRepository);
        
        troupeRepository = new TroupeRepository(troupeDao, auditableRepositoryProvider, troupeFactory);
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
    
    @Test
    public void testThatEnsureExistsCreatesTroupeIfTroupeDoesNotExist() {
        String troupeName = "Troupe Name";
        Setting setting = Setting.CAMARILLA;
        when(troupeDao.findNamed(troupeName)).thenReturn(list());
        when(troupeFactory.createTroupe(troupeName, setting)).thenReturn(troupe);
        when(auditableRepository.create(troupe)).thenReturn(savedTroupe);
        
        assertEquals(savedTroupe, troupeRepository.ensureExists(troupeName, setting));
    }
    
    @Test
    public void testThatEnsureExistsDoesNotCreateTroupeIfTroupeExists() {
        String troupeName = "Troupe Name";
        Setting setting = Setting.CAMARILLA;
        when(troupeDao.findNamed(troupeName)).thenReturn(list(troupe));
        
        assertEquals(troupe, troupeRepository.ensureExists(troupeName, setting));
    }
    
    
}
