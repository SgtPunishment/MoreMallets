package com.whammich.moremallets;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;

import com.whammich.moremallets.utils.CreativeTabMoreMallets;
import com.whammich.moremallets.utils.Modlogger;
import com.whammich.moremallets.utils.Reference;
import com.whammich.moremallets.items.ItemBotaniaMallet;
import com.whammich.roadblock.item.ItemMallet;
import com.whammich.moremallets.items.ItemUnstableMallet;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.modid, name = Reference.name, version = Reference.version, dependencies = Reference.dependencies)
public class MoreMallets {

	public static Item souliumMallet;
	public static Item manaMallet;
	public static Item terraMallet;
	public static Item elementMallet;
	public static Item unstableMallet;

	@Instance(Reference.modid)
	public static MoreMallets instance;

	public static CreativeTabs tabMoreMallets = new CreativeTabMoreMallets(
			Reference.modid + ".creativeTab");

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		// Register Items
		Modlogger.info("Registering Items");
		if (Loader.isModLoaded("SSTOW")) {
			souliumMallet = new ItemMallet(
					com.whammich.sstow.utils.Register.SOULIUM, "stickWood",
					"ingotSoulium").setCreativeTab(tabMoreMallets);
			GameRegistry.registerItem(souliumMallet, "SouliumMallet");
		}
		if (Loader.isModLoaded("Botania")) {
			manaMallet = new ItemBotaniaMallet(
					vazkii.botania.api.BotaniaAPI.manasteelToolMaterial,
					"livingwoodTwig", "ingotManasteel")
					.setCreativeTab(tabMoreMallets);
			GameRegistry.registerItem(manaMallet, "ManasteelMallet");

			terraMallet = new ItemBotaniaMallet(
					vazkii.botania.api.BotaniaAPI.terrasteelToolMaterial,
					"livingwoodTwig", "ingotTerrasteel")
					.setCreativeTab(tabMoreMallets);
			GameRegistry.registerItem(terraMallet, "TerrasteelMallet");

			elementMallet = new ItemBotaniaMallet(
					vazkii.botania.api.BotaniaAPI.elementiumToolMaterial,
					"dreamwoodTwig", "ingotElvenElementium")
					.setCreativeTab(tabMoreMallets);
			GameRegistry.registerItem(elementMallet, "ElementiumMallet");
		}

		if (Loader.isModLoaded("ExtraUtilities")) {
			unstableMallet = new ItemUnstableMallet(ToolMaterial.EMERALD,
					"blockObsidian", "ingotUnstable").setCreativeTab(tabMoreMallets);
			GameRegistry.registerItem(unstableMallet, "UnstableMallet");
		}
	}
}