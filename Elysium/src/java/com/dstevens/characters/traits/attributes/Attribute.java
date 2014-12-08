package com.dstevens.characters.traits.attributes;

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

	public abstract AttributeValue setAttributeTo(int value);
	
}
