package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.traits.changes.TraitChange;

public interface AttributeFocus {

	TraitChange<? extends AttributeFocus> set();
	
}
