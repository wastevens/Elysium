package com.dstevens.characters.backgrounds;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;
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
    
    public CharacterBackground backgroundFor(PlayerCharacter character, Background background, int rating) {
        return new CharacterBackground(idSupplier.get(), character.getId(), background, rating, NO_SPECIALIZATION, NO_FOCUSES);
    }
    
    public CharacterBackground backgroundFor(PlayerCharacter character, Background background, int rating, String specialization) {
        return new CharacterBackground(idSupplier.get(), character.getId(), background, rating, specialization, NO_FOCUSES);
    }
    
    public CharacterBackground backgroundFor(PlayerCharacter character, Background background, int rating, Set<String> focuses) {
        return new CharacterBackground(idSupplier.get(), character.getId(), background, rating, NO_SPECIALIZATION, focuses);
    }
    
    public CharacterBackground backgroundFor(PlayerCharacter character, Background background, int rating, String specialization, Set<String> focuses) {
        return new CharacterBackground(idSupplier.get(), character.getId(), background, rating, specialization, focuses);
    }
    
}
