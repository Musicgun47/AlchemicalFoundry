package com.musicgun47.alchemicalfoundry.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes 
{
	public static void init()
	{
		GameRegistry.addSmelting(ModBlocks.COPPER_ORE, new ItemStack(ModItems.COPPER_INGOT, 1), 2.5f);
		GameRegistry.addSmelting(ModBlocks.TIN_ORE, new ItemStack(ModItems.TIN_INGOT, 1), 2.5f);
	}
}
