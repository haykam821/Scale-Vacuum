package io.github.haykam821.scalevacuum.mixin;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;

import io.github.haykam821.scalevacuum.Main;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShearsItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ShearsItem.class)
public abstract class ShearsItemMixin {
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		Block target = world.getBlockState(pos).getBlock();

		if (target == Blocks.DRAGON_EGG) {
			ItemEntity itemEntity = new ItemEntity(
				world,
				pos.getX() + 0.5D,
				pos.getY() + 1.0D,
				pos.getZ() + 0.5D,
				new ItemStack(Main.DRAGON_SCALE, 1)
			);
			itemEntity.setVelocity(0.0D, 0.2D, 0.0D);
			world.spawnEntity(itemEntity);

			context.getStack().damage(4, context.getPlayer(), (Consumer<LivingEntity>) (LivingEntity entity) -> {
				entity.sendToolBreakStatus(context.getHand());
			});
			context.getPlayer().playSound(Main.BLOCK_DRAGON_EGG_SHEAR, 1.0F, 1.0F);

			return ActionResult.CONSUME;
		}

		return ActionResult.PASS;
	}
}