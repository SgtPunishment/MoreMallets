package com.whammich.moremallets;

import net.minecraftforge.common.MinecraftForge;

import com.whammich.moremallets.utils.Modlogger;
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
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		// Register Items
        Modlogger.info("Registering Items");

		// Register Blocks
        Modlogger.info("Registering Blocks");

		// Register Recipes
        Modlogger.info("Registering Recipes");

		// Register Achievements
        Modlogger.info("Registering Achievements");
	}
	
}
