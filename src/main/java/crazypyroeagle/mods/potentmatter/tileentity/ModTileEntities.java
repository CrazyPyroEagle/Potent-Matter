package crazypyroeagle.mods.potentmatter.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
	public static void initTileEntities() {
		GameRegistry.registerTileEntity(TileEntityEnergyPipe.class, "energy_pipe");
	}
}
