package com.dstevens.characters.traits.attributes;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetAttributeValueFactory {

	public SetTrait attributeValue(AttributeValue trait) {
		return new SetAttributeValue(TraitChangeStatus.PENDING, trait);
	}
	
}
