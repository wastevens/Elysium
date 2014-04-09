package com.dstevens.persistence.auditing;

import java.util.Date;

public interface Auditable<E> {

    E delete(Date timestamp);
    E undelete();
    
}
