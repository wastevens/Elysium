package com.dstevens.characters.backgrounds;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.suppliers.IdSupplier;

@Service
public class CharacterBackgroundFactory {

    private static final String NO_SPECIALIZATION = null;
    private static final Set<String> NO_FOCUSES = set();
    
    private final IdSupplier idSupplier;

    @Autowired
    public CharacterBackgroundFactory(IdSupplier idSupplier) {
        this.idSupplier = idSupplier;
    }
    
    public CharacterBackground backgroundFor(Background background, int rating) {
        return backgroundFor(background, rating, NO_SPECIALIZATION, NO_FOCUSES);
    }
    
    public CharacterBackground backgroundFor(Background background, int rating, String specialization) {
        return backgroundFor(background, rating, specialization, NO_FOCUSES);
    }
    
    public CharacterBackground backgroundFor(Background background, int rating, Set<String> focuses) {
        return backgroundFor(background, rating, NO_SPECIALIZATION, focuses);
    }
    
    public CharacterBackground backgroundFor(Background background, int rating, String specialization, Set<String> focuses) {
        return new CharacterBackground(idSupplier.get(), background, rating, specialization, focuses);
    }
    
}
