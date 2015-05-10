package com.dstevens.character.trait;

import java.util.Set;

import static com.dstevens.collections.Sets.set;

public class TraitQualitiesBuilder {

	private int rating = -1;
	private String specialization = null;
	private Set<String> focuses = set();

	public TraitQualitiesBuilder rated(int rating) {
		this.rating = rating;
		return this;
	}
	
	public TraitQualitiesBuilder specialized(String specialization) {
		this.specialization = specialization;
		return this;
	}
	
	public TraitQualitiesBuilder focused(Set<String> focuses) {
		this.focuses = focuses;
		return this;
	}
	
	public TraitQualities build() {
		return new TraitQualities(rating, specialization, focuses);
	}
	
}
