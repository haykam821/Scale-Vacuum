package io.github.haykam821.scalevacuum;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class ScalePlatformFeature extends Feature<DefaultFeatureConfig> {
	public static final BlockState PLATFORM_BLOCK = Blocks.OBSIDIAN.getDefaultState();
	public static final BlockState CENTER_BLOCK = Main.SCALE_BEDROCK.getDefaultState();

	public static final BlockPos PLATFORM_CENTER_POS = new BlockPos(8, 64, 8);
    public static final ChunkPos PLATFORM_CENTER_CHUNK_POS = new ChunkPos(PLATFORM_CENTER_POS);

	public ScalePlatformFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> config) {
		super(config);
	}
 
	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		ChunkPos chunkPos = new ChunkPos(pos);
		if (chunkPos.x != PLATFORM_CENTER_CHUNK_POS.x || chunkPos.z != PLATFORM_CENTER_CHUNK_POS.z) {
			return true;
		}

		BlockPos.Mutable mutable = new BlockPos.Mutable();
		for (int i = chunkPos.getStartZ(); i <= chunkPos.getEndZ(); i++) {
			for (int j = chunkPos.getStartX(); j <= chunkPos.getEndX(); j++) {
				mutable.set(j, PLATFORM_CENTER_POS.getY(), i);

				if ((i == 7 || i == 8) && (j == 7 || j == 8)) {
					world.setBlockState(mutable, CENTER_BLOCK, 2);
				} else {
					world.setBlockState(mutable, PLATFORM_BLOCK, 2);
				}
			}
		}

		return true;
	}
}