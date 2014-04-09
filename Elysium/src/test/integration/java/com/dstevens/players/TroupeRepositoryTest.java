package com.dstevens.players;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;

import com.dstevens.configuration.ApplicationConfiguration;
import com.dstevens.persistence.*;

public class TroupeRepositoryTest {

    private static final ApplicationContext APP_CONFIG = ApplicationConfiguration.appConfig();
    
    private TroupeRepository troupeRepository;
    private TroupeDao troupeDao;
    private AuditableDao auditableDao;

    private Troupe troupe;
    
    @Before
    public void setUp() {
        troupeRepository = APP_CONFIG.getBean(TroupeRepository.class);
        troupeDao = APP_CONFIG.getBean(TroupeDao.class);
        auditableDao = APP_CONFIG.getBean(AuditableDao.class);
        troupe = troupeRepository.createTroupe("troupe name", Setting.CAMARILLA);
    }
    
    @Test
    public void testTroupeRepositoryRecordsAuditMessageOnCreate() {
        List<Auditable<Troupe>> findByAudited = auditableDao.findAllAuditEventsFor(troupe);
        assertEquals(1, findByAudited.size());
        assertEquals("Created", findByAudited.get(0).getAuditMessage());
        assertEquals(troupe, findByAudited.get(0).getAudited());
    }
    
    @After
    public void tearDown() {
        troupeDao.delete(troupe);
        auditableDao.deleteAuditEventsFor(troupe);
    }
}
