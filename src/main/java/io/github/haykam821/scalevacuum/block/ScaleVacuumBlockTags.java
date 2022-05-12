package io.github.haykam821.scalevacuum.block;

import io.github.haykam821.scalevacuum.Main;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ScaleVacuumBlockTags {
	public static final TagKey<Block> DRAGON_FIRE_BASE_BLOCKS = ScaleVacuumBlockTags.of("dragon_fire_base_blocks");
	public static final TagKey<Block> INFINIBURN_SCALE_VACUUM = ScaleVacuumBlockTags.of("infiniburn_scale_vacuum");

	private static TagKey<Block> of(String path) {
		Identifier id = new Identifier(Main.MOD_ID, path);
		return TagKey.of(Registry.BLOCK_KEY, id);
	}
}
