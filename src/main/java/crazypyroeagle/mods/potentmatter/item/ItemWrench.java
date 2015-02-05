package crazypyroeagle.mods.potentmatter.item;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

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
	public Set<String> getToolClasses(ItemStack itemStack) {
		if (itemStack.getItem() instanceof ItemWrench)
			return toolClasses;
		return itemStack.getItem().getToolClasses(itemStack);
	}
}
