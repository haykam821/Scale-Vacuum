package io.github.haykam821.scalevacuum;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class ScaleStairsBlock extends StairsBlock {
	public ScaleStairsBlock(Block block) {
		super(block.getDefaultState(), FabricBlockSettings.copy(block).build());
	}
}