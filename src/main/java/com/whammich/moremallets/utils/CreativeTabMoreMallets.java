package com.whammich.moremallets.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.whammich.roadblock.utils.Register;

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