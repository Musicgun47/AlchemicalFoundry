package com.musicgun47.alchemicalfoundry.items.armor;

import com.musicgun47.alchemicalfoundry.Main;
import com.musicgun47.alchemicalfoundry.init.ModItems;
import com.musicgun47.alchemicalfoundry.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBase extends ItemArmor implements IHasModel
{

	public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
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
