package crazypyroeagle.mods.potentmatter.item;

import crazypyroeagle.mods.potentmatter.PotentMatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	public static ItemWrench wrench;
	
	public static void initItems() {
		wrench = new ItemWrench("wrench");
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(wrench, 0, new ModelResourceLocation(String.format("%s:%s", PotentMatter.MOD_ID, "wrench"), "inventory"));
		
		GameRegistry.registerItem(wrench, "wrench");
	}
}
