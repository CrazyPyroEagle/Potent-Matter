package crazypyroeagle.mods.potentmatter.block;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
public static BlockEnergyPipe energyPipe;
	
	public static void initBlocks() {
		energyPipe = new BlockEnergyPipe(Material.rock);
		
		GameRegistry.registerBlock(energyPipe, "energy_pipe");
	}
}
