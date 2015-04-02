package com.whammich.moremallets.compat;

import com.whammich.moremallets.items.MalletRegistry;
import com.whammich.moremallets.items.MalletType;
import com.whammich.moremallets.items.mallets.ItemMalletMana;
import com.whammich.moremallets.utils.ConfigHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CompatBotania {

    public static Item malletManasteel = null;
    public static Item malletElementium = null;
    public static Item malletTerrasteel = null;

    public static void load() {
        registerMallets();
        registerRecipes();
    }

    private static void registerMallets() {
        malletManasteel = new ItemMalletMana(MalletType.MANASTEEL);
        MalletRegistry.registerCompatMallet(malletManasteel, "ItemMalletManasteel", ConfigHandler.enableMalletManasteel);

        malletElementium = new ItemMalletMana(MalletType.ELEMENTIUM);
        MalletRegistry.registerCompatMallet(malletElementium, "ItemMalletElementium", ConfigHandler.enableMalletElementium);

        malletTerrasteel = new ItemMalletMana(MalletType.TERRASTEEL);
        MalletRegistry.registerCompatMallet(malletTerrasteel, "ItemMalletTerrasteel", ConfigHandler.enableMalletTerrasteel);
    }

    private static void registerRecipes() {
        if (ConfigHandler.enableMalletManasteel && !OreDictionary.getOres("ingotManasteel").isEmpty())
            GameRegistry.addRecipe(new ShapedOreRecipe(malletManasteel, "M ", "SM", 'M', "ingotManasteel", 'S', "livingwoodTwig"));

        if (ConfigHandler.enableMalletElementium && !OreDictionary.getOres("ingotElvenElementium").isEmpty())
            GameRegistry.addRecipe(new ShapedOreRecipe(malletElementium, "M ", "SM", 'M', "ingotElvenElementium", 'S', "dreamwoodTwig"));

        if (ConfigHandler.enableMalletTerrasteel && !OreDictionary.getOres("ingotTerrasteel").isEmpty())
            GameRegistry.addRecipe(new ShapedOreRecipe(malletTerrasteel, "M ", "SM", 'M', "ingotTerrasteel", 'S', "livingwoodTwig"));
    }
}
