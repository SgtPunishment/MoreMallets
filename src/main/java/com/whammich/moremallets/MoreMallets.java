package com.whammich.moremallets;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import WayofTime.alchemicalWizardry.AlchemicalWizardry;
import WayofTime.alchemicalWizardry.api.bindingRegistry.BindingRegistry;

import com.whammich.moremallets.items.ItemBotaniaMallet;
import com.whammich.moremallets.items.ItemBoundMallet;
import com.whammich.moremallets.items.ItemUnstableMallet;
import com.whammich.moremallets.utils.CreativeTabMoreMallets;
import com.whammich.moremallets.utils.Modlogger;
import com.whammich.moremallets.utils.Reference;
import com.whammich.roadblock.item.ItemMallet;
import com.whammich.roadblock.utils.Register;

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
	public static Item bedrockMallet;
	public static Item boundMallet;

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
			souliumMallet = new ItemMallet(com.whammich.sstow.utils.Register.SOULIUM, "stickWood", "ingotSoulium", Reference.modid + ":mallet_soulium").setCreativeTab(tabMoreMallets).setUnlocalizedName(Reference.modid + ".mallet.soulium");
			GameRegistry.registerItem(souliumMallet, "SouliumMallet");
		}
		
		if (Loader.isModLoaded("Botania")) {
			manaMallet = new ItemBotaniaMallet(vazkii.botania.api.BotaniaAPI.manasteelToolMaterial, "livingwoodTwig", "ingotManasteel", Reference.modid + ":mallet_manasteel").setCreativeTab(tabMoreMallets);
			GameRegistry.registerItem(manaMallet, "ManasteelMallet");

			terraMallet = new ItemBotaniaMallet(vazkii.botania.api.BotaniaAPI.terrasteelToolMaterial, "livingwoodTwig", "ingotTerrasteel", Reference.modid + ":mallet_terrasteel").setCreativeTab(tabMoreMallets);
			GameRegistry.registerItem(terraMallet, "TerrasteelMallet");

			elementMallet = new ItemBotaniaMallet(vazkii.botania.api.BotaniaAPI.elementiumToolMaterial, "dreamwoodTwig", "ingotElvenElementium", Reference.modid + ":mallet_elementium").setCreativeTab(tabMoreMallets);
			GameRegistry.registerItem(elementMallet, "ElementiumMallet");
		}

		if (Loader.isModLoaded("ExtraUtilities")) {
			unstableMallet = new ItemUnstableMallet(ToolMaterial.EMERALD, "blockObsidian", "ingotUnstable", Reference.modid + ":mallet_unstable").setCreativeTab(tabMoreMallets);
			GameRegistry.registerItem(unstableMallet, "UnstableMallet");
			
			Item bedrockiumIngot = GameRegistry.findItem("ExtraUtilities", "bedrockiumIngot");
			
			bedrockMallet = new ItemMallet(ToolMaterial.EMERALD, "stickWood", bedrockiumIngot.toString(), Reference.modid + ":mallet_bedrockium").setCreativeTab(tabMoreMallets).setUnlocalizedName(Reference.modid + ".mallet.bedrockium").setMaxDamage(-1);
			GameRegistry.registerItem(bedrockMallet, "BedrockiumMallet");
		}
		
		if(Loader.isModLoaded("AWWayofTime")) {
			boundMallet = new ItemBoundMallet(AlchemicalWizardry.bloodBoundToolMaterial, "stickWood", "ingotSoulium", Reference.modid + ":mallet_bound").setCreativeTab(tabMoreMallets);
			GameRegistry.registerItem(boundMallet, "BoundMallet");
			BindingRegistry.registerRecipe(new ItemStack(boundMallet), new ItemStack(Register.diamondMallet));
		}
	}
}