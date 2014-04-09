package com.dstevens.persistence;

import java.util.Date;

public interface SoftDeletable<E> {

    E delete(Date timestamp);
    E undelete();
    
}
