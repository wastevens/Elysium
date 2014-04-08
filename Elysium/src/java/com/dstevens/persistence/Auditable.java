package com.dstevens.persistence;

import java.util.Date;
import javax.persistence.*;

import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="Auditable")
public class Auditable<E> {

    @Id
    private final String id;
    
    @Column(name="status")
    private final AuditableStatus status;
    
    @Column(name="timestamp")
    private final Date timestamp;
    
    @ManyToOne
    @JoinColumn(name="audited_id")
    private final E audited;
    
    public Auditable(String id, E audited, AuditableStatus status, Date timestamp) {
        this.id = id;
        this.audited = audited;
        this.status = status;
        this.timestamp = timestamp;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Auditable) {
            Auditable<?> that = this.getClass().cast(obj);
            return this.id.equals(that.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

}
