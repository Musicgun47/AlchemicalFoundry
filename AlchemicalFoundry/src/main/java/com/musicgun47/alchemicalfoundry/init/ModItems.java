package com.musicgun47.alchemicalfoundry.init;

import java.util.List;
import java.util.ArrayList;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

import com.musicgun47.alchemicalfoundry.items.ItemBase;
import com.musicgun47.alchemicalfoundry.items.tools.ToolSword;

public class ModItems 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Tool Materials
	public static final ToolMaterial MATERIAL_BRONZE = EnumHelper.addToolMaterial("material_bronze", 2, 375, 6.0f, 2.0f, 12);
	
	//Items
	public static final Item RESIN = new ItemBase("resin");
	public static final Item AMBER = new ItemBase("amber");
	public static final Item JADED_NECTAR = new ItemBase("jaded_nectar");
	public static final Item ALCHEMICAL_POWDER = new ItemBase("alchemical_powder");
	public static final Item ALCHEMICAL_REAGENT = new ItemBase("alchemical_reagent");
	public static final Item COAL_DUST = new ItemBase("coal_dust");
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot");
	public static final Item TIN_INGOT = new ItemBase("tin_ingot");
	
	//Tools
	public static final ItemSword BRONZE_SWORD = new ToolSword("bronze_sword", MATERIAL_BRONZE);
}
