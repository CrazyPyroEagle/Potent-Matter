package crazypyroeagle.mods.potentmatter;

import crazypyroeagle.mods.potentmatter.item.ModItems;
import crazypyroeagle.mods.potentmatter.server.ServerProxy;
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
	
	public void preInit(FMLPreInitializationEvent event) {
		// Load config + textures?
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
		ModItems.initItems();
	}
}
