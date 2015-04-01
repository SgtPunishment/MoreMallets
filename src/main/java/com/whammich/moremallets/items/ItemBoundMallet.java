package com.whammich.moremallets.items;

import net.minecraft.item.Item;

import com.whammich.roadblock.item.ItemMallet;

public class ItemBoundMallet extends ItemMallet {

	String toolMaterial;
	String tex;

	public ItemBoundMallet(Item.ToolMaterial material, String handle,
			String head, String texture) {
		super(material, handle, head, texture);
	}
}
