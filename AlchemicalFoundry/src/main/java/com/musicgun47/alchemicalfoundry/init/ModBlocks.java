package com.musicgun47.alchemicalfoundry.init;

import java.util.ArrayList;
import java.util.List;

import com.musicgun47.alchemicalfoundry.blocks.AlchemistsTable;
import com.musicgun47.alchemicalfoundry.blocks.CopperBlock;
import com.musicgun47.alchemicalfoundry.blocks.Tier1Ore;
import com.musicgun47.alchemicalfoundry.blocks.TinBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block COPPER_ORE = new Tier1Ore("copper_ore", Material.ROCK);
	public static final Block TIN_ORE = new Tier1Ore("tin_ore", Material.ROCK);
	public static final Block COPPER_BLOCK = new CopperBlock("copper_block", Material.IRON);
	public static final Block TIN_BLOCK = new TinBlock("tin_block", Material.IRON);
	public static final Block ALCHEMISTS_TABLE = new AlchemistsTable("alchemists_table", Material.WOOD);
}
