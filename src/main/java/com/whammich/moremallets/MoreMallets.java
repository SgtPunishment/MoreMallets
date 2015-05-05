package com.whammich.moremallets;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import com.whammich.moremallets.items.MalletRegistry;
import com.whammich.moremallets.utils.ConfigHandler;
import com.whammich.moremallets.utils.CreativeTabMoreMallets;
import com.whammich.moremallets.utils.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.modid, name = Reference.name, version = Reference.version, dependencies = Reference.dependencies)
public class MoreMallets {

	@Instance(Reference.modid)
	public static MoreMallets instance;

	public static CreativeTabs tabMoreMallets = new CreativeTabMoreMallets(
			Reference.modid + ".creativeTab");

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event);

		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
        MalletRegistry.registerMallets();
	}
}