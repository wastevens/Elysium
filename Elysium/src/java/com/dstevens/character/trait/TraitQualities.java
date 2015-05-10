package com.dstevens.character.trait;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import org.hibernate.annotations.ForeignKey;

import static com.dstevens.collections.Sets.set;

@SuppressWarnings("deprecation")
@Embeddable
public class TraitQualities {

    @Column(name="rating")
    public final int rating;
    
    @Column(name="specialization")
    public final String specialization;
    
    @ElementCollection
    @ForeignKey(name= "TraitQualities_focuses_FK")
    public Set<String> focuses;
    
    public static final TraitQualities NONE = new TraitQualities();
    
    //Hibernate only
    @Deprecated
    protected TraitQualities() {
    	this(-1, null, set());
    }
    
    protected TraitQualities(int rating, String specialization, Set<String> focuses) {
    	this.rating = rating;
    	this.specialization = specialization;
    	this.focuses = focuses;
    }
    
}
