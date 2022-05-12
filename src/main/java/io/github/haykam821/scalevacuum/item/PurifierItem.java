package io.github.haykam821.scalevacuum.item;

import io.github.haykam821.scalevacuum.component.PurificationComponent;
import io.github.haykam821.scalevacuum.component.ScaleVacuumComponents;
import io.github.haykam821.scalevacuum.world.ScaleVacuumDimensions;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PurifierItem extends Item {
	public PurifierItem(Settings settings) {
		super(settings);
	}

	public boolean hasGlint(ItemStack itemStack) {
		return true;
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack handStack = player.getStackInHand(hand);
		if (world.isClient) {
			return TypedActionResult.pass(handStack);
		}

		// Only works in Scale Vacuum dimension
		if (!ScaleVacuumDimensions.isScaleVacuum(world)) return TypedActionResult.pass(handStack);
		PurificationComponent purification = ScaleVacuumComponents.PURIFICATION.get(world);
		int purifiedLevel = purification.getPurifiedLevel();

		// Only works if not fully purified
		if (purifiedLevel == 4) {
			return TypedActionResult.pass(handStack);
		}

		// Increase purification level
		purification.setPurifiedLevel(purifiedLevel + 1);
		
		// Update player health
		for (ServerPlayerEntity healedPlayer : PlayerLookup.world((ServerWorld) world)) {
			healedPlayer.heal(4.0F);
		}

		// Consume item
		if (!player.getAbilities().creativeMode) {
			handStack.decrement(1);
		}
		return TypedActionResult.consume(handStack);
	}
}