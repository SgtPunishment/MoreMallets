package com.whammich.moremallets.items.mallets;

import com.rwtema.extrautils.item.IItemMultiTransparency;
import com.rwtema.extrautils.item.RenderItemMultiTransparency;
import com.whammich.moremallets.items.ItemMalletBase;
import com.whammich.moremallets.items.MalletType;
import com.whammich.moremallets.utils.Reference;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

@Optional.Interface(iface = "com.twtema.extrautils.item.IItemMultiTransparency", modid = "ExtraUtilities")
public class ItemMalletUnstable extends ItemMalletBase implements IItemMultiTransparency {

	private IIcon[] icons = new IIcon[2];

	public ItemMalletUnstable() {
		super(MalletType.UNSTABLE);
		MinecraftForgeClient.registerItemRenderer(this, new RenderItemMultiTransparency());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister) {
        this.icons[0] = iconRegister.registerIcon(Reference.modid + ":mallet_unstable");
        this.icons[1] = iconRegister.registerIcon(Reference.modid + ":mallet_unstable_overlay");
        this.itemIcon = icons[0];
	}

	@Override
	public int numIcons(ItemStack stack) {
		return 2;
	}

	@Override
	public IIcon getIconForTransparentRender(ItemStack stack, int pass) {
		return this.icons[pass];
	}

	@Override
	public float getIconTransparency(ItemStack stack, int pass) {
		if (pass == 1) {
			return 0.5F;
		}
		return 1.0F;
	}

    @Override
    public boolean onMallet(World world, EntityPlayer player, ItemStack stack) {
        return false;
    }
}
