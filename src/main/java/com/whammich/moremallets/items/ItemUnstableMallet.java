package com.whammich.moremallets.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.MinecraftForgeClient;

import com.rwtema.extrautils.item.IItemMultiTransparency;
import com.rwtema.extrautils.item.RenderItemMultiTransparency;
import com.whammich.roadblock.item.ItemMallet;
import com.whammich.moremallets.utils.Reference;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Optional.Interface(iface = "com.twtema.extrautils.item.IItemMultiTransparency", modid = "ExtraUtilities")
public class ItemUnstableMallet extends ItemMallet implements
		IItemMultiTransparency {

	private IIcon[] icons;
	private String tex;

	public ItemUnstableMallet(Item.ToolMaterial material, String handle, String head, String texture) {
		super(material, handle, head, texture);
		setUnlocalizedName(Reference.modid + ".mallet.unstable");
		setMaxDamage(-1);
		tex = texture;
		MinecraftForgeClient.registerItemRenderer(this, new RenderItemMultiTransparency());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister) {
		this.icons = new IIcon[2];
		this.itemIcon = (this.icons[0] = iconRegister.registerIcon(tex));
		this.icons[1] = iconRegister.registerIcon(tex + "1");
		// itemIcon = iconRegister.registerIcon(Reference.modid +
		// ":unstablemallet");
	}

	@Override
	public int numIcons(ItemStack item) {
		return 2;
	}

	@Override
	public IIcon getIconForTransparentRender(ItemStack item, int pass) {
		return this.icons[pass];
	}

	@Override
	public float getIconTransparency(ItemStack item, int pass) {
		if (pass == 1) {
			return 0.5F;
		}
		return 1.0F;
	}

}
