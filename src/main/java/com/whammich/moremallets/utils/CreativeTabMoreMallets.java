package com.whammich.moremallets.utils;

import com.whammich.roadblock.utils.Register;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabMoreMallets extends CreativeTabs {

    public CreativeTabMoreMallets(String tabLabel) {
        super(tabLabel);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Register.diamondMallet);
    }

    @Override
    public Item getTabIconItem() {
        return new Item();
    }
}