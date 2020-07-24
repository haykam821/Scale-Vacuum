package io.github.haykam821.scalevacuum.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.haykam821.scalevacuum.DragonFireBlock;
import io.github.haykam821.scalevacuum.Main;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {
	@Inject(method = "getState", at = @At("TAIL"), cancellable = true)
	private static void getDragonFireState(BlockView world, BlockPos pos, CallbackInfoReturnable<BlockState> ci) {
		BlockState baseState = world.getBlockState(pos.down());
		if (DragonFireBlock.isDragonFireBase(baseState.getBlock())) {
			ci.setReturnValue(Main.DRAGON_FIRE.getDefaultState());
		}
	}
}