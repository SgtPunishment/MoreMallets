package com.whammich.moremallets.compat;

import com.whammich.moremallets.items.ItemMalletBase;
import com.whammich.moremallets.items.MalletRegistry;
import com.whammich.moremallets.items.MalletType;
import com.whammich.moremallets.utils.ConfigHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CompatSoulShards {

    public static Item malletSoulium = null;

    public static void load() {
        registerMallets();
        registerRecipes();
    }

    private static void registerMallets() {
        malletSoulium = new ItemMalletBase(MalletType.SOULIUM);
        MalletRegistry.registerCompatMallet(malletSoulium, "ItemMalletSoulium", ConfigHandler.enableMalletSoulium);
    }

    private static void registerRecipes() {
        if (malletSoulium != null && !OreDictionary.getOres("ingotSoulium").isEmpty())
            GameRegistry.addRecipe(new ShapedOreRecipe(malletSoulium, "M ", "SM", 'M', "ingotSoulium", 'S', "stickWood"));
    }
}
