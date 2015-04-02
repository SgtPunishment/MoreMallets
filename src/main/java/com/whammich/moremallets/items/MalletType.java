package com.whammich.moremallets.items;

import java.util.Locale;

import static com.whammich.moremallets.utils.ConfigHandler.*;

public enum MalletType {

    SOULIUM(durabilityMalletSoulium), // Soul Shards: The Old Ways
    UNSTABLE(), BEDROCKIUM(), // Extra Utils
    MANASTEEL(durabilityMalletManasteel), ELEMENTIUM(durabilityMalletElementium), TERRASTEEL(durabilityMalletTerrasteel), // Botania
    FLAXSTEAM(durabilityMalletFlaxSteam),
    BOUND(); // Blood Magic

    public final int durability;

    private MalletType(int durability) {
        this.durability = durability;
    }

    private MalletType() {
        this(-1);
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
