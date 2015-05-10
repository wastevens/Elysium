package com.dstevens.characters.clans;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;

import static com.dstevens.collections.Sets.set;

public enum Clan implements ApplicableTrait, Trait {

    ASSAMITE(0, Bloodline.ASSAMITE, Bloodline.VIZIER, Bloodline.SORCERER),
    BRUJAH(1, Bloodline.BRUJAH, Bloodline.TRUE_BRUJAH),
    FOLLOWER_OF_SET(2, Bloodline.FOLLOWER_OF_SET, Bloodline.TLACLQUE, Bloodline.VIPER),
    GANGREL(3, Bloodline.GANGREL, Bloodline.COYOTE, Bloodline.NOIAD, Bloodline.AHRIMANE),
    GIOVANNI(4, Bloodline.GIOVANNI, Bloodline.PREMASCINE),
    LASOMBRA(5, Bloodline.LASOMBRA, Bloodline.KISAYD),
    MALKAVIAN(6, Bloodline.MALKAVIAN, Bloodline.ANANKE, Bloodline.KNIGHT_OF_THE_MOON),
    NOSFERATU(7, Bloodline.NOSFERATU),
    TOREADOR(8, Bloodline.TOREADOR, Bloodline.ISHTARRI, Bloodline.VOLGIRRE),
    TREMERE(9, Bloodline.TREMERE, Bloodline.TELYAV),
    TZIMISCE(10, Bloodline.TZIMISCE, Bloodline.CARPATHIAN, Bloodline.KOLDUN),
    VENTRUE(11, Bloodline.VENTRUE, Bloodline.CRUSADER),
    CATIFF(12, Bloodline.CATIFF),
    BAALI(13, Bloodline.BAALI, Bloodline.ANGELLIS_ATER),
    CAPPADOCIAN(14, Bloodline.CAPPADOCIAN, Bloodline.SAMEDI, Bloodline.LAMIA),
    RAVNOS(15, Bloodline.RAVNOS, Bloodline.BRAHMAN),
    SALUBRI(16, Bloodline.SALUBRI, Bloodline.HEALER),
    DAUGHTER_OF_CACOPHONY(17, Bloodline.DAUGHTER_OF_CACOPHONY),
    GARGOYLE(18, Bloodline.GARGOYLE);
    
    private final int id;
    private final Set<Bloodline> bloodlines;

    private Clan(int id, Bloodline...bloodlines) {
        this.id = id;
		this.bloodlines = set(bloodlines);
    }

	@Override
	public Integer getId() {
		return id;
	}
    
    public Set<Bloodline> getBloodlines() {
        return bloodlines;
    }

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withClan(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withClan(null);
	}

	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return this;
	}
}
