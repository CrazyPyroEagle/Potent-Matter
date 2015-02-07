package crazypyroeagle.mods.potentmatter;

import crazypyroeagle.mods.potentmatter.block.ModBlocks;
import crazypyroeagle.mods.potentmatter.item.ModItems;
import crazypyroeagle.mods.potentmatter.server.ServerProxy;
import crazypyroeagle.mods.potentmatter.tileentity.ModTileEntities;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PotentMatter.MOD_ID, name = PotentMatter.MOD_NAME, version = PotentMatter.MOD_VERSION)
public class PotentMatter {
	public static final String MOD_ID = "potentmatter";
	public static final String MOD_NAME = "Potent Matter";
	public static final String MOD_VERSION = "@VERSION_INFO@";
	
	/*@Instance(PotentMatter.MOD_ID)
	private static PotentMatter instance;
	
	/public static PotentMatter instance() {
		return instance;
	}*/					// Not needed yet
	
	@SidedProxy(clientSide="crazypyroeagle.mods.potentmatter.client.ClientProxy", serverSide="crazypyroeagle.mods.potentmatter.server.ServerProxy")
	private static ServerProxy proxy;
	
	public static EnumFacing getOppositeDirection(EnumFacing direction) {
		switch (direction) {
			case UP:
				return EnumFacing.DOWN;
			case DOWN:
				return EnumFacing.UP;
			case NORTH:
				return EnumFacing.SOUTH;
			case SOUTH:
				return EnumFacing.SOUTH;
			case WEST:
				return EnumFacing.EAST;
			case EAST:
				return EnumFacing.WEST;
			default:
				return null;
		}
	}
	
	public void preInit(FMLPreInitializationEvent event) {
		// Load config
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModBlocks.initBlocks();
		ModItems.initItems();
		ModTileEntities.initTileEntities();

		proxy.registerRenderers();
	}
}
