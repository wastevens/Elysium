package com.dstevens.characters.traits.attributes;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetAttributeFocusFactory {

	public SetPhysicalFocus setFocus(PhysicalAttributeFocus trait) {
		return new SetPhysicalFocus(TraitChangeStatus.PENDING, trait);
	}
	
	public SetSocialFocus setFocus(SocialAttributeFocus trait) {
		return new SetSocialFocus(TraitChangeStatus.PENDING, trait);
	}
	
	public SetMentalFocus setFocus(MentalAttributeFocus trait) {
		return new SetMentalFocus(TraitChangeStatus.PENDING, trait);
	}
	
}
