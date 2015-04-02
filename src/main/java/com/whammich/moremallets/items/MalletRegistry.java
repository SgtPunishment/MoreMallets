package com.whammich.moremallets.items;

import com.whammich.moremallets.compat.CompatRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class MalletRegistry {

    public static void registerMallets() {
        CompatRegistry.registerCompat();
    }

    /**
     *
     * @param item - Item to register
     * @param name - Name of Item to register
     * @param config - Does the config allow the item
     */
    public static void registerCompatMallet(Item item, String name, boolean config) {
        if (config)
            GameRegistry.registerItem(item, name);
    }
}
