package crazypyroeagle.mods.potentmatter.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import crazypyroeagle.mods.potentmatter.PotentMatter;
import crazypyroeagle.mods.potentmatter.block.ModBlocks;
import crazypyroeagle.mods.potentmatter.item.ModItems;
import crazypyroeagle.mods.potentmatter.server.ServerProxy;

public class ClientProxy extends ServerProxy {
	@Override
	public void registerRenderers() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ModItems.wrench, 0, new ModelResourceLocation(String.format("%s:%s", PotentMatter.MOD_ID, "wrench"), "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(ModBlocks.energyPipe), 0, new ModelResourceLocation(String.format("%s:%s", PotentMatter.MOD_ID, "energy_pipe"), "inventory"));
	}
}
