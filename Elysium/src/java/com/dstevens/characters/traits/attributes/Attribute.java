package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;

public enum Attribute implements Trait {

	PHYSICAL {
		@Override
		public ApplicableTrait setAttributeTo(int value) {
			return new PhysicalAttributeValue(value);
		}
	},
	SOCIAL {
		@Override
		public ApplicableTrait setAttributeTo(int value) {
			return new SocialAttributeValue(value);
		}
	},
	MENTAL {
		@Override
		public ApplicableTrait setAttributeTo(int value) {
			return new MentalAttributeValue(value);
		}
	};

	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return this.setAttributeTo(qualities.rating);
	}
	
	public abstract ApplicableTrait setAttributeTo(int value);
	
}
