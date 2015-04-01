package com.whammich.moremallets.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;

import com.google.common.collect.Multimap;
import com.whammich.moremallets.utils.Reference;
import com.whammich.roadblock.block.BlockRoadBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBoundMallet extends Item implements IBindable {

	private static IIcon active;
	private static IIcon passive;

	private float DamVEnt;
	String tex;

	public ItemBoundMallet(Item.ToolMaterial material, String handle, String head, String texture) {
		super();
		tex = texture;
		setUnlocalizedName(Reference.modid + ".mallet.bound");
		setMaxStackSize(1);
		setMaxDamage(material.getMaxUses());
		DamVEnt = 2.0F + material.getDamageVsEntity();
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
		itemIcon = iconRegister.registerIcon(tex);
		active = iconRegister.registerIcon(tex);
		passive = iconRegister.registerIcon("AlchemicalWizardry:SheathedItem");
	}

	@Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player,
			ItemStack usingItem, int useRemaining) {
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
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4,
			boolean par5) {
		if (!(entity instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) entity;
		if (stack.stackTagCompound == null)
			stack.setTagCompound(new NBTTagCompound());
		if (world.getWorldTime() % 200 == stack.stackTagCompound
				.getInteger("worldTimeDelay")
				&& stack.stackTagCompound.getBoolean("isActive")) {
			if (!player.capabilities.isCreativeMode)
				SoulNetworkHandler
						.syphonAndDamageFromNetwork(stack, player, 20);
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

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int xCoord, int yCoord, int zCoord, int side, float hitX, float hitY, float hitZ) {
		
		NBTTagCompound tag = stack.stackTagCompound;
		
		if(tag.getBoolean("isActive")&&!player.isSneaking()) {
			if (world.getBlock(xCoord, yCoord, zCoord) instanceof BlockRoadBase) {
				if (world.getBlockMetadata(xCoord, yCoord, zCoord) == 0) {
					world.setBlock(xCoord, yCoord, zCoord, world.getBlock(xCoord, yCoord, zCoord), 1, 3);
					world.markBlockForUpdate(xCoord, yCoord, zCoord);
					stack.damageItem(1, player);
					return true;
				} else {
					world.setBlock(xCoord, yCoord, zCoord, world.getBlock(xCoord, yCoord, zCoord), 0, 3);
					world.markBlockForUpdate(xCoord, yCoord, zCoord);
					stack.damageItem(1, player);
					return true;
				}
			}
		}
		return false;
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

	public boolean onBlockDestroyed(ItemStack stack, World world, Block block,
			int xCoord, int yCoord, int zCoord, EntityLivingBase player) {
		if ((double) block.getBlockHardness(world, xCoord, yCoord, zCoord) != 0.0D)
			stack.damageItem(2, player);
		return true;
	}

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage
				.getAttributeUnlocalizedName(), new AttributeModifier(
				field_111210_e, "Weapon modifier", (double) this.DamVEnt, 0));
		return multimap;
	}
	
}
