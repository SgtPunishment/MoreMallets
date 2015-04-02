package com.whammich.moremallets.utils;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;

    static String category;

    // Settings
    public static boolean enableMalletBound;
    public static boolean enableMalletManasteel;
    public static boolean enableMalletElementium;
    public static boolean enableMalletTerrasteel;
    public static boolean enableMalletUnstable;
    public static boolean enableMalletBedrockium;
    public static boolean enableMalletSoulium;

    public static int durabilityMalletManasteel;
    public static int durabilityMalletElementium;
    public static int durabilityMalletTerrasteel;
    public static int durabilityMalletSoulium;
    public static int durabilityMalletFlaxSteam;

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        category = "Mallets";
        config.setCategoryComment(category, "Toggling of all mallets");

        enableMalletBound = config.getBoolean("enableMalletBound", category, true, null);
        enableMalletManasteel = config.getBoolean("enableMalletManasteel", category, true, null);
        enableMalletElementium = config.getBoolean("enableMalletElementium", category, true, null);
        enableMalletTerrasteel = config.getBoolean("enableMalletTerrasteel", category, true, null);
        enableMalletUnstable = config.getBoolean("enableMalletUnstable", category, true, null);
        enableMalletBedrockium = config.getBoolean("enableMalletBedrockium", category, true, null);
        enableMalletSoulium = config.getBoolean("enableMalletSoulium", category, true, null);

        category = "Durability";
        config.setCategoryComment(category, "Toggling of all mallets");

        durabilityMalletManasteel = config.getInt("durabilityMalletManasteel", category, 300, 0, Short.MAX_VALUE, null);
        durabilityMalletElementium = config.getInt("durabilityMalletElementium", category, 720, 0, Short.MAX_VALUE, null);
        durabilityMalletTerrasteel = config.getInt("durabilityMalletTerrasteel", category, 2300, 0, Short.MAX_VALUE, null);
        durabilityMalletSoulium = config.getInt("durabilityMalletSoulium", category, 3122, 0, Short.MAX_VALUE, null);
        durabilityMalletFlaxSteam = config.getInt("durabilityMalletFlaxSteam", category, 10000, 0, Short.MAX_VALUE, null);

        config.save();
    }
}
