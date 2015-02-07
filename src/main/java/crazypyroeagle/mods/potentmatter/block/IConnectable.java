package crazypyroeagle.mods.potentmatter.block;

import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;

public interface IConnectable {
	public String[] getConnectionTypes(EnumFacing side);
	public boolean checkConnection(Block block, EnumFacing side);
	public boolean allowConnection(EnumFacing side);
}
