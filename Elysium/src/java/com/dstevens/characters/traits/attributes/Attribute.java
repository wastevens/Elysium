package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

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

	public TraitChange set(TraitChangeStatus status, int rating) {
		return new SetAttributeValue(status, this.ordinal(), rating);
	}
	
	public abstract AttributeValue setAttributeTo(int value);
	
}
