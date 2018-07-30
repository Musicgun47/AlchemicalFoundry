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
		for(EnumFacing facing : EnumFacing.Plane.HORIZONTAL)
		{
			if(worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof AlchemistsTable && canConnect(worldIn, pos, facing)
					&& canConnect(worldIn, pos.offset(facing), facing.getOpposite()))
			{
				worldIn.setBlockState(pos, connect(worldIn, pos, facing), 1);
				worldIn.setBlockState(pos.offset(facing), connect(worldIn, pos.offset(facing), facing.getOpposite()), 1);
			}
		}
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (fromPos.equals(pos.down()) && !worldIn.getBlockState(fromPos).isSideSolid(worldIn, pos.down(), EnumFacing.UP))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }
        else if(!fromPos.equals(pos.up()) && worldIn.getBlockState(pos).getValue(CONNECTIONS) > 0)
        {
        	for(EnumFacing facing : EnumFacing.Plane.HORIZONTAL)
        	{
        		if(worldIn.getBlockState(pos).getValue(CONNECTIONS) == 1)
        		{
        			if(!(worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof AlchemistsTable) &&
        					worldIn.getBlockState(pos).getValue(FACING).equals(facing.rotateY()))
        			{
        				worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(CONNECTIONS, 0));
        			}
        		}
        		else if(worldIn.getBlockState(pos).getValue(CONNECTIONS) == 2)
        		{
        			if(!(worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof AlchemistsTable) &&
        					!facing.getAxis().equals(worldIn.getBlockState(pos).getValue(FACING).getAxis()))
        			{
        				if(worldIn.getBlockState(pos).getValue(FACING).equals(facing.rotateY()))
        				{
	        				worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(CONNECTIONS, 1)
	        					.withProperty(FACING, facing.rotateYCCW()));
        				}
        				else
        				{
        					worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(CONNECTIONS, 1));
        				}
        			}
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
	
	public IBlockState connect(IBlockAccess worldIn, BlockPos pos, EnumFacing facing)
	{
		IBlockState state = worldIn.getBlockState(pos);
		if(state.getValue(CONNECTIONS) == 0)
		{
			return state.withProperty(FACING, facing.rotateY()).withProperty(CONNECTIONS, 1);
		}
		else if(state.getValue(CONNECTIONS) == 1 && !state.getValue(FACING).equals(facing.rotateY()))
		{
			return state.withProperty(CONNECTIONS, 2);
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