package com.dstevens.characters.clans;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;

public enum Clan implements ApplicableTrait {

    ASSAMITE(Bloodline.NONE, Bloodline.VIZIER, Bloodline.SORCERER),
    BRUJAH(Bloodline.NONE, Bloodline.TRUE_BRUJAH),
    FOLLOWER_OF_SET(Bloodline.NONE, Bloodline.TLACLQUE, Bloodline.VIPER),
    GANGREL(Bloodline.NONE, Bloodline.COYOTE, Bloodline.NOIAD, Bloodline.AHRIMANE),
    GIOVANNI(Bloodline.NONE, Bloodline.PREMASCINE),
    LASOMBRA(Bloodline.NONE, Bloodline.KISAYD),
    MALKAVIAN(Bloodline.NONE, Bloodline.ANANKE, Bloodline.KNIGHT_OF_THE_MOON),
    NOSFERATU(Bloodline.NONE),
    TOREADOR(Bloodline.NONE, Bloodline.ISHTARRI, Bloodline.VOLGIRRE),
    TREMERE(Bloodline.NONE, Bloodline.TELYAV),
    TZIMISCE(Bloodline.NONE, Bloodline.CARPATHIAN, Bloodline.KOLDUN),
    VENTRUE(Bloodline.NONE, Bloodline.CRUSADER),
    CATIFF(Bloodline.NONE),
    BAALI(Bloodline.NONE, Bloodline.ANGELLIS_ATER),
    CAPPADOCIAN(Bloodline.NONE, Bloodline.SAMEDI, Bloodline.LAMIA),
    RAVNOS(Bloodline.NONE, Bloodline.BRAHMAN),
    SALUBRI(Bloodline.NONE, Bloodline.HEALER),
    DAUGHTER_OF_CACOPHONY(Bloodline.NONE),
    GARGOYLE(Bloodline.NONE);
    
    private final Set<Bloodline> bloodlines;

    private Clan(Bloodline...bloodlines) {
        this.bloodlines = set(bloodlines);
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
}
