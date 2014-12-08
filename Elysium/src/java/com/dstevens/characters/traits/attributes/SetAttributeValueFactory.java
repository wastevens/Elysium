package com.dstevens.characters.traits.attributes;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetAttributeValueFactory {

	public SetTrait attributeValue(Attribute attribute, int rating) {
		return new SetAttributeValue(TraitChangeStatus.PENDING, attribute.ordinal(), rating);
	}
	
}
