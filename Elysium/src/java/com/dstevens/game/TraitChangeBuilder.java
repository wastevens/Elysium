package com.dstevens.game;

import com.dstevens.characters.changes.SetTrait;

public interface TraitChangeBuilder {
    
    SetTrait buy();
    SetTrait add();
    SetTrait sell();
    SetTrait remove();
    
}
