package io.github.haykam821.scalevacuum;

import java.util.Random;

import com.google.common.collect.Maps;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.biome.source.FixedBiomeSourceConfig;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.FlatChunkGeneratorLayer;

public class ScaleVacuum extends Dimension {
	public static final BlockPos SPAWN_POINT = new BlockPos(8.0F, 65, 8.0F);
	private int purifiedLevel = 0;

	public ScaleVacuum(World world, DimensionType type) {
		super(world, type, 0.5F);

		CompoundTag tag = world.getLevelProperties().getWorldData(Main.SCALE_VACUUM);
		this.setPurifiedLevel(tag.getInt("PurifiedLevel"));

	}

	public int getPurifiedLevel() {
		return this.purifiedLevel;
	}

	public void setPurifiedLevel(int newLevel) {
		this.purifiedLevel = MathHelper.clamp(newLevel, 0, 4);
	}

	public void saveWorldData() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("PurifiedLevel", this.getPurifiedLevel());
		this.world.getLevelProperties().setWorldData(Main.SCALE_VACUUM, tag);
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		FlatChunkGeneratorConfig config = ChunkGeneratorType.FLAT.createSettings();

		// Layers
		config.getLayers().add(new FlatChunkGeneratorLayer(1, Blocks.BARRIER));
		config.updateLayerBlocks();
		
		// Platform
		config.getStructures().put("decoration", Maps.newHashMap());

		// Biome
		config.setBiome(Main.SCALE_VACUUM_BIOME);
		FixedBiomeSourceConfig biomeConfig = BiomeSourceType.FIXED
			.getConfig(world.getLevelProperties())
			.setBiome(Main.SCALE_VACUUM_BIOME);

		return ChunkGeneratorType.FLAT.create(world, BiomeSourceType.FIXED.applyConfig(biomeConfig), config);
	}

	@Override
	public float getSkyAngle(long worldTime, float tickDelta) {
		return 0;
	}

	@Environment(EnvType.CLIENT)
	@Override
	public Vec3d getFogColor(float skyAngle, float tickDelta) {
		return new Vec3d(0, 0, 0);
	}

	@Override
	public DimensionType getType() {
		return Main.SCALE_VACUUM;
	}

	@Override
	public boolean canPlayersSleep() {
		return false;
	}

	@Override
	public BlockPos getSpawningBlockInChunk(ChunkPos chunkPos, boolean bool) {
		Random random = new Random(this.world.getSeed());
		BlockPos blockPos = new BlockPos(chunkPos.getStartX() + random.nextInt(15), 0, chunkPos.getEndZ() + random.nextInt(15));
		return this.world.getTopNonAirState(blockPos).getMaterial().blocksMovement() ? blockPos : null;
	}

	public BlockPos getTopSpawningBlockPosition(int i, int j, boolean bool) {
		return this.getSpawningBlockInChunk(new ChunkPos(i >> 4, j >> 4), bool);
	}

	public BlockPos getForcedSpawnPoint() {
		return SPAWN_POINT;
	}

	@Override
	public boolean hasVisibleSky() {
		return false;
	}

	@Override
	public boolean isFogThick(int argA, int argB) {
		return false;
	}
}