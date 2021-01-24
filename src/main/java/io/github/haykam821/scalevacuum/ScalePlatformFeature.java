package io.github.haykam821.scalevacuum;

import java.util.Random;

import com.mojang.serialization.Codec;

import io.github.haykam821.scalevacuum.block.ScaleVacuumBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class ScalePlatformFeature extends Feature<DefaultFeatureConfig> {
	public static final BlockState CORNER_BLOCK = Blocks.AIR.getDefaultState();
	public static final BlockState BORDER_BLOCK = ScaleVacuumBlocks.SMOOTH_SCALE_BLOCK.getDefaultState();
	public static final BlockState CENTER_BLOCK = ScaleVacuumBlocks.SCALE_BEDROCK.getDefaultState();
	public static final BlockState PLATFORM_BLOCK = ScaleVacuumBlocks.SCALE_BLOCK.getDefaultState();

	public static final BlockPos PLATFORM_CENTER_POS = new BlockPos(8, 64, 8);
    public static final ChunkPos PLATFORM_CENTER_CHUNK_POS = new ChunkPos(PLATFORM_CENTER_POS);

	public ScalePlatformFeature(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	private boolean shouldGenerateCorner(int relativeX, int relativeZ) {
		return this.shouldGenerateBorder(relativeX, relativeZ) && (relativeX + relativeZ) % 15 == 0;
	}

	private boolean shouldGenerateBorder(int relativeX, int relativeZ) {
		return relativeX == 0 || relativeZ == 0 || relativeX == 15 || relativeZ == 15;
	}

	private boolean shouldGenerateCenter(int relativeX, int relativeZ) {
		return (relativeX == 7 || relativeX == 8) && (relativeZ == 7 || relativeZ == 8);
	}
 
	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		ChunkPos chunkPos = new ChunkPos(pos);
		if (chunkPos.x != PLATFORM_CENTER_CHUNK_POS.x || chunkPos.z != PLATFORM_CENTER_CHUNK_POS.z) {
			return true;
		}

		BlockPos.Mutable mutable = new BlockPos.Mutable();
		for (int i = chunkPos.getStartZ(); i <= chunkPos.getEndZ(); i++) {
			for (int j = chunkPos.getStartX(); j <= chunkPos.getEndX(); j++) {
				mutable.set(j, PLATFORM_CENTER_POS.getY(), i);

				int relativeX = i - chunkPos.getStartX();
				int relativeZ = j - chunkPos.getStartZ();

				if (this.shouldGenerateCorner(relativeX, relativeZ)) {
					world.setBlockState(mutable, CORNER_BLOCK, 2);
				} else if (this.shouldGenerateBorder(relativeX, relativeZ)) {
					world.setBlockState(mutable, BORDER_BLOCK, 2);
				} else if (this.shouldGenerateCenter(relativeX, relativeZ)) {
					world.setBlockState(mutable, CENTER_BLOCK, 2);
				} else {
					world.setBlockState(mutable, PLATFORM_BLOCK, 2);
				}
			}
		}

		return true;
	}
}