package io.github.haykam821.scalevacuum.data;

import io.github.haykam821.scalevacuum.block.ScaleVacuumBlockTags;
import io.github.haykam821.scalevacuum.block.ScaleVacuumBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.BlockTags;

public class ScaleVacuumBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public ScaleVacuumBlockTagProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void generateTags() {
		this.getOrCreateTagBuilder(BlockTags.FIRE)
			.add(ScaleVacuumBlocks.DRAGON_FIRE.getBlock());

		this.getOrCreateTagBuilder(BlockTags.SLABS)
			.add(ScaleVacuumBlocks.SCALE_SLAB.getBlock())
			.add(ScaleVacuumBlocks.SMOOTH_SCALE_SLAB.getBlock());

		this.getOrCreateTagBuilder(BlockTags.STAIRS)
			.add(ScaleVacuumBlocks.SCALE_STAIRS.getBlock())
			.add(ScaleVacuumBlocks.SMOOTH_SCALE_STAIRS.getBlock());

		this.getOrCreateTagBuilder(BlockTags.WALLS)
			.add(ScaleVacuumBlocks.SCALE_WALL.getBlock());

		this.getOrCreateTagBuilder(ScaleVacuumBlockTags.DRAGON_FIRE_BASE_BLOCKS)
			.add(ScaleVacuumBlocks.SCALE_BEDROCK.getBlock());

		this.getOrCreateTagBuilder(ScaleVacuumBlockTags.INFINIBURN_SCALE_VACUUM)
			.forceAddTag(BlockTags.INFINIBURN_OVERWORLD);
	}
}