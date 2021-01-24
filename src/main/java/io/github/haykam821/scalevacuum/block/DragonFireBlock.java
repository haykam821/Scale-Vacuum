package io.github.haykam821.scalevacuum.block;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class DragonFireBlock extends AbstractFireBlock {
	public DragonFireBlock(Block.Settings settings) {
      super(settings, 0.5f);
   }

	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
		return this.canPlaceAt(state, world, pos) ? this.getDefaultState() : Blocks.AIR.getDefaultState();
	}

	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return DragonFireBlock.isDragonFireBase(world.getBlockState(pos.down()).getBlock());
	}

	public static boolean isDragonFireBase(Block block) {
		return block.isIn(ScaleVacuumBlockTags.DRAGON_FIRE_BASE_BLOCKS);
	}

	public boolean isFlammable(BlockState state) {
		return true;
	}
}