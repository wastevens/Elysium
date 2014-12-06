package com.dstevens.characters.traits;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.attributes.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.SocialAttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.distinctions.Flaw;
import com.dstevens.characters.traits.distinctions.Merit;
import com.dstevens.characters.traits.distinctions.SetFlawBuilder;
import com.dstevens.characters.traits.distinctions.SetMeritBuilder;
import com.dstevens.characters.traits.powers.ElderPower;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.Ritual;
import com.dstevens.characters.traits.powers.SetElderPowerBuilder;
import com.dstevens.characters.traits.powers.SetInClanPowerBuilder;
import com.dstevens.characters.traits.powers.SetRitualBuilder;
import com.dstevens.characters.traits.powers.SetTechniqueBuilder;
import com.dstevens.characters.traits.powers.Technique;
import com.dstevens.characters.traits.skills.Skill;

public interface TraitChangeFactory {

	SetMeritBuilder merit(Merit merit, String specialization, SetTrait associatedTrait);

	SetFlawBuilder flaw(Flaw flaw, String specialization, SetTrait associatedTrait);

	SetTrait physicalFocus(PhysicalAttributeFocus focus);

	SetTrait mentalFocus(MentalAttributeFocus focus);

	SetTrait socialFocus(SocialAttributeFocus focus);

	SetTrait skill(Skill skill, int rating, String specialization, Set<String> focuses);

	SetTrait background(Background background, int rating, String specialization, Set<String> focuses);

	SetTrait power(Power<?> power, int rating);

	SetRitualBuilder ritual(Ritual<?> ritual);

	SetTechniqueBuilder technique(Technique technique);

	SetElderPowerBuilder elderPower(ElderPower power);

	SetInClanPowerBuilder inClanPower(Power<?> power);

	SetTrait physical(PlayerCharacter character);

	SetTrait social(PlayerCharacter character);

	SetTrait mental(PlayerCharacter character);

}