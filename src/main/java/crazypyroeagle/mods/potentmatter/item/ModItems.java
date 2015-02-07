package crazypyroeagle.mods.potentmatter.item;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	public static ItemWrench wrench;
	
	public static void initItems() {
		wrench = new ItemWrench("wrench");
		
		GameRegistry.registerItem(wrench, "wrench");
	}
}
