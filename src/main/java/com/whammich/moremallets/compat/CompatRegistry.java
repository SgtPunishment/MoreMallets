package com.whammich.moremallets.compat;

import cpw.mods.fml.common.Loader;

public class CompatRegistry {

    public static void registerCompat() {
        if (Loader.isModLoaded("AWWayofTime"))
            CompatBloodMagic.load();
        if (Loader.isModLoaded("ExtraUtilities"))
            CompatExtraUtils.load();
        if (Loader.isModLoaded("SSTOW"))
            CompatSoulShards.load();
        if (Loader.isModLoaded("Botania"))
            CompatBotania.load();
    }
}