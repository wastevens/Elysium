package com.dstevens.players;

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
    public void testTroupeRepositoryRecordsAuditMessage() {
        List<Auditable<Troupe>> findByAudited = auditableDao.findByAudited(troupe);
        System.out.println(findByAudited);
        auditableDao.delete(findByAudited);
    }
    
    @After
    public void tearDown() {
        troupeDao.delete(troupe);
    }
}
