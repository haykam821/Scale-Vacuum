package io.github.haykam821.scalevacuum.world.gen;

import java.util.Collections;
import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;

public class ScaleChunkGenerator extends ChunkGenerator {
	public static final Codec<ScaleChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> {
		return instance
			.group(BiomeSource.CODEC
				.fieldOf("biome_source")
				.forGetter(generator -> generator.populationSource))
			.apply(instance, ScaleChunkGenerator::new);
	});

	public ScaleChunkGenerator(BiomeSource biomeSource) {
		super(biomeSource, new StructuresConfig(Optional.empty(), Collections.emptyMap()));
	}

	@Override
	public void buildSurface(ChunkRegion region, Chunk chunk) {
		return;
	}

	@Override
	public BlockView getColumnSample(int x, int z) {
		return new VerticalBlockSample(new BlockState[0]);
	}

	@Override
	public int getHeight(int x, int z, Type heightmapType) {
		return 0;
	}

	@Override
	public Codec<ScaleChunkGenerator> getCodec() {
		return CODEC;
	}

	@Override
	public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
		return;
	}

	@Override
	public ChunkGenerator withSeed(long seed) {
		return this;
	}
}