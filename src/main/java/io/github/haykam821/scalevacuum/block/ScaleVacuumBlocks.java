package io.github.haykam821.scalevacuum.block;

import io.github.haykam821.scalevacuum.Main;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ScaleVacuumBlocks {
	SCALE_BEDROCK("scale_bedrock", new Block(FabricBlockSettings.copy(Blocks.BEDROCK)), ItemGroup.BUILDING_BLOCKS),
	SCALE_BLOCK("scale_block", new Block(FabricBlockSettings.copy(Blocks.NETHER_BRICKS)), ItemGroup.BUILDING_BLOCKS),
	SCALE_SLAB("scale_slab", new SlabBlock(FabricBlockSettings.copy(SCALE_BLOCK.block)), ItemGroup.BUILDING_BLOCKS),
	SCALE_STAIRS("scale_stairs", new ScaleStairsBlock(SCALE_BLOCK.block), ItemGroup.BUILDING_BLOCKS),
	SCALE_WALL("scale_wall", new WallBlock(FabricBlockSettings.copy(SCALE_BLOCK.block)), ItemGroup.DECORATIONS),
	SMOOTH_SCALE_BLOCK("smooth_scale_block", new Block(FabricBlockSettings.copy(SCALE_BLOCK.block)), ItemGroup.BUILDING_BLOCKS),
	SMOOTH_SCALE_SLAB("smooth_scale_slab", new SlabBlock(FabricBlockSettings.copy(SMOOTH_SCALE_BLOCK.block)), ItemGroup.BUILDING_BLOCKS),
	SMOOTH_SCALE_STAIRS("smooth_scale_stairs", new ScaleStairsBlock(SMOOTH_SCALE_BLOCK.block), ItemGroup.BUILDING_BLOCKS),
	DRAGON_FIRE("dragon_fire", new DragonFireBlock(FabricBlockSettings.copyOf(Blocks.FIRE).materialColor(MaterialColor.MAGENTA).luminance(5)), (Item) null);

	private final Identifier id;
	private final Block block;
	private final Item item;

	private ScaleVacuumBlocks(String path, Block block, Item item) {
		this.id = new Identifier(Main.MOD_ID, path);
		this.block = block;
		this.item = item;
	}

	private ScaleVacuumBlocks(String path, Block block, ItemGroup group) {
		this(path, block, new BlockItem(block, new Item.Settings().group(group)));
	}

	public Block getBlock() {
		return this.block;
	}

	public BlockState getDefaultState() {
		return this.block.getDefaultState();
	}

	public static void register() {
		for (ScaleVacuumBlocks block : ScaleVacuumBlocks.values()) {
			Registry.register(Registry.BLOCK, block.id, block.block);
			if (block.item != null) {
				Registry.register(Registry.ITEM, block.id, block.item);
			}
		}
	}
}
