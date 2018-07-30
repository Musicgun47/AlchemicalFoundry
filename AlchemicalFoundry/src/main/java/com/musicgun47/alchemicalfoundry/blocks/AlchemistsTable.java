package com.musicgun47.alchemicalfoundry.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AlchemistsTable extends BlockBase
{
//	public static final PropertyBool NORTH = PropertyBool.create("north");
//	public static final PropertyBool SOUTH = PropertyBool.create("south");
//	public static final PropertyBool EAST = PropertyBool.create("east");
//	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	protected static final PropertyInteger CONNECTIONS = PropertyInteger.create("connections", 0, 2);
	//net.minecraft.block.BlockFence
			
	public AlchemistsTable(String name, Material material) 
	{
		super(name, material);
		
		setSoundType(SoundType.WOOD);
		setHardness(3.0f);
		setResistance(20.0f);
		setHarvestLevel("axe", 1);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(CONNECTIONS, 0));
	}
	
	public boolean isOpaqueCube(IBlockState blockstate) 
	{
		return false;
	}
	
	@Override
	public BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, FACING, CONNECTIONS);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack)
	{
		EnumFacing placedFacing = placer.getHorizontalFacing();
		worldIn.setBlockState(pos, state.withProperty(FACING, placedFacing.getOpposite()), 1);
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (fromPos.equals(pos.down()) && !worldIn.getBlockState(fromPos).isSideSolid(worldIn, pos.down(), EnumFacing.UP))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }
        else if(!fromPos.equals(pos.up()))
        {
        	for(EnumFacing facing : EnumFacing.Plane.HORIZONTAL)
        	{
        		if(worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof AlchemistsTable && canConnect(worldIn, pos, facing) && 
        				canConnect(worldIn, pos.offset(facing), facing.getOpposite()))
        		{
        			worldIn.setBlockState(pos, connect(worldIn, pos, facing), 1);
        			worldIn.setBlockState(pos.offset(facing), connect(worldIn, pos.offset(facing), facing.getOpposite()), 1);
        		}
        	}
        }
    }
	
	public boolean canConnect(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) 
	{
		if(worldIn.getBlockState(pos).getValue(CONNECTIONS) > 0)
		{
			EnumFacing blockFacing = worldIn.getBlockState(pos).getValue(FACING);
			if(blockFacing.equals(facing) || blockFacing.equals(facing.getOpposite()))
			{
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("incomplete-switch")
	public IBlockState connect(IBlockAccess worldIn, BlockPos pos, EnumFacing facing)
	{
		IBlockState state = worldIn.getBlockState(pos);
		switch(facing)
		{
		case NORTH:		if(state.getValue(CONNECTIONS) == 0) {state = state.withProperty(FACING, facing.rotateY());}
						state = state.withProperty(CONNECTIONS, state.getValue(CONNECTIONS)+1);
						break;
		case SOUTH:		if(state.getValue(CONNECTIONS) == 0) {state = state.withProperty(FACING, facing.rotateY());}
						state = state.withProperty(CONNECTIONS, state.getValue(CONNECTIONS)+1);
						break;
		case EAST:		if(state.getValue(CONNECTIONS) == 0) {state = state.withProperty(FACING, facing.rotateY());}
						state = state.withProperty(CONNECTIONS, state.getValue(CONNECTIONS)+1);
						break;
		case WEST:		if(state.getValue(CONNECTIONS) == 0) {state = state.withProperty(FACING, facing.rotateY());}
						state = state.withProperty(CONNECTIONS, state.getValue(CONNECTIONS)+1);
						break;
		}
		return state;
	}
	
	public int getMetaFromState(IBlockState state) 
	{
		int i = 0;
		if(state.getValue(CONNECTIONS) > 0)
		{
			i = i | 4;
			if(state.getValue(CONNECTIONS) > 1)
			{
				i |= 8;
			}
		}
		i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
		return i;
	}
	
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState state = this.getDefaultState();
		if((meta & 4) > 0)
		{
			state = state.withProperty(CONNECTIONS, 1);
		}
		if((meta & 8) > 0)
		{
			state = state.withProperty(CONNECTIONS, 2);
		}
		state = state.withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
		return state;
	}
	
//	@SuppressWarnings("incomplete-switch")
//	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) 
//	{
//		EnumFacing facing = state.getValue(FACING);
//		if(state.getValue(CONNECTIONS) > 0)
//		{
//			if(state.getValue(CONNECTIONS) > 1)
//			{
//				if(facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH)
//				{
//					state = state.withProperty(EAST, Boolean.valueOf(true)).withProperty(WEST, Boolean.valueOf(true));
//				}
//				else
//				{
//					state = state.withProperty(NORTH, Boolean.valueOf(true)).withProperty(SOUTH, Boolean.valueOf(true));
//				}
//			}
//			else
//			{
//				switch(facing)
//				{
//				case NORTH :	state = state.withProperty(WEST, Boolean.valueOf(true));
//								break;
//				case SOUTH :	state = state.withProperty(EAST, Boolean.valueOf(true));
//								break;
//				case EAST :		state = state.withProperty(NORTH, Boolean.valueOf(true));
//								break;
//				case WEST :		state = state.withProperty(SOUTH, Boolean.valueOf(true));
//								break;
//				}
//			}
//		}
//		else
//		{
//			state = this.getDefaultState().withProperty(FACING, facing);
//		}
//		return state;
//	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_,
			EnumFacing p_193383_4_)
	{
		if(p_193383_4_.equals(EnumFacing.UP)) {
			return BlockFaceShape.SOLID;
		}
		return BlockFaceShape.UNDEFINED;
	}

}