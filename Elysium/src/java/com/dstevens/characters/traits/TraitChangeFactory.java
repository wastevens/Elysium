package com.dstevens.characters.traits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.backgrounds.CharacterBackgroundFactory;
import com.dstevens.characters.skills.CharacterSkillFactory;
import com.dstevens.suppliers.IdSupplier;

@Service
public class TraitChangeFactory {

    private IdSupplier supplier;

    @Autowired
    public TraitChangeFactory(IdSupplier supplier) {
        this.supplier = supplier;
    }
    
    public CharacterBackgroundFactory characterBackgroundFactory() {
        return new CharacterBackgroundFactory(supplier);
    }
    
    public CharacterSkillFactory characterSkillFactory() {
        return new CharacterSkillFactory(supplier);
    }
    
}
