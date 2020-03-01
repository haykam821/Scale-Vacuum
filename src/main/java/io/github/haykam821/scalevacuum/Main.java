package io.github.haykam821.scalevacuum;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class Main implements ModInitializer {
	public static EntityPlacer OVERWORLD_SURFACE_PLACER = (Entity entity, ServerWorld destination, Direction portalDir, double horizontalOffset, double verticalOffset) -> {
		BlockPos spawnPos = destination.getSpawnPos();
		if (entity instanceof PlayerEntity && ((PlayerEntity) entity).getSpawnPosition() != null) {
			spawnPos = ((PlayerEntity) entity).getSpawnPosition();
		}

		BlockPos topPos = destination.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, spawnPos);
		return new BlockPattern.TeleportTarget(
			new Vec3d(topPos),
			entity.getVelocity(),
			(int) entity.yaw
		);
	};
	public static EntityPlacer SCALE_VACUUM_PLACER = (Entity entity, ServerWorld destination, Direction portalDir, double horizontalOffset, double verticalOffset) -> {
		return new BlockPattern.TeleportTarget(
			new Vec3d(destination.getForcedSpawnPoint()),
			entity.getVelocity(),
			(int) entity.yaw
		);
	};

	public static final Feature<DefaultFeatureConfig> SCALE_PLATFORM = Registry.register(
		Registry.FEATURE,
		new Identifier("scalevacuum", "scale_platform"), 
		new ScalePlatformFeature(DefaultFeatureConfig::deserialize)
	);

	public static final Biome SCALE_VACUUM_VOID = new ScaleVacuumVoidBiome();
	public static FabricDimensionType SCALE_VACUUM = FabricDimensionType.builder()
			.defaultPlacer(SCALE_VACUUM_PLACER)
			.factory(ScaleVacuum::new)
			.buildAndRegister(new Identifier("scalevacuum", "scale_vacuum"));

  	public static final Item ANCIENT_CHORUS_FRUIT = new AncientChorusFruitItem(
		new Item.Settings()
		.group(ItemGroup.MATERIALS)
		.food(FoodComponents.CHORUS_FRUIT)
	);
  	public static final Item DRAGON_SCALE = new Item(
		new Item.Settings()
		.group(ItemGroup.MATERIALS)
	);

	@Override
	public void onInitialize() {
		Registry.register(Registry.BIOME, new Identifier("scalevacuum", "scale_vacuum_void"), SCALE_VACUUM_VOID);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "ancient_chorus_fruit"), ANCIENT_CHORUS_FRUIT);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "dragon_scale"), DRAGON_SCALE);
	}
}