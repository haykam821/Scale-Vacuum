package io.github.haykam821.scalevacuum.block;

import io.github.haykam821.scalevacuum.Main;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public final class ScaleVacuumBlockTags {
	private static final Identifier DRAGON_FIRE_BASE_BLOCKS_ID = new Identifier(Main.MOD_ID, "dragon_fire_base_blocks");
	public static final Tag<Block> DRAGON_FIRE_BASE_BLOCKS = TagRegistry.block(DRAGON_FIRE_BASE_BLOCKS_ID);
}
