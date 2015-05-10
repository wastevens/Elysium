package com.dstevens.characters.traits;

import org.junit.Test;

import com.dstevens.character.clan.Bloodline;
import com.dstevens.character.clan.Clan;
import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.attribute.Attribute;
import com.dstevens.character.trait.attribute.focus.MentalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.PhysicalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.SocialAttributeFocus;
import com.dstevens.character.trait.background.Background;
import com.dstevens.character.trait.distinction.flaw.Flaw;
import com.dstevens.character.trait.distinction.merit.Merit;
import com.dstevens.character.trait.power.discipline.Discipline;
import com.dstevens.character.trait.power.discipline.ElderPower;
import com.dstevens.character.trait.power.discipline.Technique;
import com.dstevens.character.trait.power.magic.necromancy.NecromanticRitual;
import com.dstevens.character.trait.power.magic.thaumaturgy.ThaumaturgicalRitual;
import com.dstevens.character.trait.skill.Skill;
import com.dstevens.character.trait.status.Status;

import static org.junit.Assert.assertEquals;

public class TraitTest {

	@Test
	public void testThatNoIdsWereMissed() {
		assertNoIdsMissedFor(Attribute.class);
		assertNoIdsMissedFor(Background.class);
		assertNoIdsMissedFor(Bloodline.class);
		assertNoIdsMissedFor(Clan.class);
		assertNoIdsMissedFor(Discipline.class);
		assertNoIdsMissedFor(ElderPower.class);
		assertNoIdsMissedFor(Flaw.class);
		assertNoIdsMissedFor(MentalAttributeFocus.class);
		assertNoIdsMissedFor(Merit.class);
		assertNoIdsMissedFor(NecromanticRitual.class);
		assertNoIdsMissedFor(PhysicalAttributeFocus.class);
		assertNoIdsMissedFor(Skill.class);
		assertNoIdsMissedFor(SocialAttributeFocus.class);
		assertNoIdsMissedFor(Status.class);
		assertNoIdsMissedFor(Technique.class);
		assertNoIdsMissedFor(ThaumaturgicalRitual.class);
	}
	
	private <E extends Enum<E>> void assertNoIdsMissedFor(Class<E> klazz) {
		assertEquals(true, klazz.isEnum());
		E[] values = klazz.getEnumConstants();
		for (E value : values) {
			assertEquals(true, Trait.class.isAssignableFrom(value.getClass()));
			assertEquals(Trait.class.cast(value).getId().intValue(), value.ordinal());
		}
	}
	
}
