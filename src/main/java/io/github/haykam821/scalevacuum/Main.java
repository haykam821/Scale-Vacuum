package io.github.haykam821.scalevacuum;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
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

	private static final Item.Settings buildingBlockItem = new Item.Settings().group(ItemGroup.BUILDING_BLOCKS);

	public static final Block SCALE_BEDROCK = new Block(FabricBlockSettings.copy(Blocks.BEDROCK).build());
	public static final Item SCALE_BEDROCK_ITEM = new BlockItem(SCALE_BEDROCK, buildingBlockItem);

	public static final Block SCALE_BLOCK = new Block(FabricBlockSettings.copy(Blocks.NETHER_BRICKS).build());
	public static final Item SCALE_BLOCK_ITEM = new BlockItem(SCALE_BLOCK, buildingBlockItem);

	public static final Block SCALE_STAIRS = new ScaleStairsBlock(SCALE_BLOCK);
	public static final Item SCALE_STAIRS_ITEM = new BlockItem(SCALE_STAIRS, buildingBlockItem);

	public static final Block SCALE_SLAB = new SlabBlock(FabricBlockSettings.copy(SCALE_BLOCK).build());
	public static final Item SCALE_SLAB_ITEM = new BlockItem(SCALE_SLAB, buildingBlockItem);

	public static final Block SCALE_WALL = new WallBlock(FabricBlockSettings.copy(SCALE_BLOCK).build());
	public static final Item SCALE_WALL_ITEM = new BlockItem(
		SCALE_WALL, 
		new Item.Settings().group(ItemGroup.DECORATIONS)
	);

	public static final Block SMOOTH_SCALE_BLOCK = new Block(FabricBlockSettings.copy(SCALE_BLOCK).build());
	public static final Item SMOOTH_SCALE_BLOCK_ITEM = new BlockItem(SMOOTH_SCALE_BLOCK, buildingBlockItem);

	public static final Block SMOOTH_SCALE_STAIRS = new ScaleStairsBlock(SMOOTH_SCALE_BLOCK);
	public static final Item SMOOTH_SCALE_STAIRS_ITEM = new BlockItem(SMOOTH_SCALE_STAIRS, buildingBlockItem);

	public static final Block SMOOTH_SCALE_SLAB = new SlabBlock(FabricBlockSettings.copy(SMOOTH_SCALE_BLOCK).build());
	public static final Item SMOOTH_SCALE_SLAB_ITEM = new BlockItem(SMOOTH_SCALE_SLAB, buildingBlockItem);

	public static final Feature<DefaultFeatureConfig> SCALE_PLATFORM = Registry.register(
		Registry.FEATURE,
		new Identifier("scalevacuum", "scale_platform"), 
		new ScalePlatformFeature(DefaultFeatureConfig::deserialize)
	);

	public static final Biome SCALE_VACUUM_BIOME = new ScaleVacuumBiome();
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
		.rarity(Rarity.RARE)
		.group(ItemGroup.MATERIALS)
	);

	public static final Item ENDER_PURIFIER = new PurifierItem(
		new Item.Settings()
		.rarity(Rarity.UNCOMMON)
		.group(ItemGroup.MISC)
		.maxCount(1)
	);

	@Override
	public void onInitialize() {
		Registry.register(Registry.BIOME, new Identifier("scalevacuum", "scale_vacuum"), SCALE_VACUUM_BIOME);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "ancient_chorus_fruit"), ANCIENT_CHORUS_FRUIT);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "dragon_scale"), DRAGON_SCALE);

		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "ender_purifier"), ENDER_PURIFIER);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			return 0x9E5EFF;
		}, ENDER_PURIFIER);

		Registry.register(Registry.BLOCK, new Identifier("scalevacuum", "scale_bedrock"), SCALE_BEDROCK);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "scale_bedrock"), SCALE_BEDROCK_ITEM);
		
		Registry.register(Registry.BLOCK, new Identifier("scalevacuum", "scale_block"), SCALE_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "scale_block"), SCALE_BLOCK_ITEM);

		Registry.register(Registry.BLOCK, new Identifier("scalevacuum", "scale_slab"), SCALE_SLAB);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "scale_slab"), SCALE_SLAB_ITEM);

		Registry.register(Registry.BLOCK, new Identifier("scalevacuum", "scale_stairs"), SCALE_STAIRS);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "scale_stairs"), SCALE_STAIRS_ITEM);

		Registry.register(Registry.BLOCK, new Identifier("scalevacuum", "scale_wall"), SCALE_WALL);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "scale_wall"), SCALE_WALL_ITEM);

		Registry.register(Registry.BLOCK, new Identifier("scalevacuum", "smooth_scale_block"), SMOOTH_SCALE_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "smooth_scale_block"), SMOOTH_SCALE_BLOCK_ITEM);

		Registry.register(Registry.BLOCK, new Identifier("scalevacuum", "smooth_scale_slab"), SMOOTH_SCALE_SLAB);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "smooth_scale_slab"), SMOOTH_SCALE_SLAB_ITEM);

		Registry.register(Registry.BLOCK, new Identifier("scalevacuum", "smooth_scale_stairs"), SMOOTH_SCALE_STAIRS);
		Registry.register(Registry.ITEM, new Identifier("scalevacuum", "smooth_scale_stairs"), SMOOTH_SCALE_STAIRS_ITEM);
	}
}