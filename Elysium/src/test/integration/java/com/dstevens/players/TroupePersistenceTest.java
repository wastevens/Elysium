package com.dstevens.players;

import java.io.Serializable;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.dstevens.persistence.HibernateUtil;
import com.dstevens.players.Troupe.PersistableTroupe;

public class TroupePersistenceTest {

    private static final Setting SETTING = Setting.ANARCH;
    private static final String TROUPE_NAME = "Troupe Name";
    private static final Long NO_ID = null;

    @Test
    public void testPersistence() {
        PersistableTroupe troupeToPersist = new Troupe.PersistableTroupe(NO_ID, TROUPE_NAME, SETTING);
        System.out.println("Initial: " + troupeToPersist);
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Serializable savedTroupe = session.save(troupeToPersist);
        session.getTransaction().commit();
        session.close();
        session = factory.openSession();
        session.beginTransaction();
        
        PersistableTroupe uniqueResult = PersistableTroupe.class.cast(session.createCriteria(PersistableTroupe.class).add(Restrictions.eq("id", savedTroupe)).uniqueResult());
        
        System.out.println("Post: " + troupeToPersist);
        System.out.println("Saved: " + savedTroupe);
        System.out.println("Found: " + uniqueResult);
        
        session.delete(uniqueResult);
        session.getTransaction().commit();
        session.close();
    }
    
}
