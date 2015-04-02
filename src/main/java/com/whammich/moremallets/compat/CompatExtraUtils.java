package com.whammich.moremallets.compat;

import com.whammich.moremallets.items.ItemMalletBase;
import com.whammich.moremallets.items.MalletRegistry;
import com.whammich.moremallets.items.MalletType;
import com.whammich.moremallets.items.mallets.ItemMalletUnstable;
import com.whammich.moremallets.utils.ConfigHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CompatExtraUtils {

    public static Item malletUnstable = null;
    public static Item malletBedrockium = null;

    public static void load() {
        registerMallets();
        registerRecipes();
    }

    private static void registerMallets() {
        malletUnstable = new ItemMalletUnstable();
        MalletRegistry.registerCompatMallet(malletUnstable, "ItemMalletUnstable", ConfigHandler.enableMalletUnstable);

        malletBedrockium = new ItemMalletBase(MalletType.BEDROCKIUM);
        MalletRegistry.registerCompatMallet(malletBedrockium, "ItemMalletBedrockium", ConfigHandler.enableMalletBedrockium);
    }

    private static void registerRecipes() {
        Item ingotBedrockium = GameRegistry.findItem("ExtraUtilities", "bedrockiumIngot");

        if (ConfigHandler.enableMalletUnstable && !OreDictionary.getOres("ingotUnstable").isEmpty())
            GameRegistry.addRecipe(new ShapedOreRecipe(malletUnstable, "M ", "SM", 'M', "ingotUnstable", 'S', Blocks.obsidian));
        if (ConfigHandler.enableMalletBedrockium && ingotBedrockium != null)
            GameRegistry.addRecipe(new ShapedOreRecipe(malletBedrockium, "M ", "SM", 'M', ingotBedrockium, 'S', "ingotUnstable"));
    }
}
