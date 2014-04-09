package com.dstevens.players;

import static com.dstevens.collections.Lists.list;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.*;

import org.junit.*;
import org.mockito.*;
import org.springframework.context.ApplicationContext;

import com.dstevens.configuration.ApplicationConfiguration;
import com.dstevens.persistence.*;
import com.dstevens.persistence.auditing.*;

public class AuditEventDaoTest {

    private static final ApplicationContext APP_CONFIG = ApplicationConfiguration.appConfig();

    @Mock private IdGenerator idGenerator;
    @Mock private ClockProvider clockProvider;
    @Mock private Clock clock;
    private AuditEventFactory auditableFactory;
    
    private TroupeFactory troupeFactory;
    private TroupeDao troupeDao;
    private AuditEventDao auditableDao;

    private Troupe troupe1;
    private Troupe troupe2;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        troupeDao = APP_CONFIG.getBean(TroupeDao.class);
        auditableDao = APP_CONFIG.getBean(AuditEventDao.class);
        troupeFactory = APP_CONFIG.getBean(TroupeFactory.class);
        auditableFactory = new AuditEventFactory(idGenerator, clockProvider);
        
        when(idGenerator.createId()).thenReturn("id 1").thenReturn("id 2").thenReturn("id 3").
                                     thenReturn("id 4").thenReturn("id 5").thenReturn("id 6");
        when(clockProvider.getClock()).thenReturn(clock);
        when(clock.instant()).thenReturn(Instant.ofEpochMilli(10100L)).thenReturn(Instant.ofEpochMilli(20100L)).thenReturn(Instant.ofEpochMilli(30100L)).
                              thenReturn(Instant.ofEpochMilli(40100L)).thenReturn(Instant.ofEpochMilli(50100L)).thenReturn(Instant.ofEpochMilli(60100L));
        
        troupe1 = troupeDao.save(troupeFactory.createTroupe("name", Setting.CAMARILLA));
        troupe2 = troupeDao.save(troupeFactory.createTroupe("another name", Setting.CAMARILLA));
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testFindAuditEvents() throws InterruptedException {
        AuditEvent<Troupe> auditable1ForTroupe1 = auditableFactory.auditableFor(troupe1, "Create for troupe 1");
        AuditEvent<Troupe> auditable1ForTroupe2 = auditableFactory.auditableFor(troupe2, "Create for troupe 2");
        AuditEvent<Troupe> auditable2ForTroupe1 = auditableFactory.auditableFor(troupe1, "Update for troupe 1");
        AuditEvent<Troupe> auditable2ForTroupe2 = auditableFactory.auditableFor(troupe2, "Update for troupe 2");
        AuditEvent<Troupe> auditable3ForTroupe1 = auditableFactory.auditableFor(troupe1, "Another Update for troup 1");
        AuditEvent<Troupe> auditable3ForTroupe2 = auditableFactory.auditableFor(troupe2, "Another Update for troup 2");
        
        auditableDao.save(auditable1ForTroupe1);
        auditableDao.save(auditable1ForTroupe2);
        auditableDao.save(auditable2ForTroupe2);
        auditableDao.save(auditable2ForTroupe1);
        auditableDao.save(auditable3ForTroupe1);
        auditableDao.save(auditable3ForTroupe2);
        
        assertEquals(list(auditable3ForTroupe1, auditable2ForTroupe1, auditable1ForTroupe1), auditableDao.findAllAuditEventsFor(troupe1));
        assertEquals(list(auditable3ForTroupe2, auditable2ForTroupe2, auditable1ForTroupe2), auditableDao.findAllAuditEventsFor(troupe2));
        
        assertEquals(auditable3ForTroupe1, auditableDao.findMostRecentAuditEventFor(troupe1));
        assertEquals(auditable3ForTroupe2, auditableDao.findMostRecentAuditEventFor(troupe2));
        
        assertEquals(auditable1ForTroupe1, auditableDao.findFirstAuditEventFor(troupe1));
        assertEquals(auditable1ForTroupe2, auditableDao.findFirstAuditEventFor(troupe2));
    }
    
    @After
    public void tearDown() {
        troupeDao.delete(troupe1);
        troupeDao.delete(troupe2);
        auditableDao.deleteAuditEventsFor(troupe1);
        auditableDao.deleteAuditEventsFor(troupe2);
    }
    
}
