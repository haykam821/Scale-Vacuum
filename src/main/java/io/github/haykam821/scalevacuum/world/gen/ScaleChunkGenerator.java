package io.github.haykam821.scalevacuum.world.gen;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.MultiNoiseUtil.MultiNoiseSampler;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.YLevels;
import net.minecraft.world.gen.GenerationStep.Carver;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;

public class ScaleChunkGenerator extends ChunkGenerator {
	public static final Codec<ScaleChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> {
		return ChunkGenerator
			.method_41042(instance)
			.and(BiomeSource.CODEC.fieldOf("biome_source").forGetter(generator -> generator.biomeSource))
			.apply(instance, instance.stable(ScaleChunkGenerator::new));
	});

	public ScaleChunkGenerator(Registry<StructureSet> structureSetRegistry, BiomeSource biomeSource) {
		super(structureSetRegistry, Optional.empty(), biomeSource);
	}

	@Override
	public void buildSurface(ChunkRegion region, StructureAccessor structures, Chunk chunk) {
		return;
	}

	@Override
	public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world) {
		return new VerticalBlockSample(world.getBottomY(), new BlockState[0]);
	}

	@Override
	public int getHeight(int x, int z, Type heightmap, HeightLimitView world) {
		return 0;
	}

	@Override
	public Codec<ScaleChunkGenerator> getCodec() {
		return CODEC;
	}

	@Override
	public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, StructureAccessor structures, Chunk chunk) {
		return CompletableFuture.completedFuture(chunk);
	}

	@Override
	public ChunkGenerator withSeed(long seed) {
		return this;
	}

	@Override
	public MultiNoiseSampler getMultiNoiseSampler() {
		return MultiNoiseUtil.method_40443();
	}

	@Override
	public void getDebugHudText(List<String> text, BlockPos pos) {
		return;
	}

	@Override
	public void carve(ChunkRegion chunkRegion, long seed, BiomeAccess biomes, StructureAccessor structures, Chunk chunk, Carver carver) {
		return;
	}

	@Override
	public void populateEntities(ChunkRegion region) {
		return;
	}

	@Override
	public int getMinimumY() {
		return YLevels.END_MIN_Y;
	}

	@Override
	public int getSeaLevel() {
		return YLevels.END_MIN_Y;
	}

	@Override
	public int getWorldHeight() {
		return YLevels.END_GENERATION_HEIGHT;
	}
}