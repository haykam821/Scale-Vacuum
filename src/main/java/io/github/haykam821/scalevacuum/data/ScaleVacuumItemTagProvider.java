package io.github.haykam821.scalevacuum.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;

public class ScaleVacuumItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public ScaleVacuumItemTagProvider(FabricDataGenerator dataGenerator, FabricTagProvider.BlockTagProvider blockTags) {
		super(dataGenerator, blockTags);
	}

	@Override
	protected void generateTags() {
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.WALLS, ItemTags.WALLS);
	}
}