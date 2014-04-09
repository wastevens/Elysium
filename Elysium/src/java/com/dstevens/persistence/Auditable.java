package com.dstevens.persistence;

import java.util.Date;

public interface Auditable<E> {

    E delete(Date timestamp);
    E undelete();
    
}
