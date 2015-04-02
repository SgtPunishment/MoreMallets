package com.whammich.moremallets.items.mallets;

import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import com.whammich.moremallets.items.ItemMalletBase;
import com.whammich.moremallets.items.MalletType;
import com.whammich.moremallets.utils.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemMalletBound extends ItemMalletBase implements IBindable {

	private static IIcon active;
	private static IIcon passive;

	public ItemMalletBound() {
        super(MalletType.BOUND);

        this.attackDmg = 3.0F;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		SoulNetworkHandler.checkAndSetItemOwner(stack, player);
		if (player.isSneaking()) {
			setActivated(stack, !getActivated(stack));
			stack.stackTagCompound.setInteger("worldTimeDelay", (int) (world.getWorldTime() - 1) % 100);
			return stack;
		}
		if (!getActivated(stack))
			return stack;
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		active = iconRegister.registerIcon(Reference.modid + ":mallet_bound");
        passive = iconRegister.registerIcon("AlchemicalWizardry:SheathedItem");
        itemIcon = active;
    }

	@Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		if (stack.stackTagCompound == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = stack.stackTagCompound;
		if (tag.getBoolean("isActive"))
			return active;
		else
			return passive;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		if (!(entity instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer) entity;
		if (stack.stackTagCompound == null)
			stack.setTagCompound(new NBTTagCompound());

		if (world.getWorldTime() % 200 == stack.stackTagCompound.getInteger("worldTimeDelay") && stack.stackTagCompound.getBoolean("isActive")) {
			if (!player.capabilities.isCreativeMode)
				SoulNetworkHandler.syphonAndDamageFromNetwork(stack, player, 20);
		}

		stack.setItemDamage(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (stack.stackTagCompound != null) {
			if (stack.stackTagCompound.getBoolean("isActive"))
				list.add(StatCollector.translateToLocal("ttip.moremallets.bound.activated"));
			else
				list.add(StatCollector.translateToLocal("ttip.moremallets.bound.deactivated"));
			if (!stack.stackTagCompound.getString("ownerName").equals(""))
				list.add(String.format(StatCollector.translateToLocal("ttip.moremallets.bound.owner"), stack.stackTagCompound.getString("ownerName")));
		}
	}

	// Bound stuff
	public void setActivated(ItemStack stack, boolean newActivated) {
		NBTTagCompound itemTag = stack.stackTagCompound;
		if (itemTag == null)
			stack.setTagCompound(new NBTTagCompound());

		itemTag.setBoolean("isActive", newActivated);
	}

	public boolean getActivated(ItemStack stack) {
		NBTTagCompound itemTag = stack.stackTagCompound;
		if (itemTag == null)
			stack.setTagCompound(new NBTTagCompound());

		return itemTag.getBoolean("isActive");
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase entity,
			EntityLivingBase player) {
		stack.damageItem(1, player);
		return true;
	}

    @Override
    public boolean canMallet(World world, EntityPlayer player, ItemStack stack) {
        NBTTagCompound itemTag = stack.stackTagCompound;
        if (itemTag == null)
            stack.setTagCompound(new NBTTagCompound());

        return itemTag.getBoolean("isActive");
    }

    @Override
    public boolean onMallet(World world, EntityPlayer player, ItemStack stack) {
        return false;
    }
}
