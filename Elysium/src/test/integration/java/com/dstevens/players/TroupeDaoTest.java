package com.dstevens.players;

import static org.junit.Assert.*;

import com.dstevens.players.Troupe.PersistableTroupe;

import org.junit.*;

public class TroupeDaoTest {

    private static final Setting SETTING = Setting.ANARCH;
    private static final String TROUPE_NAME = "Troupe Name";
    private TroupeDao dao;

    @Before
    public void setUp() {
        dao = new TroupeDao();
    }
    
    @Test
    public void testPersistance() {
        PersistableTroupe troupeToCreate = new Troupe.PersistableTroupe(null, TROUPE_NAME, SETTING);
        dao.saveOrUpdate(troupeToCreate);
        Troupe troupe = troupeToCreate.toTroupe();
        
        try {
            assertEquals(TROUPE_NAME, troupe.getName());
            assertEquals(SETTING, troupe.getSetting());
            
            Troupe troupeWithChanges = troupe.withName("New " + TROUPE_NAME).withSetting(Setting.SABBAT);
            dao.save(troupeWithChanges);
            
            assertEquals(TROUPE_NAME, troupe.getName());
            assertEquals(SETTING, troupe.getSetting());
            assertEquals("New " + TROUPE_NAME, troupeWithChanges.getName());
            assertEquals(Setting.SABBAT, troupeWithChanges.getSetting());
            assertEquals(troupe.getId(), troupeWithChanges.getId());
            assertEquals(troupe, troupeWithChanges);
            assertEquals(troupe.hashCode(), troupeWithChanges.hashCode());
        } finally {
            dao.delete(troupe);
        }
    }
    
}
