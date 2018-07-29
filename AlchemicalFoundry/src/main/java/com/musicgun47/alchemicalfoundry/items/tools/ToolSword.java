package com.musicgun47.alchemicalfoundry.items.tools;

import com.musicgun47.alchemicalfoundry.Main;
import com.musicgun47.alchemicalfoundry.init.ModItems;
import com.musicgun47.alchemicalfoundry.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ToolSword extends ItemSword implements IHasModel
{
	public ToolSword(String name, ToolMaterial material) 
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModel() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
}
