package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.changes.TraitChange;

public enum Attribute {

	PHYSICAL {
		@Override
		public AttributeValue setAttributeTo(int value) {
			return new PhysicalAttributeValue(value);
		}
	},
	SOCIAL {
		@Override
		public AttributeValue setAttributeTo(int value) {
			return new SocialAttributeValue(value);
		}
	},
	MENTAL {
		@Override
		public AttributeValue setAttributeTo(int value) {
			return new MentalAttributeValue(value);
		}
	};

	public TraitChange<AttributeValue> set(int rating) {
		return new SetAttributeValue(this.ordinal(), rating);
	}
	
	public abstract AttributeValue setAttributeTo(int value);
	
}
