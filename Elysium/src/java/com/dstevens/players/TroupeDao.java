package com.dstevens.players;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import com.dstevens.persistence.HibernateUtil;
import com.dstevens.players.Troupe.PersistableTroupe;

public class TroupeDao {

    private static SessionFactory factory = HibernateUtil.getSessionFactory();
    
    public Troupe save(Troupe troupe) {
        PersistableTroupe troupeToPersist = Troupe.PersistableTroupe.fromTroup(troupe);
        saveOrUpdate(troupeToPersist);
        
        return troupeToPersist.toTroupe();
    }

    void saveOrUpdate(PersistableTroupe troupeToPersist) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(troupeToPersist);
        session.getTransaction().commit();
        session.close();
    }
    
    public Troupe withId(long id) {
        Session session = factory.openSession();
        PersistableTroupe troupeFound = PersistableTroupe.class.cast(session.createCriteria(PersistableTroupe.class).add(Restrictions.idEq(id)).uniqueResult());
        return troupeFound.toTroupe();
    }
    
    public void delete(Troupe troupe) {
        PersistableTroupe troupeToDelete = Troupe.PersistableTroupe.fromTroup(troupe);
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(troupeToDelete);
        session.getTransaction().commit();
        session.close();
    }
    
}
