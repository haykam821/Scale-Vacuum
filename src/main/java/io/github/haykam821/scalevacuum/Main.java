package io.github.haykam821.scalevacuum;

import java.util.Optional;

import io.github.haykam821.scalevacuum.block.ScaleVacuumBlocks;
import io.github.haykam821.scalevacuum.item.ScaleVacuumItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class Main implements ModInitializer {
	public static final String MOD_ID = "scalevacuum";

	// Dimension
	private static final Identifier SCALE_PLATFORM_ID = new Identifier(MOD_ID, "scale_platform");
	private static final Feature<DefaultFeatureConfig> SCALE_PLATFORM = new ScalePlatformFeature(DefaultFeatureConfig.CODEC);
	public static final ConfiguredFeature<?, ?> CONFIGURED_SCALE_PLATFORM = Main.SCALE_PLATFORM
		.configure(FeatureConfig.DEFAULT)
		.createDecoratedFeature(Decorator.NOPE.configure(DecoratorConfig.DEFAULT));

	private static final Identifier SCALE_VACUUM_ID = new Identifier(MOD_ID, "scale_vacuum");
	public static final RegistryKey<World> SCALE_VACUUM_KEY = RegistryKey.of(Registry.DIMENSION, SCALE_VACUUM_ID);
	public static final RegistryKey<DimensionType> SCALE_VACUUM_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, SCALE_VACUUM_ID);

	public static final Biome SCALE_VACUUM_BIOME = new ScaleVacuumBiome();

	public static EntityPlacer EXIT_SURFACE_PLACER = (Entity entity, ServerWorld destination, Direction portalDir, double horizontalOffset, double verticalOffset) -> {
		Optional<Vec3d> respawnPos = Optional.empty();
		if (entity instanceof PlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) entity;
			BlockPos spawnPointPos = player.getSpawnPointPosition();
			if (spawnPointPos != null) {
				respawnPos = PlayerEntity.findRespawnPosition(destination, spawnPointPos, true, false);
			}
		}

		BlockPos topPos = destination.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, respawnPos.map(BlockPos::new).orElseGet(destination::getSpawnPos));
		return new BlockPattern.TeleportTarget(Vec3d.of(topPos), entity.getVelocity(), (int) entity.yaw);
	};

	private static final Vec3d SCALE_VACUUM_SPAWN_POS = new Vec3d(8, 65, 8);
	public static EntityPlacer SCALE_VACUUM_PLACER = (Entity entity, ServerWorld destination, Direction portalDir, double horizontalOffset, double verticalOffset) -> {
		return new BlockPattern.TeleportTarget(
			SCALE_VACUUM_SPAWN_POS,
			entity.getVelocity(),
			(int) entity.yaw
		);
	};

	// Sound events
	private static final Identifier BLOCK_DRAGON_EGG_SHEAR_ID = new Identifier(MOD_ID, "block.dragon_egg.shear");
	public static final SoundEvent BLOCK_DRAGON_EGG_SHEAR = new SoundEvent(BLOCK_DRAGON_EGG_SHEAR_ID);

	@Override
	public void onInitialize() {
		ScaleVacuumItems.register();
		ScaleVacuumBlocks.register();

		// Dimension
		Registry.register(Registry.FEATURE, SCALE_PLATFORM_ID, SCALE_PLATFORM);
		Registry.register(Registry.BIOME, SCALE_VACUUM_ID, SCALE_VACUUM_BIOME);

		Registry.register(Registry.CHUNK_GENERATOR, SCALE_VACUUM_ID, ScaleChunkGenerator.CODEC);
		
		// Sound events
		Registry.register(Registry.SOUND_EVENT, BLOCK_DRAGON_EGG_SHEAR_ID, BLOCK_DRAGON_EGG_SHEAR);
	}

	public static boolean isScaleVacuum(World world) {
		return world.getDimensionRegistryKey() == Main.SCALE_VACUUM_TYPE_KEY;
	}
}