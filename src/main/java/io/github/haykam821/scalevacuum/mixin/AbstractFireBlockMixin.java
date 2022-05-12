package io.github.haykam821.scalevacuum.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.haykam821.scalevacuum.block.DragonFireBlock;
import io.github.haykam821.scalevacuum.block.ScaleVacuumBlocks;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {
	@Inject(method = "getState", at = @At("TAIL"), cancellable = true)
	private static void getDragonFireState(BlockView world, BlockPos pos, CallbackInfoReturnable<BlockState> ci) {
		BlockState baseState = world.getBlockState(pos.down());
		if (DragonFireBlock.isDragonFireBase(baseState)) {
			ci.setReturnValue(ScaleVacuumBlocks.DRAGON_FIRE.getDefaultState());
		}
	}
}