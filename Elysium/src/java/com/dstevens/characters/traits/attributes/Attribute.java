package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;

public enum Attribute implements Trait {

	PHYSICAL(0) {
		@Override
		public ApplicableTrait setAttributeTo(int value) {
			return new PhysicalAttributeValue(value);
		}
	},
	SOCIAL(1) {
		@Override
		public ApplicableTrait setAttributeTo(int value) {
			return new SocialAttributeValue(value);
		}
	},
	MENTAL(2) {
		@Override
		public ApplicableTrait setAttributeTo(int value) {
			return new MentalAttributeValue(value);
		}
	};

	private final int id;

	private Attribute(int id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return this.setAttributeTo(qualities.rating);
	}
	
	public abstract ApplicableTrait setAttributeTo(int value);
	
}
