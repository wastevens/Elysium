package com.dstevens.characters.attributes;

import java.util.List;

import com.dstevens.utilities.ObjectExtensions;

public class Attribute {

    private final int rating;
    private final List<AttributeFocus> focuses;
    
    public Attribute(int rating, List<AttributeFocus> focuses) {
        this.rating = rating;
        this.focuses = focuses;
    }
    
    @Override
    public boolean equals(Object that) {
        return ObjectExtensions.equals(this, that);
    }
    
    @Override
    public int hashCode() {
        return ObjectExtensions.hashCodeFor(this);
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }
    
}
