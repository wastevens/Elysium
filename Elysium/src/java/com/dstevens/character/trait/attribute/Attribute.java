package com.dstevens.character.trait.attribute;

import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.TraitQualities;
import com.dstevens.utilities.IdentityUtilities;

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
	
	public static Attribute from(int id) {
		return IdentityUtilities.withId(id, Attribute.values());
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
