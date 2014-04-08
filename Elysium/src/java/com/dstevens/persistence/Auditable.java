package com.dstevens.persistence;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="Auditable")
public class Auditable<E extends Identified> {

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
    
    
}
