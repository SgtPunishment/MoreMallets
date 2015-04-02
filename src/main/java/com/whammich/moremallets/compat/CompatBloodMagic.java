package com.whammich.moremallets.compat;

import WayofTime.alchemicalWizardry.api.bindingRegistry.BindingRegistry;
import com.whammich.moremallets.items.MalletRegistry;
import com.whammich.moremallets.items.mallets.ItemMalletBound;
import com.whammich.moremallets.utils.ConfigHandler;
import com.whammich.roadblock.utils.Register;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CompatBloodMagic {

    public static Item malletBound = null;

    public static void load() {
        registerMallets();
        registerRecipes();
    }

    private static void registerMallets() {
        malletBound = new ItemMalletBound();
        MalletRegistry.registerCompatMallet(malletBound, "ItemMalletBound", ConfigHandler.enableMalletBound);
    }

    private static void registerRecipes() {
        BindingRegistry.registerRecipe(new ItemStack(malletBound), new ItemStack(Register.diamondMallet));
    }
}
