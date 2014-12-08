package com.dstevens.characters.traits;

import java.util.Set;

import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.AttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.distinctions.flaws.Flaw;
import com.dstevens.characters.traits.distinctions.merits.Merit;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.disciplines.ElderPower;
import com.dstevens.characters.traits.powers.disciplines.Technique;
import com.dstevens.characters.traits.powers.magic.Ritual;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.status.Status;

public interface TraitChangeFactory {

	SetTrait attribute(Attribute attribute, int rating);
	
	SetTrait focus(AttributeFocus focus);

	SetTrait skill(Skill skill, int rating, String specialization, Set<String> focuses);

	SetTrait background(Background background, int rating, String specialization, Set<String> focuses);

	SetTrait power(Power<?> power, int rating);

	SetTrait ritual(Ritual<?> ritual);

	SetTrait technique(Technique technique);

	SetTrait elderPower(ElderPower power);

	SetTrait inClanPower(Power<?> power);
	
	SetTrait merit(Merit merit, String specialization, SetTrait associatedTrait);
	
	SetTrait flaw(Flaw flaw, String specialization, SetTrait associatedTrait);

	SetTrait status(Status awesome, String string);

}