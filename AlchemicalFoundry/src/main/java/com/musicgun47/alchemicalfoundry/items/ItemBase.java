package com.musicgun47.alchemicalfoundry.items;

import com.musicgun47.alchemicalfoundry.Main;
import com.musicgun47.alchemicalfoundry.init.ModItems;
import com.musicgun47.alchemicalfoundry.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
	
	public ItemBase(String name) 
	{
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
