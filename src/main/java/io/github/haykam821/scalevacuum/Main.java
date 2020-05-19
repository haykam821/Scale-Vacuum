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
	private static final String MOD_ID = "scalevacuum";

	// Dimension
	private static final Identifier SCALE_VACUUM_ID = new Identifier(MOD_ID, "scale_vacuum");
	public static final Biome SCALE_VACUUM_BIOME = new ScaleVacuumBiome();

	private static final Identifier SCALE_PLATFORM_ID = new Identifier(MOD_ID, "scale_platform");
	public static final Feature<DefaultFeatureConfig> SCALE_PLATFORM = new ScalePlatformFeature(DefaultFeatureConfig::deserialize);

	public static EntityPlacer OVERWORLD_SURFACE_PLACER = (Entity entity, ServerWorld destination, Direction portalDir, double horizontalOffset, double verticalOffset) -> {
		BlockPos spawnPos = destination.getSpawnPos();
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			if (player.getSpawnPosition() != null) {
				spawnPos = player.getSpawnPosition();
			}
		}

		BlockPos topPos = destination.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, spawnPos);
		return new BlockPattern.TeleportTarget(new Vec3d(topPos), entity.getVelocity(), (int) entity.yaw);
	};
	public static EntityPlacer SCALE_VACUUM_PLACER = (Entity entity, ServerWorld destination, Direction portalDir, double horizontalOffset, double verticalOffset) -> {
		return new BlockPattern.TeleportTarget(
			new Vec3d(destination.getForcedSpawnPoint()),
			entity.getVelocity(),
			(int) entity.yaw
		);
	};

	public static FabricDimensionType SCALE_VACUUM = FabricDimensionType.builder()
		.defaultPlacer(SCALE_VACUUM_PLACER)
		.factory(ScaleVacuum::new)
		.buildAndRegister(SCALE_VACUUM_ID);

	// Items
	private static final Identifier ANCIENT_CHORUS_FRUIT_ID = new Identifier(MOD_ID, "ancient_chorus_fruit");
  	public static final Item ANCIENT_CHORUS_FRUIT = new AncientChorusFruitItem(
		new Item.Settings()
		.group(ItemGroup.MATERIALS)
		.food(FoodComponents.CHORUS_FRUIT)
	);

	private static final Identifier DRAGON_SCALE_ID = new Identifier(MOD_ID, "dragon_scale");
  	public static final Item DRAGON_SCALE = new Item(
		new Item.Settings()
		.rarity(Rarity.RARE)
		.group(ItemGroup.MATERIALS)
	);

	private static final Identifier ENDER_PURIFIER_ID = new Identifier(MOD_ID, "ender_purifier");
	public static final Item ENDER_PURIFIER = new PurifierItem(
		new Item.Settings()
		.rarity(Rarity.UNCOMMON)
		.group(ItemGroup.MISC)
		.maxCount(1)
	);

	// Blocks
	private static final Item.Settings BUILDING_BLOCK_ITEM_SETTINGS = new Item.Settings().group(ItemGroup.BUILDING_BLOCKS);
	private static final Item.Settings DECORATION_BLOCK_ITEM_SETTINGS = new Item.Settings().group(ItemGroup.DECORATIONS);

	private static final Identifier SCALE_BEDROCK_ID = new Identifier(MOD_ID, "scale_bedrock");
	public static final Block SCALE_BEDROCK = new Block(FabricBlockSettings.copy(Blocks.BEDROCK).build());
	public static final Item SCALE_BEDROCK_ITEM = new BlockItem(SCALE_BEDROCK, BUILDING_BLOCK_ITEM_SETTINGS);

	private static final Identifier SCALE_BLOCK_ID = new Identifier(MOD_ID, "scale_block");
	public static final Block SCALE_BLOCK = new Block(FabricBlockSettings.copy(Blocks.NETHER_BRICKS).build());
	public static final Item SCALE_BLOCK_ITEM = new BlockItem(SCALE_BLOCK, BUILDING_BLOCK_ITEM_SETTINGS);

	private static final Identifier SCALE_SLAB_ID = new Identifier(MOD_ID, "scale_slab");
	public static final Block SCALE_SLAB = new SlabBlock(FabricBlockSettings.copy(SCALE_BLOCK).build());
	public static final Item SCALE_SLAB_ITEM = new BlockItem(SCALE_SLAB, BUILDING_BLOCK_ITEM_SETTINGS);
	
	private static final Identifier SCALE_STAIRS_ID = new Identifier(MOD_ID, "scale_stairs");
	public static final Block SCALE_STAIRS = new ScaleStairsBlock(SCALE_BLOCK);
	public static final Item SCALE_STAIRS_ITEM = new BlockItem(SCALE_STAIRS, BUILDING_BLOCK_ITEM_SETTINGS);

	private static final Identifier SCALE_WALL_ID = new Identifier(MOD_ID, "scale_wall");
	public static final Block SCALE_WALL = new WallBlock(FabricBlockSettings.copy(SCALE_BLOCK).build());
	public static final Item SCALE_WALL_ITEM = new BlockItem(SCALE_WALL, DECORATION_BLOCK_ITEM_SETTINGS);

	private static final Identifier SMOOTH_SCALE_BLOCK_ID = new Identifier(MOD_ID, "smooth_scale_block");
	public static final Block SMOOTH_SCALE_BLOCK = new Block(FabricBlockSettings.copy(SCALE_BLOCK).build());
	public static final Item SMOOTH_SCALE_BLOCK_ITEM = new BlockItem(SMOOTH_SCALE_BLOCK, BUILDING_BLOCK_ITEM_SETTINGS);

	private static final Identifier SMOOTH_SCALE_SLAB_ID = new Identifier(MOD_ID, "smooth_scale_slab");
	public static final Block SMOOTH_SCALE_SLAB = new SlabBlock(FabricBlockSettings.copy(SMOOTH_SCALE_BLOCK).build());
	public static final Item SMOOTH_SCALE_SLAB_ITEM = new BlockItem(SMOOTH_SCALE_SLAB, BUILDING_BLOCK_ITEM_SETTINGS);

	private static final Identifier SMOOTH_SCALE_STAIRS_ID = new Identifier(MOD_ID, "smooth_scale_stairs");
	public static final Block SMOOTH_SCALE_STAIRS = new ScaleStairsBlock(SMOOTH_SCALE_BLOCK);
	public static final Item SMOOTH_SCALE_STAIRS_ITEM = new BlockItem(SMOOTH_SCALE_STAIRS, BUILDING_BLOCK_ITEM_SETTINGS);

	@Override
	public void onInitialize() {
		// Dimension
		Registry.register(Registry.BIOME, SCALE_VACUUM_ID, SCALE_VACUUM_BIOME);
		Registry.register(Registry.FEATURE, SCALE_PLATFORM_ID, SCALE_PLATFORM);

		// Items
		Registry.register(Registry.ITEM, ANCIENT_CHORUS_FRUIT_ID, ANCIENT_CHORUS_FRUIT);
		Registry.register(Registry.ITEM, DRAGON_SCALE_ID, DRAGON_SCALE);

		Registry.register(Registry.ITEM, ENDER_PURIFIER_ID, ENDER_PURIFIER);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			return 0x9E5EFF;
		}, ENDER_PURIFIER);

		// Blocks
		Registry.register(Registry.BLOCK, SCALE_BEDROCK_ID, SCALE_BEDROCK);
		Registry.register(Registry.ITEM, SCALE_BEDROCK_ID, SCALE_BEDROCK_ITEM);
		
		Registry.register(Registry.BLOCK, SCALE_BLOCK_ID, SCALE_BLOCK);
		Registry.register(Registry.ITEM, SCALE_BLOCK_ID, SCALE_BLOCK_ITEM);

		Registry.register(Registry.BLOCK, SCALE_SLAB_ID, SCALE_SLAB);
		Registry.register(Registry.ITEM, SCALE_SLAB_ID, SCALE_SLAB_ITEM);

		Registry.register(Registry.BLOCK, SCALE_STAIRS_ID, SCALE_STAIRS);
		Registry.register(Registry.ITEM, SCALE_STAIRS_ID, SCALE_STAIRS_ITEM);

		Registry.register(Registry.BLOCK, SCALE_WALL_ID, SCALE_WALL);
		Registry.register(Registry.ITEM, SCALE_WALL_ID, SCALE_WALL_ITEM);

		Registry.register(Registry.BLOCK, SMOOTH_SCALE_BLOCK_ID, SMOOTH_SCALE_BLOCK);
		Registry.register(Registry.ITEM, SMOOTH_SCALE_BLOCK_ID, SMOOTH_SCALE_BLOCK_ITEM);

		Registry.register(Registry.BLOCK, SMOOTH_SCALE_SLAB_ID, SMOOTH_SCALE_SLAB);
		Registry.register(Registry.ITEM, SMOOTH_SCALE_SLAB_ID, SMOOTH_SCALE_SLAB_ITEM);

		Registry.register(Registry.BLOCK, SMOOTH_SCALE_STAIRS_ID, SMOOTH_SCALE_STAIRS);
		Registry.register(Registry.ITEM, SMOOTH_SCALE_STAIRS_ID, SMOOTH_SCALE_STAIRS_ITEM);
	}
}