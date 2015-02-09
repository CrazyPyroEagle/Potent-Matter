package crazypyroeagle.mods.potentmatter.block;

import java.util.Random;

import crazypyroeagle.mods.potentmatter.PotentMatter;
import crazypyroeagle.mods.potentmatter.tileentity.TileEntityEnergyPipe;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEnergyPipe extends Block implements ITileEntityProvider, IConnectable {
	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool DOWN = PropertyBool.create("down");
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool EAST = PropertyBool.create("east");
	
	private String[] connectionTypes = {"energy"};
	
	protected BlockEnergyPipe(Material materialIn) {
		super(materialIn);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		// TODO Update dis shtuff
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean isFullCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
	
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {UP, DOWN, NORTH, SOUTH, WEST, EAST});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(UP, this.checkConnection(worldIn.getBlockState(pos.offset(EnumFacing.UP)).getBlock(), EnumFacing.UP)).withProperty(DOWN, this.checkConnection(worldIn.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock(), EnumFacing.DOWN)).withProperty(NORTH, this.checkConnection(worldIn.getBlockState(pos.offset(EnumFacing.NORTH)).getBlock(), EnumFacing.NORTH)).withProperty(SOUTH, this.checkConnection(worldIn.getBlockState(pos.offset(EnumFacing.SOUTH)).getBlock(), EnumFacing.SOUTH)).withProperty(WEST, this.checkConnection(worldIn.getBlockState(pos.offset(EnumFacing.WEST)).getBlock(), EnumFacing.WEST)).withProperty(EAST, this.checkConnection(worldIn.getBlockState(pos.offset(EnumFacing.EAST)).getBlock(), EnumFacing.EAST));
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem().getToolClasses(player.inventory.getCurrentItem()).contains("wrench") && player.isSneaking()) {
			// The player has sneak-right clicked this block... Time to disassemble :D
			if (world.getBlockState(pos).getBlock() instanceof BlockEnergyPipe) {
				IBlockState metadata = world.getBlockState(pos);		// Store this since we'll be deleting the block
				world.setBlockToAir(pos);
				player.inventory.getCurrentItem().damageItem(1, player);
				if (!world.isRemote && !((EntityPlayerMP) player).theItemInWorldManager.isCreative())
					this.dropBlockAsItem(world, pos, metadata, 1);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos).getBlock() instanceof IConnectable) {
			IConnectable connectable = (IConnectable) worldIn.getBlockState(pos).getBlock();
			float x1 = connectable.checkConnection(worldIn.getBlockState(pos.west()).getBlock(), EnumFacing.WEST) ? 0F : 5F/16F;
			float y1 = connectable.checkConnection(worldIn.getBlockState(pos.down()).getBlock(), EnumFacing.DOWN) ? 0F : 5F/16F;
			float z1 = connectable.checkConnection(worldIn.getBlockState(pos.north()).getBlock(), EnumFacing.NORTH) ? 0F : 5F/16F;
			float x2 = connectable.checkConnection(worldIn.getBlockState(pos.east()).getBlock(), EnumFacing.EAST) ? 1F : 11F/16F;
			float y2 = connectable.checkConnection(worldIn.getBlockState(pos.up()).getBlock(), EnumFacing.UP) ? 1F : 11F/16F;
			float z2 = connectable.checkConnection(worldIn.getBlockState(pos.south()).getBlock(), EnumFacing.SOUTH) ? 1F : 11F/16F;
			
			this.setBlockBounds(x1, y1, z1, x2, y2, z2);
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		this.setBlockBoundsBasedOnState(worldIn, pos);
		return super.getCollisionBoundingBox(worldIn, pos, state);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityEnergyPipe();
	}

	@Override
	public String[] getConnectionTypes(EnumFacing side) {
		return connectionTypes;
	}

	@Override
	public boolean checkConnection(Block block, EnumFacing side) {
		if (!(block instanceof IConnectable))
			return false;
		
		IConnectable connectable = (IConnectable) block;
		EnumFacing opposite = PotentMatter.getOppositeDirection(side);
		
		boolean connected = false;
		for (String type : this.getConnectionTypes(side)) {
			for (String type2 : connectable.getConnectionTypes(opposite)) {
				if (type.equalsIgnoreCase(type2)) {
					connected = true;
					break;
				}
			}
			
			if (connected)
				break;
		}
		
		return connected && this.allowConnection(side) && connectable.allowConnection(opposite);
	}

	@Override
	public boolean allowConnection(EnumFacing side) {
		return true;
	}
}