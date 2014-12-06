package com.dstevens.persistence.auditing;

import static com.dstevens.collections.Lists.list;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import com.dstevens.configuration.ApplicationConfiguration;
import com.dstevens.players.Player;
import com.dstevens.players.PlayerDao;
import com.dstevens.players.PlayerFactory;
import com.dstevens.players.Setting;
import com.dstevens.players.Troupe;
import com.dstevens.players.TroupeDao;
import com.dstevens.players.TroupeFactory;
import com.dstevens.suppliers.ClockSupplier;
import com.dstevens.suppliers.IdSupplier;

public class AuditEventDaoTest {

    private static final ApplicationContext APP_CONFIG = ApplicationConfiguration.appConfig();

    @Mock private IdSupplier idSupplier;
    @Mock private ClockSupplier clockSupplier;
    @Mock private Clock clock;
    
    private AuditEventFactory auditableFactory;
    private AuditEventDao auditableDao;
    private TroupeFactory troupeFactory;
    private TroupeDao troupeDao;
    private PlayerFactory playerFactory;
    private PlayerDao playerDao;

    private Troupe troupe1;
    private Troupe troupe2;
    private Player player1;
    private Player player2;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        troupeDao = APP_CONFIG.getBean(TroupeDao.class);
        troupeFactory = APP_CONFIG.getBean(TroupeFactory.class);
        playerDao = APP_CONFIG.getBean(PlayerDao.class);
        playerFactory = APP_CONFIG.getBean(PlayerFactory.class);
        auditableDao = APP_CONFIG.getBean(AuditEventDao.class);
        auditableFactory = new AuditEventFactory(idSupplier, clockSupplier);
        
        when(idSupplier.get()).thenReturn("id 1").thenReturn("id 2").thenReturn("id 3").
                               thenReturn("id 4").thenReturn("id 5").thenReturn("id 6");
        when(clockSupplier.get()).thenReturn(clock);
        when(clock.instant()).thenReturn(Instant.ofEpochMilli(10100L)).thenReturn(Instant.ofEpochMilli(20100L)).thenReturn(Instant.ofEpochMilli(30100L)).
                              thenReturn(Instant.ofEpochMilli(40100L)).thenReturn(Instant.ofEpochMilli(50100L)).thenReturn(Instant.ofEpochMilli(60100L));
        
        troupe1 = troupeDao.save(troupeFactory.createTroupe("name", Setting.CAMARILLA));
        troupe2 = troupeDao.save(troupeFactory.createTroupe("another name", Setting.CAMARILLA));
        player1 = playerDao.save(playerFactory.createPlayer("name", "email"));
        player2 = playerDao.save(playerFactory.createPlayer("another name", "another email"));
        troupe1 = troupeDao.save(troupe1.withPlayer(player1));
        troupe2 = troupeDao.save(troupe2.withPlayer(player2));
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
        playerDao.delete(player1.getId());
        playerDao.delete(player2.getId());
        troupeDao.delete(troupe1.getId());
        troupeDao.delete(troupe2.getId());
        
        auditableDao.deleteAuditEventsFor(troupe1);
        auditableDao.deleteAuditEventsFor(troupe2);
    }
    
}
