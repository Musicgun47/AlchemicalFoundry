package com.musicgun47.alchemicalfoundry.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class Tier1Ore extends BlockBase
{

	public Tier1Ore(String name, Material material) 
	{
		super(name, material);

		setSoundType(SoundType.STONE);
		setHardness(4.0f);
		setResistance(15.0f);
		setHarvestLevel("pickaxe", 1);
	}

}
