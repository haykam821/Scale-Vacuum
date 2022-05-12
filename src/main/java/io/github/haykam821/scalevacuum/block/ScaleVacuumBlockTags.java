package io.github.haykam821.scalevacuum.block;

import io.github.haykam821.scalevacuum.Main;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ScaleVacuumBlockTags {
	private static final Identifier DRAGON_FIRE_BASE_BLOCKS_ID = new Identifier(Main.MOD_ID, "dragon_fire_base_blocks");
	public static final TagKey<Block> DRAGON_FIRE_BASE_BLOCKS = TagKey.of(Registry.BLOCK_KEY, DRAGON_FIRE_BASE_BLOCKS_ID);
}
