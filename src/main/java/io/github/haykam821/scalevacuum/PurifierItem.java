package io.github.haykam821.scalevacuum;

import java.util.Iterator;

import net.fabricmc.fabric.api.server.PlayerStream;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;

public class PurifierItem extends Item {
	public PurifierItem(Settings settings) {
		super(settings);
	}

	public boolean hasEnchantmentGlint(ItemStack itemStack) {
		return true;
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack handStack = player.getStackInHand(hand);
		if (world.isClient) {
			return TypedActionResult.pass(handStack);
		}

		// Only works in Scale Vacuum dimension
		Dimension dimension = world.dimension;
		if (!(dimension instanceof ScaleVacuum)) return TypedActionResult.pass(handStack);
		ScaleVacuum scaleVacuum = (ScaleVacuum) world.dimension;
		int purifiedLevel = scaleVacuum.getPurifiedLevel();

		// Only works if not fully purified
		if (purifiedLevel == 4) {
			return TypedActionResult.pass(handStack);
		}

		// Increase purification level
		scaleVacuum.setPurifiedLevel(purifiedLevel + 1);
		
		// Update player health
		Iterator<PlayerEntity> players = PlayerStream.world(world).iterator();
		while (players.hasNext()) {
			PlayerEntity nextPlayer = players.next();
			nextPlayer.heal(4.0F);
		}

		// Consume item
		if (!player.abilities.creativeMode) {
			handStack.decrement(1);
		}
		return TypedActionResult.consume(handStack);
	}
}