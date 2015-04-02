package com.whammich.moremallets.items;

import com.google.common.collect.Multimap;
import com.whammich.moremallets.MoreMallets;
import com.whammich.moremallets.utils.Reference;
import com.whammich.roadblock.block.BlockRoadBase;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMalletBase extends Item {

    public MalletType type;
    public float attackDmg = 2.0F;

    /**
     *
     * @param type - Pre-defined mallet type from {@link com.whammich.moremallets.items.MalletType}
     */
    public ItemMalletBase(MalletType type) {
        this(type.name(), type.durability);

        this.type = type;
    }

    /**
     *
     * @param name - Unlocalized name for mallet.
     * @param durability - Effective durability of mallet.
     */
    public ItemMalletBase(String name, int durability) {
        super();

        setUnlocalizedName(Reference.modid + ".mallet." + name.toLowerCase());
        setTextureName(Reference.modid + ":mallet_" + name.toLowerCase());
        setCreativeTab(MoreMallets.tabMoreMallets);
        setMaxDamage(durability);
        setMaxStackSize(1);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int xCoord, int yCoord, int zCoord, int side, float hitX, float hitY, float hitZ) {
        if (world.getBlock(xCoord, yCoord, zCoord) instanceof BlockRoadBase && canMallet(world, player, stack)) {
            if (world.getBlockMetadata(xCoord, yCoord, zCoord) == 0) {
                world.setBlock(xCoord, yCoord, zCoord, world.getBlock(xCoord, yCoord, zCoord), 1, 3);
                world.markBlockForUpdate(xCoord, yCoord, zCoord);
                if (onMallet(world, player, stack))
                    stack.damageItem(1, player);
                return true;
            } else {
                world.setBlock(xCoord, yCoord, zCoord, world.getBlock(xCoord, yCoord, zCoord), 0, 3);
                world.markBlockForUpdate(xCoord, yCoord, zCoord);
                stack.damageItem(1, player);
                if (onMallet(world, player, stack))
                    stack.damageItem(1, player);
                return true;
            }
        }

        return false;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {
        stack.damageItem(1, player);
        return true;
    }

    @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
    public Multimap getItemAttributeModifiers() {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) this.attackDmg, 0));
        return multimap;
    }

    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int xCoord, int yCoord, int zCoord, EntityLivingBase player) {
        if (onMallet(world, (EntityPlayer) player, stack))
            stack.damageItem(1, player);
        return true;
    }

    /**
     * @return - Return true if the mallet can mallet.
     */
    public boolean canMallet(World world, EntityPlayer player, ItemStack stack) {
        return world != null && player != null && stack != null;
    }

    /**
     * @return - Return true if the mallet should take damage, false if not.
     */
    public boolean onMallet(World world, EntityPlayer player, ItemStack stack) {
        return true;
    }
}
