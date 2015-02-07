package crazypyroeagle.mods.potentmatter.item;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemWrench extends ItemTool {
	private Set<String> toolClasses;
	
	public ItemWrench(String name) {
		super(1.0F, ToolMaterial.IRON, Collections.EMPTY_SET);
		this.setUnlocalizedName(name);
		this.setFull3D();
		this.setMaxDamage(128);
		toolClasses = ImmutableSet.of("wrench");
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		return worldIn.getBlockState(pos).getBlock().onBlockActivated(worldIn, pos, worldIn.getBlockState(pos), playerIn, side, hitX, hitY, hitZ);
	}
	
	@Override
	public Set<String> getToolClasses(ItemStack itemStack) {
		if (itemStack.getItem() instanceof ItemWrench)
			return toolClasses;
		return itemStack.getItem().getToolClasses(itemStack);
	}
}
